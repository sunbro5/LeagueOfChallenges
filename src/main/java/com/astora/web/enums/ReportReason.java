package com.astora.web.enums;

import java.io.Serializable;

/**
 * @author <a href="mailto:mares.jan@o2.cz">Jan Mares</a>, 16.11.2017
 */
public enum ReportReason implements Serializable {

    //bad player
    USER_INSERT_INVALID_DATA("enums.reportReason.",2),
    USER_CHEATER("enums.reportReason.",3),
    USER_BAD_TO_PLAY_WITH("enums.reportReason.",1),
    USER_VERBAL_ABUSE("enums.reportReason.",3),
    //good player
    USER_GOOD_TO_PLAY_WITH("enums.reportReason.",1),
    USER_FRIENDLY("enums.reportReason.",1),
    USER_FAIR_PLAY("enums.reportReason.",1);

    private String code;
    private int value;


    ReportReason(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }
}
