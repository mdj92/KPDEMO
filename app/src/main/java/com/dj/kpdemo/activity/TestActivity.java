package com.dj.kpdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dj.kpdemo.R;
import com.dj.kpdemo.base.BaseActivity;
import com.dj.kpdemo.bean.UserInfoBean;
import com.dj.kpdemo.util.Base64Utils;
import com.dj.kpdemo.util.MTimeUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import krt.wid.http.MCallBack;
import krt.wid.util.ParseJsonUtil;

import static com.dj.kpdemo.util.SpUtil.getTime;

public class TestActivity extends BaseActivity {


    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;

    MTimeUtils mTimeUtils;
    private int type;

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

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                getToken();
                break;
            case R.id.btn2:
                getData();
                break;
            case R.id.btn3:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn4: //周
                type=0;
                mTimeUtils=new MTimeUtils();
                showTimePicker();
                break;
            case R.id.btn5: //月
                type=1;
                mTimeUtils=new MTimeUtils();
                showTimePicker();
//                Log.w("MDateTime","月起始:"+ mTimeUtils.getMonthFirstDate(new Date())+"  结束："+mTimeUtils.getMonthLastDate(new Date()));
                break;
            case R.id.btn6: //年
                type=2;
                mTimeUtils =new MTimeUtils();
                showTimePicker();
//                Log.w("MDateTime","年起始:"+ mTimeUtils.getYearFirstDate(new Date())+"  结束："+mTimeUtils.getYearLastDate(new Date()));
                break;
            case R.id.btn7:
                showToast("当前日期："+spUtil.getDateString());
                break;
        }
    }

    private void showTimePicker() {
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (type==0){
                    Log.w("MDateTime","周起始:"+ mTimeUtils.getWeekfirstday(date)+"  结束："+mTimeUtils.getWeeklastday(date));
                }else if (type==1){
                    Log.w("MDateTime","月起始:"+ mTimeUtils.getMonthFirstDate(date)+"  结束："+mTimeUtils.getMonthLastDate(date));
                }else if (type==2){
                    Log.w("MDateTime","年起始:"+ mTimeUtils.getYearFirstDate(date)+"  结束："+mTimeUtils.getYearLastDate(date));
                }
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .setBgColor(ContextCompat.getColor(this,R.color.color_1c2134))
                .setBackgroundId(ContextCompat.getColor(this,R.color.color_1c2134))
                .setTextColorCenter(ContextCompat.getColor(this,R.color.white))
                .build();

        pvTime.show();
        pvTime.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {

            }
        });
    }


    private void getToken() {
        String headers = "";
        headers = "Basic " + Base64Utils.encodeToString("test_ysw:test_ysw!@#");
        OkGo.<String>post("https://dish.91yx.vip/oauth/token?grant_type=client_credentials")
                .headers("Authorization", headers)
                .execute(new MCallBack<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body().toString();
                        if (data != null) {
                            UserInfoBean bean = ParseJsonUtil.getBean(data, UserInfoBean.class);
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


    private void getData() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3lzdyIsImF1dGgiOiJST0xFX0FQUERJU0giLCJleHAiOjE1OTM2OTY1ODd9.0Eoilgp7Y7KA-Evv6WkfY9BrGSqWQ1gNm4mKtwvtrh_FCRU_1KaSorg8iKDykswU2jby3n9vH6HejDorXAwNuw";
        token = Base64Utils.encodeToString("test_ysw:test_ysw!@#");
        OkGo.<String>get("https://dish.91yx.vip/dish/api/analysis")
                .headers("Authorization", "Bearer " + token)
                .params("rangeType", "month")
                .params("fromDate", "2020-05-01")
                .params("toDate", "2020-06-01")
                .execute(new MCallBack<String>(this) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body().toString();
                        if (data != null) {
                            spUtil.setgetMainInfo(data);
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
