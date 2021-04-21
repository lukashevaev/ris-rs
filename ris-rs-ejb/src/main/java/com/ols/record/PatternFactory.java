package com.ols.record;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This class is used to create patterns to find the required fields and to check them for the correct format.
 */
public class PatternFactory {
    private static final Map<RecordType, Pattern> patternsForType = new HashMap<>();

    private PatternFactory(){
        patternsForType.put(RecordType.BOOK,

                        Pattern.compile("book")

        patternsForType.put(RecordType.CONF,

                        Pattern.compile("proceedings|" +
                                "of\\s*(a|the)\\s*conference|" +
                                "conference|" +
                                "proceedings\\s*of"));

        patternsForType.put(RecordType.JOUR,

                        Pattern.compile("журнал|journal"));

        patternsForType.put(RecordType.THES,

                        Pattern.compile("дис.*канд|дис.*маг"));


        patternsForType.put(RecordType.ABST,

                        Pattern.compile("abstract"));

        patternsForType.put(RecordType.ADVS,

                        Pattern.compile("audiovisual material"));

        patternsForType.put(RecordType.AGGR,

                        Pattern.compile("aggregated database"));

        patternsForType.put(RecordType.ANCIENT,

                        Pattern.compile("Ancient Text"));

        patternsForType.put(RecordType.ART,

                        Pattern.compile("Art Work"));

        patternsForType.put(RecordType.BILL,

                        Pattern.compile("Bill|законопроект"));

        patternsForType.put(RecordType.BLOG,

                        Pattern.compile("Blog"));

        patternsForType.put(RecordType.CASE,

                        Pattern.compile("Case"));

        patternsForType.put(RecordType.CHAP,

                        Pattern.compile("Book chapter"));

        patternsForType.put(RecordType.CHART,

                        Pattern.compile("Chart|диаграмма"));

        patternsForType.put(RecordType.CLSWK,

                        Pattern.compile("Classical Work"));

        patternsForType.put(RecordType.COMP,

                        Pattern.compile("Computer program|программа"));

        patternsForType.put(RecordType.CPAPER,

                        Pattern.compile("Conference paper"));

        patternsForType.put(RecordType.CTLG,

                        Pattern.compile("Catalog"));

        patternsForType.put(RecordType.DATA,

                        Pattern.compile("Data file"));

        patternsForType.put(RecordType.DBASE,

                        Pattern.compile("Online Database"));

        patternsForType.put(RecordType.DICT,

                        Pattern.compile("Dictionary"));

        patternsForType.put(RecordType.EBOOK,

                        Pattern.compile("Electronic Book"));

        patternsForType.put(RecordType.ECHAP,

                        Pattern.compile("Electronic Book Section"));

        patternsForType.put(RecordType.EDBOOK,

                        Pattern.compile("Edited Book"));

        patternsForType.put(RecordType.EJOUR,

                        Pattern.compile("Electronic Article");

        patternsForType.put(RecordType.ELEC,

                        Pattern.compile("Web Page"));

        patternsForType.put(RecordType.ENCYC,

                        Pattern.compile("encyclopaedia|энциклопедия|Encyclopedia"));

        patternsForType.put(RecordType.EQUA,

                        Pattern.compile("Equation|уравнение"));

        patternsForType.put(RecordType.FIGURE,

                        Pattern.compile("Figure|рисунок"));

        patternsForType.put(RecordType.GEN,

                        Pattern.compile("Figure"));

        patternsForType.put(RecordType.GOVDOC,

                        Pattern.compile("Government Document"));

        patternsForType.put(RecordType.GRANT,

                        Pattern.compile("Grant|грант"));

        patternsForType.put(RecordType.HEAR,

                        Pattern.compile("hearing"),
                        Pattern.compile("слушание")

        patternsForType.put(RecordType.ICOMM,

                        Pattern.compile("Internet Communication")

        patternsForType.put(RecordType.INPR,

                        Pattern.compile("In Press")

        patternsForType.put(RecordType.JFULL,

                        Pattern.compile("Journal (full)")

        patternsForType.put(RecordType.LEGAL,

                        Pattern.compile("Legal Rule or Regulation")

        patternsForType.put(RecordType.MANSCPT,

                        Pattern.compile("Manuscript"),
                        Pattern.compile("манускрипт")

        patternsForType.put(RecordType.MAP,

                        Pattern.compile("Map"),
                        Pattern.compile("карта")

        patternsForType.put(RecordType.MGZN,

                        Pattern.compile("Magazine article"),
                        Pattern.compile("статья"),
                        Pattern.compile("article"),
                        Pattern.compile("magazine")

        patternsForType.put(RecordType.MPCT,

                        Pattern.compile("Motion picture"),
                        Pattern.compile("кинофильм")

        patternsForType.put(RecordType.MULTI,

                        Pattern.compile("Online Multimedia")

        patternsForType.put(RecordType.MUSIC,

                        Pattern.compile("Music score")

        patternsForType.put(RecordType.NEWS,

                        Pattern.compile("Newspaper"),
                        Pattern.compile("газета")

        patternsForType.put(RecordType.PAMP,

                        Pattern.compile("Pamphlet"),
                        Pattern.compile("брошюра")

        patternsForType.put(RecordType.PAT,

                        Pattern.compile("Patent"),
                        Pattern.compile("патент")

        patternsForType.put(RecordType.PCOMM,

                        Pattern.compile("Personal communication")

        patternsForType.put(RecordType.RPRT,

                        Pattern.compile("Report"),
                        Pattern.compile("отчет")

        patternsForType.put(RecordType.SER,

                        Pattern.compile("Serial publication"),
                        Pattern.compile("серийное издание")

        patternsForType.put(RecordType.SLIDE,

                        Pattern.compile("Slide"),
                        Pattern.compile("слайд")

        patternsForType.put(RecordType.SOUND,

                        Pattern.compile("Sound recording"),
                        Pattern.compile("звукозапись"),
                        Pattern.compile("запись звука")

        patternsForType.put(RecordType.STAND,

                        Pattern.compile("Standard"),
                        Pattern.compile("стандарт")

        patternsForType.put(RecordType.STAT,

                        Pattern.compile("Statute"),
                        Pattern.compile("Statistics")

        patternsForType.put(RecordType.UNBILL,

                        Pattern.compile("Unenacted Bill"),
                        Pattern.compile("не принятый законопроект")

        patternsForType.put(RecordType.UNPB,

                        Pattern.compile("Unpublished work"),
                        Pattern.compile("неопубликован")

        patternsForType.put(RecordType.VIDEO,

                        Pattern.compile("Video recording"),
                        Pattern.compile("запись видео")

    }

    private static class PatternFactoryHolder {
        private static final PatternFactory instance = new PatternFactory();
    }

    public static PatternFactory getInstance(){
        return PatternFactoryHolder.instance;
    }
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
    public static final Pattern volumePattern = Pattern.compile("^((том|vol|chapter|[nтpч№#]|part|часть)\\.?\\s*[нn№#]?\\s*\\d*)");
    /**
     * For field "number"
     * check if field matches pattern like : "N. 15", "number 8", "№ 9"
     * in short it checks that field is the number of journal
     */
    public static final Pattern numberPattern = Pattern.compile("^(([#№n]|number)\\.?\\s*\\d*)");

    public static final Pattern pagePattern = Pattern.compile("\\d*\\s*(pages|[pсc]|стр|страниц)\\.?");

    public Map<RecordType, Pattern> getPatternsForType() {
        return patternsForType;
    }

}
