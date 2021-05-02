package com.ols.ruslan.neo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This class is used to create patterns to find the required fields and to check them for the correct format.
 */
public class PatternFactory {
    private static final Map<RecordType, Pattern> patternsForType = new HashMap<>();

    private PatternFactory(){
        patternsForType.put(RecordType.BOOK,

                        Pattern.compile("энциклопедия|encyclopa[e]?dia|сборник|собрание|сочинения|работы|книга|book|" +
                                "((в|in)\\s\\d+-?х?\\s(т|ч|vols)\\.?)$")); // Пример: сборник в 3 томах

        patternsForType.put(RecordType.CONF,

                        Pattern.compile("proceedings|" +
                                "of\\s*(a|the)\\s*conference|" +
                                "conference|proceedings\\s*of|" +
                                "of\\s*(a|the).*\\s*colloquium|" +
                                "of\\s*symposia|symposium|" +
                                "of\\s*(a|the)\\s*congress"
                        ));

        patternsForType.put(RecordType.JOUR,

                        Pattern.compile("журнал|journal|статья|article"));

        patternsForType.put(RecordType.THES,

                        Pattern.compile("дис.*канд|дис.*маг|" +
                                "выпускная квалификационная работа магистра|" +
                                "(master(s)?)?\\s*thesis\\s*((of)?\\smaster)?"));


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

                        Pattern.compile("Electronic Article"));

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

                        Pattern.compile("hearing|слушание"));

        patternsForType.put(RecordType.ICOMM,

                        Pattern.compile("Internet Communication"));

        patternsForType.put(RecordType.INPR,

                        Pattern.compile("In Press"));

        patternsForType.put(RecordType.JFULL,

                        Pattern.compile("Journal (full)"));

        patternsForType.put(RecordType.LEGAL,

                        Pattern.compile("Legal Rule or Regulation"));

        patternsForType.put(RecordType.MANSCPT,

                        Pattern.compile("Manuscript|манускрипт"));

        patternsForType.put(RecordType.MAP,

                        Pattern.compile("Map|карта"));

        patternsForType.put(RecordType.MGZN,

                        Pattern.compile("Magazine article|статья|magazine"));

        patternsForType.put(RecordType.MPCT,

                        Pattern.compile("Motion picture|кинофильм"));

        patternsForType.put(RecordType.MULTI,

                        Pattern.compile("Online Multimedia"));

        patternsForType.put(RecordType.MUSIC,

                        Pattern.compile("Music score"));

        patternsForType.put(RecordType.NEWS,

                        Pattern.compile("Newspaper|газета"));

        patternsForType.put(RecordType.PAMP,

                        Pattern.compile("Pamphlet|брошюра"));

        patternsForType.put(RecordType.PAT,

                        Pattern.compile("Patent|патент"));

        patternsForType.put(RecordType.PCOMM,

                        Pattern.compile("Personal communication"));

        patternsForType.put(RecordType.RPRT,

                        Pattern.compile("Report|отчет"));

        patternsForType.put(RecordType.SER,

                        Pattern.compile("Serial publication|серийное издание"));

        patternsForType.put(RecordType.SLIDE,

                        Pattern.compile("Slide|слайд"));

        patternsForType.put(RecordType.SOUND,

                        Pattern.compile("Sound recording|звукозапись|запись звука"));

        patternsForType.put(RecordType.STAND,

                        Pattern.compile("Standard|стандарт"));

        patternsForType.put(RecordType.STAT,

                        Pattern.compile("Statute|Statistics"));

        patternsForType.put(RecordType.UNBILL,

                        Pattern.compile("Unenacted Bill|не принятый законопроект"));

        patternsForType.put(RecordType.UNPB,

                        Pattern.compile("Unpublished work|неопубликован"));

        patternsForType.put(RecordType.VIDEO,

                        Pattern.compile("Video recording|запись видео"));
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
