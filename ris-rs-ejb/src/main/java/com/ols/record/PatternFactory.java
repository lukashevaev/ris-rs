package com.ols.record;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to create patterns to find the required fields and to check them for the correct format.
 */
public class PatternFactory {

    private final Map<String, String> patternForTags = new LinkedHashMap<String, String>();
    private static final Map<String, Set<Pattern>> patternsForType = new HashMap<String, Set<Pattern>>();
    /** For field "pages"
     * check if field matches pattern "digits-digits"
     * for example "10-20", "345-466"
     */
    public static final Pattern pagesPattern = Pattern.compile("\\D*\\d*-\\d*");
    /**
     * For field "volume"
     * check if field matches pattern like : "chapter 3", "#5", "№ 9", "том 8", "vol № 12"
     * in short it checks that field contains volume or chapter of smth
     */
    public static final Pattern volumePattern = Pattern.compile("^(\\s*(том|vol|[nтpч№#])\\.?\\s*[нn№#]?\\s*\\d*)");
    /**
     * For field "number"
     * check if field matches pattern like : "N. 15", "number 8", "№ 9"
     * in short it checks that field is the number of journal
     */
    public static final Pattern numberPattern = Pattern.compile("^(\\s*([#№n]|number)\\.?\\s*\\d*)");

    public static final Pattern pagePattern = Pattern.compile("\\d*\\s*(pages|[pсc]|стр|страниц)\\.?");

    public static final Pattern chapterPattern = Pattern.compile("^(\\s*(часть|chapter|chap|ch|ч)\\.?\\s*[нn№#]?\\d*)");

    public static final Pattern digitPattern = Pattern.compile("\\d*");



    /**
     * This constructor has one parameter
     * @param xml-tree with tags and fields in string-format.
     * It creates patterns to find tags in xml-tree and uses it to get fields of current record
     * It put found fields to Map<String, String> like:
     *           fieldName1-fieldValue1
     *           fieldName2-fieldValue2
     *           and so on
     */
    public PatternFactory(String xml) {
        Pattern patternForFindingTags = Pattern.compile("<\\w*>");
        Matcher matcherForFindingTags = patternForFindingTags.matcher(xml);
        String foundFieldParameter = "";
        while (matcherForFindingTags.find()) {
            String foundPattern = matcherForFindingTags.group();
            StringBuilder currentPatternBuilder = new StringBuilder();
            currentPatternBuilder // creating a pattern like "<pattern>.*</pattern>"
                    .append(foundPattern)
                    .append(".*")
                    .append(foundPattern.replace("<", "</"));
                Pattern currentPattern = Pattern.compile(currentPatternBuilder.toString());
                Matcher currentMatcher = currentPattern.matcher(xml);
                if (currentMatcher.find()) foundFieldParameter = currentMatcher.group()
                                                        .replace(foundPattern, "")
                                                        .replace(foundPattern.replace("<", "</"), "");
                if (!foundFieldParameter.equals("")) {
                    //put parameter into map of found parameters
                    patternForTags.put(foundPattern.replace("<", "")
                                    .replace(">", ""),
                            foundFieldParameter);
                }
        }
    }

    public Map<String, String> getPatternForTags() {
        return patternForTags;
    }

    public static Map<String, Set<Pattern>> getPatternsForType() {
        return patternsForType;
    }


