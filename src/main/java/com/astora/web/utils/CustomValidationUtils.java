package com.astora.web.utils;

import java.util.Collection;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 05.11.2017.
 */
public class CustomValidationUtils {

    public static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public static boolean isEmpty(Collection o) {
        if (o == null) {
            return true;
        }
        if(o.isEmpty()){
            return true;
        }
        return false;
    }
}
