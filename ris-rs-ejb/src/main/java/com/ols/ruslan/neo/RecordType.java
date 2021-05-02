package com.ols.ruslan.neo;

/**
 *  Перечисление типов
 */

public enum RecordType {
    BOOK,
    CONF,
    JOUR,
    THES,
    ABST,
    ADVS,
    AGGR,
    ANCIENT,
    ART,
    BILL,
    BLOG,
    CASE,
    CHAP,
    CHART,
    CLSWK,
    COMP,
    CPAPER,
    CTLG,
    DATA,
    DBASE,
    DICT,
    EBOOK,
    ECHAP,
    EDBOOK,
    EJOUR,
    ELEC,
    ENCYC,
    EQUA,
    FIGURE,
    GEN,
    GOVDOC,
    GRANT,
    HEAR,
    ICOMM,
    INPR,
    JFULL,
    LEGAL,
    MANSCPT,
    MAP,
    MGZN,
    MPCT,
    MULTI,
    MUSIC,
    NEWS,
    PAMP,
    PAT,
    PCOMM,
    RPRT,
    SER,
    SLIDE,
    SOUND,
    STAND,
    STAT,
    UNBILL,
    UNPB,
    VIDEO;

    public static RecordType getType(String type) {
        return RecordType.valueOf(type);
    }
}