    /**
     * This methods creates patterns that define a type of current record in method "defineType()" of class TypeDefiner
     */
    public static void createPatternsForType() {
        patternsForType.put("BOOK",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("book")
                )));
        patternsForType.put("CONF",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("proceedings"),
                        Pattern.compile("of\\s*(a|the)\\s*conference"),
                        Pattern.compile("conference"),
                        Pattern.compile("proceedings\\s*of")
                )));
        patternsForType.put("JOUR",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("журнал"),
                        Pattern.compile("journal")
                )));
        patternsForType.put("THES",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("дис.*канд"),
                        Pattern.compile("дис.*маг")
                )));
        patternsForType.put("ABST",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Abstract")
                )));
        patternsForType.put("ADVS",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Audiovisual material")
                )));
        patternsForType.put("AGGR",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Aggregated Database")
                )));
        patternsForType.put("ANCIENT",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Ancient Text")
                )));
        patternsForType.put("ART",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Art Work")
                )));
        patternsForType.put("BILL",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Bill"),
                        Pattern.compile("законопроект")
                )));
        patternsForType.put("BLOG",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Blog")
                )));
        patternsForType.put("CASE",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Case")
                )));
        patternsForType.put("CHAP",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Book chapter")
                )));
        patternsForType.put("CHART",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Chart"),
                        Pattern.compile("диаграмма")
                )));
        patternsForType.put("CLSWK",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Classical Work")
                )));
        patternsForType.put("COMP",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Computer program"),
                        Pattern.compile("программа")
                )));
        patternsForType.put("CPAPER",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Conference paper")
                )));
        patternsForType.put("CTLG",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Catalog")
                )));
        patternsForType.put("DATA",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Data file")
                )));
        patternsForType.put("DBASE",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Online Database")
                )));
        patternsForType.put("DICT",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Dictionary")
                )));
        patternsForType.put("EBOOK",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Electronic Book")
                )));
        patternsForType.put("ECHAP",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Electronic Book Section")
                )));
        patternsForType.put("EDBOOK",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Edited Book")
                )));
        patternsForType.put("EJOUR",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Electronic Article")
                )));
        patternsForType.put("ELEC",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Web Page")
                )));
        patternsForType.put("ENCYC",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("encyclopaedia"),
                        Pattern.compile("энциклопедия"),
                        Pattern.compile("Encyclopedia")
                )));
        patternsForType.put("EQUA",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Equation"),
                        Pattern.compile("уравнение")
                )));
        patternsForType.put("FIGURE",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Figure"),
                        Pattern.compile("рисунок")
                )));
        patternsForType.put("GEN",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Figure")
                )));
        patternsForType.put("GOVDOC",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Government Document")
                )));
        patternsForType.put("GRANT",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Grant"),
                        Pattern.compile("грант")
                )));
        patternsForType.put("HEAR",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Hearing"),
                        Pattern.compile("слушание")
                )));
        patternsForType.put("ICOMM",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Internet Communication")
                )));
        patternsForType.put("INPR",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("In Press")
                )));
        patternsForType.put("JFULL",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Journal (full)")
                )));
        patternsForType.put("LEGAL",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Legal Rule or Regulation")
                )));
        patternsForType.put("MANSCPT",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Manuscript"),
                        Pattern.compile("манускрипт")
                )));
        patternsForType.put("MAP",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Map"),
                        Pattern.compile("карта")
                )));
        patternsForType.put("MGZN",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Magazine article"),
                        Pattern.compile("статья"),
                        Pattern.compile("article"),
                        Pattern.compile("magazine")
                )));
        patternsForType.put("MPCT",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Motion picture"),
                        Pattern.compile("кинофильм")
                )));
        patternsForType.put("MULTI",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Online Multimedia")
                )));
        patternsForType.put("MUSIC",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Music score")
                )));
        patternsForType.put("NEWS",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Newspaper"),
                        Pattern.compile("газета")
                )));
        patternsForType.put("PAMP",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Pamphlet"),
                        Pattern.compile("брошюра")
                )));
        patternsForType.put("PAT",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Patent"),
                        Pattern.compile("патент")
                )));
        patternsForType.put("PCOMM",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Personal communication")
                )));
        patternsForType.put("RPRT",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Report"),
                        Pattern.compile("отчет")
                )));
        patternsForType.put("SER",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Serial publication"),
                        Pattern.compile("серийное издание")
                )));
        patternsForType.put("SLIDE",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Slide"),
                        Pattern.compile("слайд")
                )));
        patternsForType.put("SOUND",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Sound recording"),
                        Pattern.compile("звукозапись"),
                        Pattern.compile("запись звука")
                )));
        patternsForType.put("STAND",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Standard"),
                        Pattern.compile("стандарт")
                )));
        patternsForType.put("STAT",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Statute"),
                        Pattern.compile("Statistics")
                )));
        patternsForType.put("UNBILL",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Unenacted Bill"),
                        Pattern.compile("не принятый законопроект")
                )));
        patternsForType.put("UNPB",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Unpublished work"),
                        Pattern.compile("неопубликован")
                )));
        patternsForType.put("VIDEO",
                new HashSet<>(Arrays.asList(
                        Pattern.compile("Video recording"),
                        Pattern.compile("запись видео")
                )));

    }
}
