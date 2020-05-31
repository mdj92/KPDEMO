package com.dj.kpdemo;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dj.kpdemo.base.BaseActivity;
import com.dj.kpdemo.bean.SalesCPBean;
import com.dj.kpdemo.bean.TimeBean;
import com.dj.kpdemo.bean.UserCostBean;
import com.dj.kpdemo.view.LineChartManagger;
import com.dj.kpdemo.view.PieChartEntity;
import com.dj.kpdemo.view.RecyclerViewDivider;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dj.kpdemo.util.SpUtil.getTime;

public class MainActivity extends BaseActivity {


    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.tjTypeName)
    TextView tjTypeName;
    @BindView(R.id.todayMoney)
    TextView todayMoney;
    @BindView(R.id.dTimeTV)
    TextView dTimeTV;
    @BindView(R.id.todayOrder)
    TextView todayOrder;
    @BindView(R.id.tlayout)
    LinearLayout tlayout;
    @BindView(R.id.uRecyclerView)
    RecyclerView uRecyclerView;
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
    @BindView(R.id.imgDate)
    ImageView imgDate;
    @BindView(R.id.imgType)
    ImageView imgType;

    private TimePickerView pvTime;

    private UserCostAdapter userCostAdapter; //用户消费
    private SalesAdapter salesAdapter; //菜品消费
    List<UserCostBean> uData = new ArrayList<>();
    List<SalesCPBean> cData = new ArrayList<>();

    private String dateTime=""; //选择的日期
    private int tType=0; //用于判断点击哪个三角
    private int dateType =0; //选择时间 日 周 月 类型 -默认日

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        dTimeTV.setText(spUtil.getDateString());
        uRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userCostAdapter = new UserCostAdapter(getCostData());
        uRecyclerView.setAdapter(userCostAdapter);

        cRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        salesAdapter = new SalesAdapter(getSalesData());
        cRecyclerView.setAdapter(salesAdapter);

        setPieChart(getPieData());
        setLineChartData();
    }

    @OnClick({R.id.layout1,R.id.layout2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.layout1:
                tType=1;
                startAnim(imgType,true);
                initDialog(getTimeData(),1);
//                showTimePicker();
                break;
            case R.id.layout2:
                tType=0;
                startAnim(imgDate,true);
                showTimePicker();
                break;

//                initDialog(getTimeData(),1);
//                break;
        }
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
            pieChartEntity.setLegendEnabled(true); //显示lab说明
            pieChartEntity.setPercentValues(true); //设置是否使用百分值
            pieChartEntity.setDrawEntryLabels(false); //否绘制条目标签
//            pieChartEntity.setDrawValues(true);
            pieChartEntity.setHoleEnabled(getResources().getColor(R.color.color_1c2134), 50f, 0, 10f);
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

    private List<TimeBean> getTimeData(){
        List<TimeBean> timeBeanList =new ArrayList<>();

        for (int i = 0; i < 3 ; i++) {
            TimeBean bean =new TimeBean();
            bean.setType(i);
            if (i==0){
                bean.setName("按日统计");
            }else if (i==1){
                bean.setName("按周统计");
            }else if (i==2){
                bean.setName("按月统计");
            }
            timeBeanList.add(bean);
        }
        return  timeBeanList;
    }

    private ArrayList<PieEntry> getPieData() {
        ArrayList<PieEntry> pData = new ArrayList<>();
        pData.add(new PieEntry(40, "支出"));
        pData.add(new PieEntry(60, "收入"));
        return pData;
    }

    private void setLineChartData() {

        List<String> mlist = new ArrayList<>();

        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            float x = (float) (i);
            float y = (float) (100 * i);
            entries.add(new Entry(i, y));
            mlist.add("02/"+(17 + i));
        }
        LineChartManagger lineChartManagger = new LineChartManagger(mLineChart, entries, this, mlist);
        lineChartManagger.setXLabelRotationAngle(0);
//        lineChartManagger.setLegend(false);
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


    private void showTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                dateTime = getTime(date, "yyyy-MM-dd");
                dTimeTV.setText(dateTime);
                //日 周 月
                if (dateType == 0){

                }else if (dateType == 1){
                   showToast("当前是当月第"+ spUtil.getWeek(date)+ "周");
                }else if (dateType == 2){
                    showToast("当前月份："+ spUtil.getMonth(date));
                }
//                initData();
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
                if (tType ==0){
                    startAnim(imgDate,false);
                }else if (tType ==1) {
                    startAnim(imgType,false);
                }
            }
        });
    }

    private void startAnim(ImageView img, boolean click) {
        if (click) {
            ObjectAnimator.ofFloat(img, "rotation", 0f, 180f).setDuration(300).start();
        } else {
            ObjectAnimator.ofFloat(img, "rotation", 180f, 0f).setDuration(300).start();
        }
    }

    private RecyclerView reaRecycler;
    private PopupWindow popupWindow;
    private ReasonAdapter rAdapter;
    private void initDialog(List<TimeBean> list, final int type) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_select, null);
        reaRecycler = view.findViewById(R.id.recycler);
        reaRecycler.setLayoutManager(new LinearLayoutManager(this));
        rAdapter = new ReasonAdapter(list);
        reaRecycler.setAdapter(rAdapter);
        reaRecycler.addItemDecoration(new RecyclerViewDivider(this, RecyclerViewDivider.VERTICAL_LIST, R.drawable.shape_divider));
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.color_1c2134));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                startAnim(imgType,false);
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });
        rAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TimeBean data = (TimeBean) adapter.getData().get(position);
                tjTypeName.setText(data.getName());
                dateType =data.getType();
                popupWindow.dismiss();
            }
        });
    }



    private class ReasonAdapter extends BaseQuickAdapter<TimeBean, BaseViewHolder> {


        public ReasonAdapter(@Nullable List<TimeBean> data) {
            super(R.layout.item_bottomsheet, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TimeBean item) {
            helper.setText(R.id.title, item.getName());
        }
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
