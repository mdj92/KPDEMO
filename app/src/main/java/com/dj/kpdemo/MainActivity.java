package com.dj.kpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dj.kpdemo.base.BaseActivity;
import com.dj.kpdemo.bean.SalesCPBean;
import com.dj.kpdemo.bean.UserCostBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.todayMoney)
    TextView todayMoney;
    @BindView(R.id.todayOrder)
    TextView todayOrder;
    @BindView(R.id.tlayout)
    LinearLayout tlayout;
    @BindView(R.id.uRecyclerView)
    RecyclerView uRecyclerView;
    List<UserCostBean> uData = new ArrayList<>();
    List<SalesCPBean> cData = new ArrayList<>();
    @BindView(R.id.ulayout)
    LinearLayout ulayout;
    @BindView(R.id.cRecyclerView)
    RecyclerView cRecyclerView;
    @BindView(R.id.clayout)
    LinearLayout clayout;

    private UserCostAdapter userCostAdapter;

    private SalesAdapter salesAdapter;
    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        uRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userCostAdapter = new UserCostAdapter(getCostData());
        uRecyclerView.setAdapter(userCostAdapter);

        cRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        salesAdapter = new SalesAdapter(getSalesData());
        cRecyclerView.setAdapter(salesAdapter);
    }

    @Override
    public void loadData() {

    }


    private List<UserCostBean> getCostData() {
        for (int i = 0; i < 5; i++) {
            UserCostBean bean = new UserCostBean();
            bean.setMoney(100 + i + "");
            bean.setName("张三" + i);
            uData.add(bean);
        }
        return uData;
    }


    private List<SalesCPBean> getSalesData() {
        for (int i = 0; i < 3; i++) {
            SalesCPBean bean = new SalesCPBean();
            bean.setNum(100 + i + "");
            cData.add(bean);
        }
        return cData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //用户消费排行
    private class UserCostAdapter extends BaseQuickAdapter<UserCostBean, BaseViewHolder> {

        public UserCostAdapter(@Nullable List<UserCostBean> data) {
            super(R.layout.item_usercost, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, UserCostBean item) {
            if (helper.getAdapterPosition() == 0 || helper.getAdapterPosition() == 1 || helper.getAdapterPosition() == 2) {
                helper.getView(R.id.position).setVisibility(View.VISIBLE);
                helper.setText(R.id.position, helper.getAdapterPosition() + 1 + "");
            } else {
                helper.getView(R.id.position).setVisibility(View.INVISIBLE);
            }
            helper.setText(R.id.userName, item.getName())
                    .setText(R.id.money, item.getMoney() + "元");
        }
    }


    //菜品销量排行
    private class SalesAdapter extends BaseQuickAdapter<SalesCPBean, BaseViewHolder> {

        public SalesAdapter(@Nullable List<SalesCPBean> data) {
            super(R.layout.item_salescp, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SalesCPBean item) {
            if (helper.getAdapterPosition() == 0 || helper.getAdapterPosition() == 1 || helper.getAdapterPosition() == 2) {
                helper.getView(R.id.position).setVisibility(View.VISIBLE);
                helper.setText(R.id.position, helper.getAdapterPosition() + 1 + "");
            } else {
                helper.getView(R.id.position).setVisibility(View.INVISIBLE);
            }
            helper.setText(R.id.num, item.getNum() + "单");
        }
    }


}
