package com.example.administrateur.sqlitedepts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrateur on 31/08/2017.
 */

public class Tools {
    public static Date strToDat(String value) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        return df.parse(value);
    }
    public static String datToStr(Date value) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        return df.format(value);
    }
    public static Date sqlToDat(String value) throws Exception {
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        return df.parse(value);
    }
    public static String datToSql(Date value) throws Exception {
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        return df.format(value);
    }
}
