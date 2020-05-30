package com.dj.kpdemo.view;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.dj.kpdemo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;


import java.util.List;

public class LineChartManagger {

    public LineChart mLineChart;
    private XAxis xAxis;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private List<String> labels;
    private Context mContext;
    private List<Entry>entries;
    private List<Integer>chartColor;
    private List<String>fxlist;

    public LineChartManagger(LineChart lineChart, List<Entry>entries, List<String>lables
            , Context context , List<Integer>chartColor, List<String>fxlist) {
        this.mLineChart = lineChart;
        this.mContext=context;
        this.chartColor=chartColor;
        this.labels=lables;
        this.entries=entries;
        this.fxlist=fxlist;
        initLineChart();
    }

    public LineChartManagger(LineChart lineChart, List<Entry>entries, Context context , List<String>fxlist) {
        this.mLineChart = lineChart;
        this.mContext=context;
        this.chartColor=chartColor;
//        this.labels=lables;
        this.entries=entries;
        this.fxlist=fxlist;
        initLineChart();
    }

    //初始化
    private void initLineChart() {
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setTouchEnabled(false);
        mLineChart.setDrawGridBackground(false);
        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        // mLineChart.setScaleXEnabled(true);
        // mLineChart.setScaleYEnabled(true);
        // force pinch zoom along both axis
//        mLineChart.setPinchZoom(true);

//        mLineChart.fitScreen();//重置所有缩放和拖动
//        mLineChart.zoomToCenter(3.8f, 1f);
//        mLineChart.setMaxVisibleValueCount();

        //设置样式
        rightAxis = mLineChart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setAxisMinimum(0);
        rightAxis.setEnabled(false);

        leftAxis = mLineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false); //设置Y轴不显示对应线

        //设置x轴
        xAxis = mLineChart.getXAxis();

        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(11f);
        xAxis.setLabelCount(fxlist.size(),false);//设置步长
        xAxis.setAxisMinimum(0f); //设置x轴最小值从0开始
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
//        xAxis.setTypeface(Typeface.DEFAULT_BOLD);
        xAxis.setGranularity(1f);//禁止放大后x轴标签重绘
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return  String.valueOf(value) ;
//            }
//        });
        //设置每个tab的显示位置 获取图例
        Legend l = mLineChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setXEntrySpace(0f); //x轴的间距
        l.setYEntrySpace(0f);//y轴的间距
        l.setYOffset(0f);//图例的y偏移量
        l.setTextColor(Color.WHITE);
        setData();
    }

    public void setData(){
        xAxis.setValueFormatter(new IndexAxisValueFormatter(fxlist));
        LineDataSet lineDataSet = new LineDataSet(entries, "出勤走势");
        ContextCompat.getColor(mContext, R.color.color_35E6FD);
        lineDataSet.setColor(ContextCompat.getColor(mContext,R.color.color_35E6FD));//线条颜色
        lineDataSet.setCircleColor(ContextCompat.getColor(mContext,R.color.color_35E6FD));//圆点颜色
        lineDataSet.setLineWidth(1f);//线条宽度
        lineDataSet.setCircleRadius(2);
        lineDataSet.setDrawFilled(true); //设置阴影部分

        LineData lineData = new LineData(lineDataSet);
        //是否绘制线条上的文字
        lineData.setDrawValues(false);
        mLineChart.setData(lineData);
        mLineChart.invalidate(); // refresh
    }

    /**
     * 设置X轴Lable倾斜角度
     * @param f 角度
     */
    public void setXLabelRotationAngle(float f) {
        xAxis.setLabelRotationAngle(f);
    }



}
