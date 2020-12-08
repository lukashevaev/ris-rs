package com.ols.record;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class TypeDefiner {
    private final Map<String, String> fields;
    private String recordType;
    private final Map<String, Set<Pattern>> patternsForType;

    public TypeDefiner(Map<String, String> fields){
        patternsForType = PatternFactory.getPatternsForType();
        this.fields = fields;
        recordType = fields.get("TY").toLowerCase();
        defineType();
    }

    private void defineType(){
        boolean isChanged = false;
        //patternsLookup
        for (Map.Entry<String, Set<Pattern>> entry : patternsForType.entrySet()) {
            for (Pattern pattern : entry.getValue()) {
                if (pattern.matcher(recordType).find() || pattern.matcher(fields.get("T1").toLowerCase()).find()) {
                    recordType =  entry.getKey();
                    isChanged = true;
                }
            }
        }

        //check books-group
        if (recordType.equals("BOOK")){
            if (fields.get("VL") != null & PatternFactory.chapterPattern.matcher(fields.get("VL").toLowerCase()).find()) {
                recordType = "CHAP";
            }
        }

        if(!isChanged) recordType = "HELLO";
    }

    public String getRecordType(){
        return recordType;
    }
}
