package com.astora.web.enums;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 11.11.2017.
 */
public enum Leagues {

    PROFESSIONAL("enums.leagues.professional"),
    GOLD("enums.leagues.gold"),
    SILVER("enums.leagues.silver"),
    BRONZE("enums.leagues.bronze");

    private String code;

    Leagues(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
