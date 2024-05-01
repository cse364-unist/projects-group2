package com.group2.milestone2.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.cglib.core.Local;

public class DateHandler {

    public static LocalDate stringToLocalDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return LocalDate.parse(dateString, formatter);
    }

    public static String locatDateTimeToString(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
