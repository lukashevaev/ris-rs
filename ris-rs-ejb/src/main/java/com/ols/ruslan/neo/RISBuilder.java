package com.ols.ruslan.neo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Данный класс используется для того,
 * чтобы собрать библиографическую запись в формат RIS
 */
public class RISBuilder {

    private String recordType;
    RISInstance instance;

    public RISBuilder(Map<String, String> fields){
        instance = new RISInstance(fields);
        TypeDefiner typeDefiner = new TypeDefiner(instance);
        this.recordType = typeDefiner.getRecordType();
        refactorFields();
    }
    // Метод для выделения цифр из поля
    public String getDigits(String field) {
        return field.replaceAll("[^0-9]", "");
    }

    private boolean isExist(String fieldName){
        return instance.getFields().get(fieldName) != null;
    }

    // Изменение полей
    private void refactorFields(){

        instance.deleteRecordType();


        // Если том не подходит под паттерн, то он удаляется
        String volume = instance.getVolume();
        if (!PatternFactory.volumePattern.matcher(volume).find()) instance.deleteVolume();
        else instance.setVolume(getDigits(volume));

        /*// Если тип записи не статья, то удаляется номер журнала
        if (isExist("M1")){
            if (!recordType.equals("@article")) fields.remove("number");
        }*/
        /*// Если тип записи статья, но номер журнала не подходит под паттерн, то удаляется номер журнала.
        // В противном случае удаляется номер тома (эти 2 поля по сути идентичны, поэтому одно из них надо удалить из статьи)
        if (recordType.equals("@article") && fields.get("number") != null) {
            if (!PatternFactory.numberPattern.matcher(fields.get("number").toLowerCase()).find()) fields.remove("M1");
            else fields.remove("VL");
        }*/
        /*// Если есть поле Страницы, удовлетворяющее паттерну, то выделяем из него цифры
        String pages = isExist("EP") ? fields.get("EP").toLowerCase() : "";
        if (!PatternFactory.pagePattern.matcher(pages).find()) fields.remove("EP");
        else fields.put("EP", getDigits(fields.get("EP")));*/
        instance.setPages(getDigits(instance.getPages()));

        // Выделяем цифры из поля Издание
        instance.setEdition(getDigits(instance.getEdition()));


        // Удаление "and" в конце поля "author"
        String author = instance.getAuthor();
        String[] authors = author.split("and");
        int lastAnd = author.lastIndexOf("and");
        if (authors.length > 1) instance.setAuthor(author.substring(0, lastAnd));

        //Удаляем пустые поля
        instance.setFields(
                instance.getFields()
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() != null && !entry.getValue().equals("") && PatternFactory.notEmptyFieldPattern.matcher(entry.getValue()).find())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue , (a, b) -> a, LinkedHashMap::new)));

    }

    public String buildRIS(){
        StringBuilder risText = new StringBuilder();
        risText.append("TY-")
                .append(recordType)
                .append("\n");
        for (Map.Entry<String, String> entry : instance.getFields().entrySet()) {
            String field = entry.getValue();
            String parameter = entry.getKey();
            if (field != null) risText.append(parameter)
                    .append("-")
                    .append(field)
                    .append("\n");
        }
        // Добавление последнего обзательного тэга
        return risText.append("ER-").toString();
    }

}
