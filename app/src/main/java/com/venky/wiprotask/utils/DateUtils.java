package com.venky.wiprotask.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Venkatesh on 03,December,2019
 */
public class DateUtils {


    public static DateFormat dateFormat(){
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        return sdf;
    }

    public static String currentDate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
        String formattedDate = df.format(c);

        return formattedDate;
    }

    public static String convertDateFormat(String mDate){
        String  mNewDate ="";
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            Date date = sdfSource.parse(mDate);
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            mNewDate = sdfDestination.format(date);
        }catch (ParseException e){
            System.out.println("Parse Exception : " + e);
        }
        return  mNewDate;
    }


    public static String convertDateFormat2(String mDate){
        String  mNewDate ="";
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = sdfSource.parse(mDate);
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            mNewDate = sdfDestination.format(date);
        }catch (ParseException e){
            System.out.println("Parse Exception : " + e);
        }
        return  mNewDate;
    }

    public static String convertDateFormat3(String mDate){
        String  mNewDate ="";
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
            Date date = sdfSource.parse(mDate);
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            mNewDate = sdfDestination.format(date);
        }catch (ParseException e){
            System.out.println("Parse Exception : " + e);
        }
        return  mNewDate;
    }


    public static String convertDateFormat4(String mDate){
        String  mNewDate ="";
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.ENGLISH);
            Date date = sdfSource.parse(mDate);
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.ENGLISH);
            mNewDate = sdfDestination.format(date);
        }catch (ParseException e){
            System.out.println("Parse Exception : " + e);
        }
        return  mNewDate;
    }

    public static String convertDateFormat5(String mDate){
        String  mNewDate ="";
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date date = sdfSource.parse(mDate);
            SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            mNewDate = sdfDestination.format(date);
        }catch (ParseException e){
            System.out.println("Parse Exception : " + e);
        }
        return  mNewDate;
    }

    public static String convertDateFormat6(String mDate){
        String  mNewDate ="";
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
            Date date = sdfSource.parse(mDate);
            SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            mNewDate = sdfDestination.format(date);
        }catch (ParseException e){
            System.out.println("Parse Exception : " + e);
        }
        return  mNewDate;
    }
}
