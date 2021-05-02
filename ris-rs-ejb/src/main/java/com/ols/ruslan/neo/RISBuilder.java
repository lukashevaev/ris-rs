package com.ols.ruslan.neo;

import java.util.*;

public class RISBuilder {

    private final Map<String, String> fields;
    private final String recordType;

    public RISBuilder(Map<String, String> fields){
        this.fields = fields;
        TypeDefiner typeDefiner = new TypeDefiner(fields);
        this.recordType = typeDefiner.getRecordType();
        refactorFields();
    }

    public String getDigits(String field) {
        StringBuilder result = new StringBuilder();
        Set<Character> digits = new HashSet<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
        for (int i = 0; i < field.length(); i++){
            if (digits.contains(field.charAt(i))) result.append(field.charAt(i));
        }
        return result.toString();

    }

    private void refactorFields(){

        fields.remove("TY");
        fields.remove("AN");


        //check that volume matches a specific pattern (if exists)
        String volume = isExist("VL") ? fields.get("VL").toLowerCase() : "";

        if (!PatternFactory.volumePattern.matcher(volume).find()) fields.remove("VL");
        else fields.put("VL", getDigits(volume));
        //check that number of journal matches a specific pattern (if exists)
        if (isExist("M1")){
            if (!recordType.equals("@article")) fields.remove("number");
        }




        if (recordType.equals("@article") & fields.get("number") != null) {
            if (!PatternFactory.numberPattern.matcher(fields.get("number").toLowerCase()).find()) fields.remove("M1");
            else fields.remove("VL");
        }
        //pages
        String pages = isExist("EP") ? fields.get("EP").toLowerCase() : "";
        if (!PatternFactory.pagePattern.matcher(pages).find()) fields.remove("EP");
        else fields.put("EP", getDigits(fields.get("EP")));

        //edition
        if (isExist("ET")) fields.put("ET", getDigits(fields.get("ET")));

    }

    private boolean isExist(String fieldName){
        return fields.get(fieldName) != null;
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
        //deleting "," from last body-line and adding a closing "}"
        return risText.append("ER-").toString();
    }

}
