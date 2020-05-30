package com.dj.kpdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dj.kpdemo.base.BaseActivity;
import com.dj.kpdemo.bean.SalesCPBean;
import com.dj.kpdemo.bean.UserCostBean;
import com.dj.kpdemo.view.LineChartManagger;
import com.dj.kpdemo.view.PieChartEntity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;
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
    @BindView(R.id.mPieChart)
    PieChart mPieChart;
    @BindView(R.id.mLineChart)
    LineChart mLineChart;

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

        setPieChart(getPieData());
    }

    @Override
    public void loadData() {

    }

    private void setPieChart(ArrayList<PieEntry> entries1) {
        PieDataSet dataSet = new PieDataSet(entries1, "");
        dataSet.setSliceSpace(0);
        dataSet.setSelectionShift(5f);
        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(this, R.color.color_f49b50));
        colors.add(ContextCompat.getColor(this, R.color.color_34bdec));
        dataSet.setColors(colors);
        if (entries1.size() > 0) {
            PieChartEntity pieChartEntity = new PieChartEntity(mPieChart, entries1, new String[]{"", ""}, colors, 11f, Color.WHITE, PieDataSet.ValuePosition.OUTSIDE_SLICE, PieDataSet.ValuePosition.OUTSIDE_SLICE);
            pieChartEntity.setLegendEnabled(false); //显示lab说明
            pieChartEntity.setPercentValues(true);
            pieChartEntity.setDrawEntryLabels(false);
//            pieChartEntity.setDrawValues(true);
            pieChartEntity.setHoleEnabled(getResources().getColor(R.color.color_142769), 50f, 110, 10f);
        }
    }

    private void initLineChart() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            float x = (float) (i);
            float y = 0;
            entries.add(new Entry(i, y));
        }
//        LineChartManagger lineChartManagger = new LineChartManagger(mLineChart, entries, this, Arrays.asList(months));
//        lineChartManagger.setXLabelRotationAngle(-80f);
    }


    private ArrayList<PieEntry> getPieData() {
        ArrayList<PieEntry> pData = new ArrayList<>();
        pData.add(new PieEntry(80, "移动支付"));
        pData.add(new PieEntry(20, "现金支付"));
        return pData;
    }

//    private void setLineChartData(InMBean inMBean) {
//        if (inMBean.getMon() ==null) return;
//        List<String> mlist = inMBean.getMon();
//        ArrayList<Entry> entries = new ArrayList<>();
//        for (int i = 0; i < inMBean.getMon().size(); i++) {
//            float x = (float) (i);
//            float y = (float) (inMBean.getCql().get(i));
//            entries.add(new Entry(i, y));
//        }
//        LineChartManagger lineChartManagger = new LineChartManagger(mLineChart, entries, getActivity(), mlist);
//        lineChartManagger.setXLabelRotationAngle(-80f);
//    }


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
