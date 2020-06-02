package com.dj.kpdemo.bean;

/**
 * @author dj
 * @description
 * @Date 2020/5/30
 */
public class UserInfoBean {

    /**
     * access_token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3lzdyIsImF1dGgiOiJST0xFX0FQUERJU0giLCJleHAiOjE1OTM2OTY1ODd9.0Eoilgp7Y7KA-Evv6WkfY9BrGSqWQ1gNm4mKtwvtrh_FCRU_1KaSorg8iKDykswU2jby3n9vH6HejDorXAwNuw
     * token_type : bearer
     * expires_in : 2592000000
     */

    private String access_token;
    private String token_type;
    private long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
