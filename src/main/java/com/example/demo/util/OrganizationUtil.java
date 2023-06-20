package com.example.demo.util;

import com.example.demo.exception.CustomException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrganizationUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static Date fromStringToDate (String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (Exception e) {
            throw new CustomException("No se pudo pasar la fecha", 400);
        }
    }

    public static String fromDateToString (Date date){
        return DATE_FORMAT.format(date);
    }

}
