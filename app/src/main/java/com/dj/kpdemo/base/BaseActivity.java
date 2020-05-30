package com.dj.kpdemo.base;


import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import com.dj.kpdemo.util.SpUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import krt.wid.base.MBaseActivity;
import krt.wid.http.MCallBack;
import krt.wid.util.MToast;
import krt.wid.util.MUpdate;
import krt.wid.util.MUtil;

/**
 * @author dj
 * @description
 * @Date 2020-05-09
 */
public abstract class BaseActivity extends MBaseActivity {

    private Unbinder mUnbinder;

    public SpUtil spUtil;







    @Override
    public void bindButterKnife() {
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    public void unbindButterknife() {
        mUnbinder.unbind();
    }

    @Override
    public void beforeBindLayout() {

    }

    @Override
    public void init() {
        spUtil = new SpUtil(this);

    }



    public void showToast(String info){
        MToast.showToast(getApplicationContext(),info);
    }

    public String getStr(String info){
        SpannableString ss=new SpannableString(info);
        ss.setSpan(new AbsoluteSizeSpan(80),0,ss.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLACK)
                ,0,ss.length()-1,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return ss.toString();
    }




}
