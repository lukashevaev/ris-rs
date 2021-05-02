package com.ols.ruslan.neo;

import java.util.Map;
import java.util.regex.Pattern;

public class TypeDefiner {
    private final Map<String, String> fields;
    private String recordType;
    private final Map<RecordType, Pattern> patternsForType;

    public TypeDefiner(Map<String, String> fields){
        PatternFactory patternFactory = PatternFactory.getInstance();
        patternsForType = patternFactory.getPatternsForType();
        this.fields = fields;
        if (!fields.isEmpty()) {
            recordType = this.fields.get("TY").toLowerCase();
            defineType();
        }
    }

    private void defineType(){
        boolean isChanged = false;
        //patternsLookup
        for (Map.Entry<RecordType,Pattern> entry : patternsForType.entrySet()) {
            if (entry.getValue().matcher(recordType).find() ||
                    entry.getValue().matcher(fields.get("T1").toLowerCase()).find()) {
                recordType =  entry.getKey().toString();
                isChanged = true;
            }
        }

        //searchForSpecialCases
        //checkForArticle
        if (fields.get("JA") != null) {
            if (patternsForType.get(RecordType.MGZN).matcher(fields.get("JA").toLowerCase()).find()) {
                recordType = "MGZN";
                return;
            }
            if (!recordType.equals("MGZN")) fields.remove("journal");
        }
        //check for book
        String pages = fields.get("EP") != null ? fields.get("EP").toLowerCase() : "";
        if (PatternFactory.pagePattern.matcher(pages).find()
                & !PatternFactory.pagesPattern.matcher(pages).find()) {
            recordType = "BOOK";
        }

        /*//check books-group
        if (recordType.equals("BOOK")){
            if (fields.get("VL") != null & PatternFactory.chapterPattern.matcher(fields.get("VL").toLowerCase()).find()) {
                recordType = "CHAP";
            }
        }*/

        if(!isChanged) recordType = "HELLO";
    }

    public String getRecordType(){
        return recordType;
    }
}
