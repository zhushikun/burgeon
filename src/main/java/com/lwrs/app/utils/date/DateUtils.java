package com.lwrs.app.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zsk on 17-7-18.
 */
public class DateUtils {

    // ISO 8601 format
    public static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    // Alternate ISO 8601 format without fractional seconds
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date parseDate(String pattern, String strDate) throws ParseException {
        return new SimpleDateFormat(pattern).parse(strDate);
    }

    public static String format(String pattern, Date strDate) {
        return new SimpleDateFormat(pattern).format(strDate);
    }


    public static String formatIso8601Date(Instant instant) {
        return getIso8601DateFormat().format(instant);
    }

    public static String formatAlternativeIso8601Date(Instant instant) {
        return getTimeFormat().format(instant);
    }
    /**
     * Parse a date string in the format of ISO 8601.
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Instant parseIso8601Date(String dateString) throws Exception {
        try {
            return getTimeFormat().parse(dateString, Instant::from);
        } catch (Exception e) {
            return getIso8601DateFormat().parse(dateString, Instant::from);
        }
    }

    public static DateTimeFormatter getDateFormat(String dateFormatter) {
        DateTimeFormatter df = DateTimeFormatter.
            ofPattern(dateFormatter).
            withLocale(Locale.SIMPLIFIED_CHINESE)
            .withZone(ZoneId.systemDefault());
        return df;
    }

    public static DateTimeFormatter getIso8601DateFormat() {
        DateTimeFormatter df = DateTimeFormatter.
            ofPattern(ISO8601_DATE_FORMAT).
            withLocale(Locale.SIMPLIFIED_CHINESE)
            .withZone(ZoneId.systemDefault());
        return df;
    }

    private static DateTimeFormatter getTimeFormat() {
        DateTimeFormatter df = DateTimeFormatter
            .ofPattern(TIME_FORMAT)
            .withLocale(Locale.SIMPLIFIED_CHINESE)
            .withZone(ZoneId.systemDefault());
        return df;
    }

    public static long getCurrentSystemTime() {
        return System.currentTimeMillis();
    }

}
