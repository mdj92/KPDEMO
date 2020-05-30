package com.dj.kpdemo.constant;




/**
 * Created by Administrator on 2018/11/19.
 */

public class Constants {


    public static String BASE_URL = "";






    public static String getUrl(String method) {
        return BASE_URL + method;
    }

    public static String getUserUrl(String method){
        return BASE_URL + "user/" + method;
    }



}
