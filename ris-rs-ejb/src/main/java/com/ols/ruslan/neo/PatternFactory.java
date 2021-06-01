package com.ols.ruslan.neo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Данный класс используется для создания паттернов для того,
 * чтобы найти тип записи, если он указан явно, а также для того,
 * чтобы найти случаи нетипичной записи полей.
 */
public class PatternFactory {
    private static final Map<RecordType, Pattern> patternsForType = new HashMap<>();

    PatternFactory(){
        patternsForType.put(RecordType.BOOK,

                        Pattern.compile("энциклопедия|encyclopa[e]?dia|сборник|собрание|сочинения|работы|книга|book|" +
                                "(в|in)\\s\\d+-?х?\\s(т|ч|vols)\\.?$")); // Пример: сборник в 3 томах

        patternsForType.put(RecordType.CONF,
                        Pattern.compile("proceedings|" +
                                "of\\s*(a|the)\\s*conference|" +
                                "conference|proceedings\\s*of|" +
                                "of\\s*(a|the).*\\s*colloquium|" +
                                "of\\s*symposia|symposium|" +
                                "of\\s*(a|the)\\s*congress"
                        ));
        patternsForType.put(RecordType.JOUR,

                        Pattern.compile("журнал|journal|газета|№|номер"));

        patternsForType.put(RecordType.THES,

                        Pattern.compile("дис.*канд|дис.*маг|" +
                                "выпускная квалификационная работа магистра|выпускная квалификационная работа бакалавра|" +
                                "(master(s)?)?\\s*thesis\\s*((of)?\\smaster)?"+
                                "(bachelor(s)?)?\\s*thesis\\s*((of)?\\sbachelor)?"
                        ));


        patternsForType.put(RecordType.ABST,

                        Pattern.compile("abstract|аннотация"));

        patternsForType.put(RecordType.ADVS,

                        Pattern.compile("audiovisual material|аудиовизуальные материалы"));

        patternsForType.put(RecordType.AGGR,

                        Pattern.compile("aggregated database|агрегированная база данных"));

        patternsForType.put(RecordType.ANCIENT,

                        Pattern.compile("Ancient Text|ancient"));

        patternsForType.put(RecordType.ART,

                        Pattern.compile("Art Work|art"));

        patternsForType.put(RecordType.BILL,

                        Pattern.compile("Bill|законопроект|билл"));

        patternsForType.put(RecordType.BLOG,

                        Pattern.compile("Blog|блог"));

        patternsForType.put(RecordType.CASE,

                        Pattern.compile("Case"));

        patternsForType.put(RecordType.CHAP,

                        Pattern.compile("Book chapter|глава"));

        patternsForType.put(RecordType.CHART,

                        Pattern.compile("Chart|диаграмма"));

        patternsForType.put(RecordType.CLSWK,

                        Pattern.compile("Classical Work|классическая работа"));

        patternsForType.put(RecordType.COMP,

                        Pattern.compile("Computer program|программа|компьютерная программа"));

        patternsForType.put(RecordType.CPAPER,

                        Pattern.compile("Conference paper|документ конференции|материал конференции"));

        patternsForType.put(RecordType.CTLG,

                        Pattern.compile("Catalog|каталог"));

        patternsForType.put(RecordType.DATA,

                        Pattern.compile("Data file|файл|файл данных"));

        patternsForType.put(RecordType.DBASE,

                        Pattern.compile("Online Database|база данных|бд|онлайн база данных"));

        patternsForType.put(RecordType.DICT,

                        Pattern.compile("Dictionary|словарь"));

        patternsForType.put(RecordType.EBOOK,

                        Pattern.compile("Electronic Book|электронная книга"));

        patternsForType.put(RecordType.ECHAP,

                        Pattern.compile("Electronic Book Section|раздел электронной книги|раздел"));

        patternsForType.put(RecordType.EDBOOK,

                        Pattern.compile("Edited Book|отредактированная книга"));

        patternsForType.put(RecordType.EJOUR,

                        Pattern.compile("Electronic Article|электронная статья"));

        patternsForType.put(RecordType.ELEC,

                        Pattern.compile("Web Page|веб-страница"));

        patternsForType.put(RecordType.ENCYC,

                        Pattern.compile("encyclopaedia|энциклопедия|Encyclopedia"));

        patternsForType.put(RecordType.EQUA,

                        Pattern.compile("Equation|уравнение"));

        patternsForType.put(RecordType.FIGURE,

                        Pattern.compile("Figure|рисунок"));

        patternsForType.put(RecordType.GEN,

                        Pattern.compile("Figure|общий"));

        patternsForType.put(RecordType.GOVDOC,

                        Pattern.compile("Government Document|правительственный документ"));

        patternsForType.put(RecordType.GRANT,

                        Pattern.compile("Grant|грант"));

        patternsForType.put(RecordType.HEAR,

                        Pattern.compile("hearing|слушание"));

        patternsForType.put(RecordType.ICOMM,

                        Pattern.compile("Internet Communication|интернет коммуникация"));

        patternsForType.put(RecordType.INPR,

                        Pattern.compile("In Press|в прессе"));

        patternsForType.put(RecordType.JFULL,

                        Pattern.compile("Journal (full)|полный журнал|журнал полный| журнал (полный)"));

        patternsForType.put(RecordType.LEGAL,

                        Pattern.compile("Legal Rule or Regulation|правовые нормы|правила"));

        patternsForType.put(RecordType.MANSCPT,

                        Pattern.compile("Manuscript|манускрипт|рукопись"));

        patternsForType.put(RecordType.MAP,

                        Pattern.compile("Map|карта"));

        patternsForType.put(RecordType.MGZN,

                        Pattern.compile("Magazine article|magazine|статья в журнале|статья"));

        patternsForType.put(RecordType.MPCT,

                        Pattern.compile("Motion picture|кинофильм|фильм"));

        patternsForType.put(RecordType.MULTI,

                        Pattern.compile("Online Multimedia|мультфильм"));

        patternsForType.put(RecordType.MUSIC,

                        Pattern.compile("Music score|музыкальная партитура"));

        patternsForType.put(RecordType.NEWS,

                        Pattern.compile("Newspaper|газета"));

        patternsForType.put(RecordType.PAMP,

                        Pattern.compile("Pamphlet|брошюра"));

        patternsForType.put(RecordType.PAT,

                        Pattern.compile("Patent|патент"));

        patternsForType.put(RecordType.PCOMM,

                        Pattern.compile("Personal communication|сообщение"));

        patternsForType.put(RecordType.RPRT,

                        Pattern.compile("Report|отчет"));

        patternsForType.put(RecordType.SER,

                        Pattern.compile("Serial publication|серийное издание|серийная публикация"));

        patternsForType.put(RecordType.SLIDE,

                        Pattern.compile("Slide|слайд"));

        patternsForType.put(RecordType.SOUND,

                        Pattern.compile("Sound recording|звукозапись|запись звука|звук"));

        patternsForType.put(RecordType.STAND,

                        Pattern.compile("Standard|стандарт"));

        patternsForType.put(RecordType.STAT,

                        Pattern.compile("Statute|Statistics|устав"));

        patternsForType.put(RecordType.UNBILL,

                        Pattern.compile("Unenacted Bill|не принятый законопроект|не принятый законопроект"));

        patternsForType.put(RecordType.UNPB,

                        Pattern.compile("Unpublished work|неопубликован|неопубликованная"));

        patternsForType.put(RecordType.VIDEO,

                        Pattern.compile("Video recording|запись видео|видеозапись|видео запись"));
    }

