package com.dj.kpdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dj.kpdemo.R;
import com.dj.kpdemo.base.BaseActivity;
import com.dj.kpdemo.bean.UserInfoBean;
import com.dj.kpdemo.util.Base64Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import krt.wid.http.MCallBack;
import krt.wid.util.ParseJsonUtil;

public class TestActivity extends BaseActivity {


    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    public int bindLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                getToken();
                break;
            case R.id.btn2:
                getData();
                break;
            case R.id.btn3:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }


    private void getToken(){
        String headers="";
        headers="Basic " + Base64Utils.encodeToString("test_ysw:test_ysw!@#");
        OkGo.<String>post("https://dish.91yx.vip/oauth/token?grant_type=client_credentials")
                .headers("Authorization",headers)
                .execute(new MCallBack<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data= response.body().toString();
                        if (data != null){
                            UserInfoBean bean= ParseJsonUtil.getBean(data,UserInfoBean.class);
                            spUtil.setAccessToken(bean.getAccess_token());
                            spUtil.setUserInfo(data);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }


    private void getData(){
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3lzdyIsImF1dGgiOiJST0xFX0FQUERJU0giLCJleHAiOjE1OTM2OTY1ODd9.0Eoilgp7Y7KA-Evv6WkfY9BrGSqWQ1gNm4mKtwvtrh_FCRU_1KaSorg8iKDykswU2jby3n9vH6HejDorXAwNuw";
        token= Base64Utils.encodeToString("test_ysw:test_ysw!@#");
        OkGo.<String>get("https://dish.91yx.vip/dish/api/analysis")
                .headers("Authorization","Bearer "+token)
                .params("rangeType","month")
                .params("fromDate","2020-05-01")
                .params("toDate","2020-06-01")
                .execute(new MCallBack<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data= response.body().toString();
                        if (data != null){
                            spUtil.setgetMainInfo(data);
                        }
                    }
                });
    }
}
