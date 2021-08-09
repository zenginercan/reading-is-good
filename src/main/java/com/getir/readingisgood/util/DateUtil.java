package com.getir.readingisgood.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static Date dateStringToDate(String dateString) throws NullPointerException, IllegalArgumentException, ParseException, ParseException {
        String dateFormatWithMillisecond = "yyyy-MM-dd HH:mm:ss.SSS";
        DateFormat format = new SimpleDateFormat(dateFormatWithMillisecond);
        return format.parse(dateString);
    }
}
