package com.dj.kpdemo.base;

import android.content.Context;

import com.dj.kpdemo.R;
import com.dj.kpdemo.util.SpUtil;


import krt.wid.base.MApp;
import krt.wid.config.BaseModule;
import krt.wid.config.BaseModuleConfig;

/**
 * @author dj
 * @description
 * @Date 2020-05-09
 */
public class APP extends MApp {
    //日志TAG
    protected final String TAG = this.getClass().getSimpleName();

    private SpUtil tsp;

    //对外提供整个应用生命周期的Context
    private static Context instance;

    public static Context getNewContent() {
        return instance;
    }



    @Override
    protected void init() {
        BaseModule.initialize(BaseModuleConfig.newBuilder()
                .setLoadingViewColor(R.color.color_1e6afe).build());

        instance = this;
        tsp=new SpUtil(getBaseContext());
    }
}
