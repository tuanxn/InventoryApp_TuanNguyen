/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author tuanxn
 */
public class TimeFiles {
    public static Calendar stringToCalendar(String strDate) throws ParseException{
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       Date date = sdf. parse(strDate);
       Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
   } 
    
   public static String ConvertToGMT(Calendar time) throws ParseException{
       
       SimpleDateFormat gmtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
       return gmtFormat.format(time.getTime());
   }
   
   public static String ConvertToLocalTime(Calendar time) throws ParseException {
       SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       localFormat.setTimeZone(TimeZone.getDefault());
       return localFormat.format(time.getTime());
   }
    
}
