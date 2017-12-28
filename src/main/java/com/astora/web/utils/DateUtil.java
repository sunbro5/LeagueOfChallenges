package com.astora.web.utils;

import com.astora.web.exception.ServiceException;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 14.12.2017
 */
public class DateUtil {

    private static final String DATE_INPUT_FORMAT = "dd.MM.yyyy hh:mm";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_DAY = "HH:mm:ss";

    public static String getDateText(Date date) {
        Format formatter;
        if (date.before(getMidnight(new Date()))) {
            formatter = new SimpleDateFormat(DATE_FORMAT);
        } else {
            formatter = new SimpleDateFormat(DATE_FORMAT_DAY);
        }
        return formatter.format(date);
    }

    public static Date getMidnight(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Timestamp convertToTimestamp(String date) throws ServiceException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_INPUT_FORMAT);
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new ServiceException("Cant parse time: " + date,e);
        }
        return new Timestamp(parsedDate.getTime());
    }
}
