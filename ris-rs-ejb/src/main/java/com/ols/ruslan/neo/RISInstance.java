package com.ols.ruslan.neo;

import java.util.Map;

/**
 * Данный класс хранит в себе поля и дает к ним доступ
 */
public class RISInstance {
    private Map<String, String> fields;

    public RISInstance(Map<String, String> fields) {
        this.fields = fields;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public String getRecordType() {
        return fields.get("TY") != null ? fields.get("TY") : "";
    }

    public void setRecordType(String recordType) {
        this.fields.put("TY", recordType);
    }

    public String getConference() {
        return fields.get("conference");
    }

    public void setConference(String conference) {
        this.fields.put("conference", conference);
    }

    public String getData() {
        return fields.get("data");
    } //if Date - Y1

    public void setData(String data) {
        this.fields.put("data", data);
    }

    public String getUniversity() {
        return fields.get("university");
    }

    public void setUniversity(String university) {
        this.fields.put("university", university);
    }

    public String getAuthor() {
        return fields.get("AU") != null ? fields.get("AU") : "";
    }

    public void setAuthor(String author) {
        this.fields.put("AU", author);
    }

    public String getYear() {
        return fields.get("PY") != null ? fields.get("PY") : "";
    }

    public void setYear(String year) {
        this.fields.put("PY", year);
    }

    public String getPublisher() {
        return fields.get("PB") != null ? fields.get("PB") : "";
    }

    public void setPublisher(String publisher) {
        this.fields.put("PB", publisher);
    }

    public String getTitle() {
        return fields.get("T1") != null ? fields.get("T1") : "";
    }

    public void setTitle(String title) {
        this.fields.put("T1", title);
    }

    public String getLanguage() {
        return fields.get("LA") != null ? fields.get("LA") : "";
    }

    public void setLanguage(String language) {
        this.fields.put("LA", language);
    }

    public String getSchool() {
        return fields.get("school") != null ? fields.get("school") : "";
    }

    public void setSchool(String school) {
        this.fields.put("school", school);
    }

    public String getUrl() {
        return fields.get("url") != null ? fields.get("url") : "";
    }

    public void setUrl(String url) {
        this.fields.put("url", url);
    }

    public String getAddress() {
        return fields.get("PP") != null ? fields.get("PP") : "";
    }

    public void setAddress(String address) {
        this.fields.put("PP", address);
    }

    public String getEdition() {
        return fields.get("ET") != null ? fields.get("ET") : "";
    }

    public void setEdition(String edition) {
        this.fields.put("ET", edition);
    }

    public String getJournal() {
        return fields.get("JA") != null ? fields.get("JA") : "";
    }

    public void setJournal(String journal) {
        this.fields.put("JA", journal);
    }

    public String getNumber() {
        return fields.get("M1") != null ? fields.get("M1") : "";
    }

    public void setNumber(String number) {
        this.fields.put("M1", number);
    }

    public String getPages() {
        return fields.get("EP") != null ? fields.get("EP") : "";
    }

    public void setPages(String pages) {
        this.fields.put("EP", pages);
    }

    public String getVolume() {
        return fields.get("VL") != null ? fields.get("VL") : "";
    }

    public void setVolume(String volume) {
        this.fields.put("VL", volume);
    }

    public String getEditor() {
        return fields.get("ED") != null ? fields.get("ED") : "";
    }

    public void setEditor(String volume) {
        this.fields.put("ED", volume);
    }

    public void deleteYear() {
        this.fields.remove("PY");
    }

    public void deletePublisher() {
        this.fields.remove("PB");
    }

    public void deleteTitle() {
        this.fields.remove("T1");
    }

    public void deleteLanguage() {
        this.fields.remove("LA");
    }

    public void deleteSchool() {
        this.fields.remove("school");
    }

    public void deleteUrl() {
        this.fields.remove("url");
    }

    public void deleteAddress() {
        this.fields.remove("PP");
    }

    public void deleteEdition() {
        this.fields.remove("ET");
    }

    public void deleteJournal() {
        this.fields.remove("JA");
    }

    public void deleteNumber() {
        this.fields.remove("M1");
    }

    public void deletePages() {
        this.fields.remove("EP");
    }

    public void deleteVolume() {
        this.fields.remove("VL");
    }

    public void deleteAuthor() {
        this.fields.remove("AU");
    }

    public void deleteRecordType() {
        this.fields.remove("recordType");
    }

    public String getTitleChapter() {
        return fields.get("title_chapter") != null ? fields.get("title_chapter") : "";
    }

    public void setTitleChapter(String title_chapter) {
        this.fields.put("title_chapter", title_chapter);
    }





}

