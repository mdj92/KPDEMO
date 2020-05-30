package com.dj.kpdemo.base;

import android.view.View;

import com.dj.kpdemo.util.SpUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import krt.wid.base.MBaseFragment;


/**
 * @author dj
 * @description
 * @Date 2020-05-09
 */
public abstract class BaseFragment  extends MBaseFragment {

    private Unbinder mUnbinder;

    public SpUtil spUtil;

    @Override
    public void bindButterKnife(View view) {
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void unBindButterKnife() {
        mUnbinder.unbind();
    }

    @Override
    public void init() {
        spUtil = ((BaseActivity) mContext).spUtil;
    }


}
