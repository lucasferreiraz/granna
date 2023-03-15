package io.lucasprojects.granna.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    
    public static String parse(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formatter.format(date);
    }

}
