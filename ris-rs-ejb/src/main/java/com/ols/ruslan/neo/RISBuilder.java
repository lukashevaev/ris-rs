package com.ols.ruslan.neo;

import java.util.*;

/**
 * Данный класс используется для того,
 * чтобы собрать библиографическую запись в формат RIS
 */
public class RISBuilder {

    private final Map<String, String> fields;
    private final String recordType;

    public RISBuilder(Map<String, String> fields){
        this.fields = fields;
        TypeDefiner typeDefiner = new TypeDefiner(fields);
        this.recordType = typeDefiner.getRecordType();
        refactorFields();
    }
    // Метод для выделения цифр из поля
    public String getDigits(String field) {
        return field.replaceAll("[^0-9]", "");
    }

    private boolean isExist(String fieldName){
        return fields.get(fieldName) != null;
    }

    // Изменение полей
    private void refactorFields(){

        fields.remove("TY");
        fields.remove("AN");


        // Если том не подходит под паттерн, то он удаляется
        String volume = isExist("VL") ? fields.get("VL").toLowerCase() : "";
        if (!PatternFactory.volumePattern.matcher(volume).find()) fields.remove("VL");
        else fields.put("VL", getDigits(volume));

        // Если тип записи не статья, то удаляется номер журнала
        if (isExist("M1")){
            if (!recordType.equals("@article")) fields.remove("number");
        }
        // Если тип записи статья, но номер журнала не подходит под паттерн, то удаляется номер журнала.
        // В противном случае удаляется номер тома (эти 2 поля по сути идентичны, поэтому одно из них надо удалить из статьи)
        if (recordType.equals("@article") && fields.get("number") != null) {
            if (!PatternFactory.numberPattern.matcher(fields.get("number").toLowerCase()).find()) fields.remove("M1");
            else fields.remove("VL");
        }
        // Если есть поле Страницы, удовлетворяющее паттерну, то выделяем из него цифры
        String pages = isExist("EP") ? fields.get("EP").toLowerCase() : "";
        if (!PatternFactory.pagePattern.matcher(pages).find()) fields.remove("EP");
        else fields.put("EP", getDigits(fields.get("EP")));

        // Выделяем цифры из поля Издание
        if (isExist("ET")) fields.put("ET", getDigits(fields.get("ET")));


        // Удаление "and" в конце поля "author"
        String author = fields.get("author");
        if (author != null) fields.put("author", author.substring(0, author.length() - 4));
    }

    public String buildRIS(){
        StringBuilder risText = new StringBuilder();
        risText.append("TY-")
                .append(recordType)
                .append("\n");
        for (Map.Entry<String, String> entry : fields.entrySet()) {
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
