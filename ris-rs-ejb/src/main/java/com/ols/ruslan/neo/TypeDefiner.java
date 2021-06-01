package com.ols.ruslan.neo;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Данный класс используется для того, чтобы определить тип записи на основании паттернов
 */
public class TypeDefiner {
    private String recordType;
    private final Map<RecordType, Pattern> patternsForType;
    private Set<String> recordTypes = new HashSet<>();
    private final RISInstance instance;

    public TypeDefiner(RISInstance instance){
        PatternFactory patternFactory = new PatternFactory();
        patternsForType = patternFactory.getPatternsForType();
        this.instance = instance;
        if (!instance.getFields().isEmpty()) {
            recordType = instance.getRecordType().toLowerCase();
            defineType();
        }
    }

    private void defineType(){
        boolean isChanged = false;
        // Поиск по паттернам
        for (Map.Entry<RecordType,Pattern> entry : patternsForType.entrySet()) {
            if (entry.getValue().matcher(recordType).find() ||
                    entry.getValue().matcher(instance.getTitle().toLowerCase()).find()) {
                recordType =  entry.getKey().toString();
                isChanged = true;
            }
        }

        //searchForSpecialCases ДОДЕЛАТЬ ДОДЕЛАТЬ ДОДЕЛАТЬ ДОДЕЛАТЬ ДОДЕЛАТЬ ДОДЕЛАТЬ ДОДЕЛАТЬ ДОДЕЛАТЬ ДОДЕЛАТЬ ДОДЕЛАТЬ ДОДЕЛАТЬ

        // Если есть поле "Периодическое издание", то тип записи определяется как "Статья в журнале"
        if (!instance.getJournal().equals("")) {
            if (patternsForType.get(RecordType.MGZN).matcher(instance.getJournal().toLowerCase()).find()) {
                recordType = "MGZN";
                return;
            }
        }
        // Проверка на тип "Книга" по паттернам страниц:
        // если паттерн не "digit-digit", а является общим количеством страниц, то это книга
        String pages = instance.getPages();
        if (PatternFactory.pagePattern.matcher(pages).find()
                & !PatternFactory.pagesPattern.matcher(pages).find()) {
            recordType = "BOOK";
            return;
        }

        /*//check books-group
        if (recordType.equals("BOOK")){
            if (fields.get("VL") != null & PatternFactory.chapterPattern.matcher(fields.get("VL").toLowerCase()).find()) {
                recordType = "CHAP";
            }
        }*/

        if(!isChanged && (recordType == null || "".equals(recordType))) recordType = "UNPB";
    }

    public String getRecordType(){
        return recordType;
    }
}
