package com.jtelecom.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class DateConstructorUtil {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.ENGLISH);

    public static String getOrderDate() {
        Date date = new Date();

        return formatter.format(date);
    }

    public static String getPlusDayDate(Integer plusDays) {
        Date currentDate = new Date();
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        localDateTime = localDateTime.plusDays(plusDays);
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());

        return formatter.format(currentDatePlusOneDay);
    }

    public static boolean compareDates(String loyaltyDueDate) throws ParseException {
        Date current = new Date();
        Date loyaltyDate = formatter.parse(loyaltyDueDate);

        return current.after(loyaltyDate);
    }
}
