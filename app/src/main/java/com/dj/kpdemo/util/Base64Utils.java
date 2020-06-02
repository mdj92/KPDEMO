package com.dj.kpdemo.util;


import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @author dj
 * @description
 * @Date 2020/6/2
 */
public class Base64Utils {
    /**
     * 字符Base64加密
     * @param str
     * @return
     */
    public static String encodeToString(String str){
        String encodeString="";
        try {
            encodeString =Base64.encodeToString(str.getBytes("UTF-8"), Base64.NO_WRAP);
            return encodeString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeString;
    }
    /**
     * 字符Base64解密
     * @param str
     * @return
     */
    public static String decodeToString(String str){
        try {
            return new String(Base64.decode(str.getBytes("UTF-8"), Base64.DEFAULT));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
