package com.snapp.fintech.util;

import com.github.mfathi91.time.PersianDate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CalendarUtil {

    public static PersianDate convertGeorgianDateToPersianDate(Date date) {

        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        return PersianDate.fromGregorian(localDate);
    }
}
