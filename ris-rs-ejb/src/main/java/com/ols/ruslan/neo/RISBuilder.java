package com.ols.ruslan.neo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Данный класс используется для того,
 * чтобы собрать библиографическую запись в формат RIS
 */
public class RISBuilder {

    private final String recordType;
    private final RISInstance instance;
    private final NavigableMap<String, String> authorFields = new TreeMap<>();

    public RISBuilder(Map<String, String> fields){
        instance = new RISInstance(fields);
        TypeDefiner typeDefiner = new TypeDefiner(instance);
        this.recordType = typeDefiner.getRecordType().toUpperCase();
        refactorFields();
    }

    // Метод для выделения цифр из поля
    public String getDigits(String field) {
        return field.replaceAll("[^0-9-]", "");
    }

    public String getDigits2(String field) {
        return field.replaceAll("[^0-9]", "");
    }

    // Метод для выделения цифр из поля
    public String replaceDigits(String field) {
        return field.replaceAll("[0-9]", "");
    }

    private Integer getPosition(String[] array, String destination) {
        for (int i = 0; i <= array.length; i++) {
            if (Objects.equals(array[i], destination)) {
                return i;
            }
        }
        return -1;
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
        instance.setNumber(getDigits(instance.getNumber()));

        // Выделяем цифры из поля Издание
        instance.setEdition(getDigits2(instance.getEdition()));

        // Удаление "and" в конце поля "author"
        /*String author = instance.getAuthor();
        String[] authors = author.split("and");
        int lastAnd = author.lastIndexOf("and");
        if (authors.length > 1) instance.setAuthor(author.substring(0, lastAnd));*/

        String[] authors = instance.getAuthor().split("/-/");
        if (authors.length > 1) {
            instance.getFields().remove("AU");
            Arrays.stream(authors).forEach(author -> {
                String[] authorParts = author.trim().split(" ");
                String name = authorParts[0] + ", ";
                StringBuilder authorBuilder = new StringBuilder();
                authorBuilder.append(name);
                Arrays.stream(authorParts).skip(1).forEach(str -> authorBuilder.append(str).append(" "));
                String fieldName = String.format("AU%s", getPosition(authors, author) + 1);
                authorFields.put(fieldName, authorBuilder.toString());
            });
        } else instance.setAuthor(instance.getAuthor().replace("/-/", ""));


        //Удаляем пустые поля
        instance.setFields(
                instance.getFields()
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() != null && !entry.getValue().equals("") && PatternFactory.notEmptyFieldPattern.matcher(entry.getValue()).find())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue , (a, b) -> a, LinkedHashMap::new)));

        instance.deleteRecordType();
    }

    public String buildRIS(){
        StringBuilder risText = new StringBuilder();
        risText.append(String.format("TY-%s\n", recordType));
        authorFields.forEach((key, value) -> risText.append(String.format("%s-%s\n", replaceDigits(key), value)));

        instance.getFields().forEach((key, value) -> risText.append(String.format("%s-%s\n", key, value)));

        // Добавление последнего обзательного тэга
        return risText.append("ER-").toString();
    }

}
