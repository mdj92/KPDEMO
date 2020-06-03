package com.dj.kpdemo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author dj
 * @description
 * @Date 2020-06-03
 */
public class MTimeUtils {
    DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd"); //HH表示24小时制；


    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public  String getFetureDate(Date date,int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
//        Date today = calendar.getTime();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = dFormat.format(calendar.getTime());
        return result;
    }


    /**
     * 获取当前日期
     * @return
     */
    public String getDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 当前第几周
     * @param date
     * @return
     */
    public int getWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        //设置星期一为一周开始的第一天 ,默认是周天为一周的第一天
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week;
    }

    /**
     * 获取当前月份
     * @param date
     * @return
     */
    public int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }

    /**
     * 获取输入日期所在周的周一日期
     * @param day
     * @return
     */
    public  String getWeekfirstday(Date day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        int weekday = cal.get(Calendar.DAY_OF_WEEK);//当前日期是周几
        if(weekday == 1){//如果是周日,减一
            cal.add(Calendar.DAY_OF_MONTH,-1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//周一
        int dy = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE,cal.getFirstDayOfWeek() - dy);
        return dFormat.format(cal.getTime());
    }

    /**
     * 获取输入日期所在周的周日日期
     * @param day
     * @return
     */
    public String getWeeklastday(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        int weekday = cal.get(Calendar.DAY_OF_WEEK);//当前日期是周几
        if(weekday != 1){
            cal.add(Calendar.DATE, 8 - weekday);
        }
        return dFormat.format(cal.getTime());
    }


    /**
     * 获取输入日期当月的第一天日期
     * @param date
     * @return
     */
    public String getMonthFirstDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return dFormat.format(calendar.getTime());
    }

    /**
     * 获取输入日期当月的最后一天日期
     * @param date
     * @return
     */
    public String getMonthLastDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
//        String lastDay = df.format(calendar.getTime());
        return  dFormat.format(calendar.getTime());
    }

    /**
     * 获取输入日期年的第一天日期
     * @param date
     * @return
     */
    public String getYearFirstDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final int last = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, last);
        return  dFormat.format(calendar.getTime());
    }

    /**
     * 获取输入日期年的最后一天日期
     * @param date
     * @return
     */
    public String getYearLastDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final int last = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, last);
        return  dFormat.format(calendar.getTime());
    }





}