    private static class PatternFactoryHolder {
        private static final PatternFactory instance = new PatternFactory();
    }

    public static PatternFactory getInstance(){
        return PatternFactoryHolder.instance;
    }

    /** Для поля "pages"
     * Если поле совпадает с паттерном "digits-digits"
     * Например "10-20", "345-466"
     */
    public static final Pattern pagesPattern = Pattern.compile("\\D*\\d*-\\d*");

    /**
     * Для поля "volume"
     * Если поле совпадает с паттерном : "chapter 3", "#5", "№ 9", "том 8", "vol № 12"
     * Проверка, что поле является томом или главой
     */
    public static final Pattern volumePattern = Pattern.compile("^((том|vol|chapter|[nтpч№#]|part|часть)\\.?\\s*[нn№#]?\\s*\\d*)");

    /**
     * Для поля "number"
     * Если поле совпадает с паттерном : "N. 15", "number 8", "№ 9"
     * Проверка, что поле является номером журнала
     */
    public static final Pattern numberPattern = Pattern.compile("^(([#№n]|number)\\.?\\s*\\d*)");

    /** Для поля "pages"
     * Если поле совпадает с паттерном "digits"
     * Например "10 стр", "345 pages"
     */
    public static final Pattern pagePattern = Pattern.compile("\\d*\\s*(pages|[pсc]|стр|страниц)\\.?");

    public Map<RecordType, Pattern> getPatternsForType() {
        return patternsForType;
    }

    public static final Pattern notEmptyFieldPattern = Pattern.compile("[a-zA-Zа-яА-Я0-9]");

    public static final Pattern journalPattern = Pattern.compile("журнал|journal");

}
