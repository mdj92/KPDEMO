package com.dj.kpdemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;


import com.dj.kpdemo.bean.MainInfoBean;
import com.dj.kpdemo.bean.UserInfoBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import krt.wid.util.ParseJsonUtil;

/**
 * @author Marcus
 * @package krt.wid.tour_gz.manager
 * @description sp存储类
 * @time 2017/11/20
 */

public class SpUtil {
    private static final String USER = "user";

    private static final String PUSH = "push";

    private static final String CITY = "city";

    private static final String CART = "cart";

    private static final String SEARCH = "search";

    private static final String BOTTOM = "bottom";



    private Context mContext;

    public SpUtil(Context context) {
        this.mContext = context;
    }

    /**
     * 保存token
     */
    public void setAccessToken(String token) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences(USER, Context.MODE_PRIVATE).edit();
        edit.putString("tokens", token).apply();
    }

    public String getAccessToken() {
        SharedPreferences edit = mContext.getSharedPreferences(USER, Context.MODE_PRIVATE);
        String bean = edit.getString("tokens", "");
        return bean;
    }



    /**
     * 保存首页信息
     */
    public void setgetMainInfo(String userInfo) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences(USER, Context.MODE_PRIVATE).edit();
        edit.putString("maininfo", userInfo).apply();
    }

    public MainInfoBean getMainInfo() {
        SharedPreferences edit = mContext.getSharedPreferences(USER, Context.MODE_PRIVATE);
        return  ParseJsonUtil.getBean(edit.getString("maininfo", ""),MainInfoBean.class);
    }


    /**
     * 保存token
     */
    public void setUserInfo(String userInfo) {
        SharedPreferences.Editor edit = mContext.getSharedPreferences(USER, Context.MODE_PRIVATE).edit();
        edit.putString("userinfo", userInfo).apply();
    }

    public UserInfoBean getUserInfo() {
        SharedPreferences edit = mContext.getSharedPreferences(USER, Context.MODE_PRIVATE);
        return  ParseJsonUtil.getBean(edit.getString("userinfo", ""),UserInfoBean.class);
    }



    public String getReplaceString(String info){
        String str=info;
        if (!"".equals(info)){
            str=str.replace("&nbsp;","");
            str=str.replace("<br />","");
            str=str.replace("<div>","");
            str=str.replace("</div>","");
        }
        return str;
    }

    /**
     * 通过WiFiManager获取mac地址
     * @return
     */
    public String getWifiMac() {
        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wi = wm.getConnectionInfo();

        if (wi == null || wi.getMacAddress() == null) {
            return null;
        }
        if ("02:00:00:00:00:00".equals(wi.getMacAddress().trim())) {
            return null;
        } else {
            return wi.getMacAddress().trim();
        }
    }

    /**
     * 格式化单位
     * 计算缓存的大小
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            // return size + "Byte";
            return "0K";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }


    public static String listToString(List<String> list, String s) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + s);
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }


    public String getJson( String fileName) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }

    /**
     * 获取当前日期
     * @return
     */
    public String getDateString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    //获取时间
    public static String getTime(Date date,String style){
        SimpleDateFormat format = new SimpleDateFormat(style);
        return format.format(date);
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public  String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    public String switchCreateTime(String createTime) {
        String formatStr2 = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//注意格式化的表达式
        try {
            Date time = format.parse(createTime);
            String date = time.toString();
            //将西方形式的日期字符串转换成java.util.Date对象
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.US);
            Date datetime = (Date) sdf.parse(date);
            //再转换成自己想要显示的格式
            formatStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr2;
    }

    public String getDate(Date createTime) {
        String formatDate = null;
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
        formatDate = dFormat.format(createTime);

        return formatDate;
    }

    //当月第几周
    public int getWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        //设置星期一为一周开始的第一天 ,默认是周天为一周的第一天
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week;
    }

    //获取当前日期是几月份
    public int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }

}
