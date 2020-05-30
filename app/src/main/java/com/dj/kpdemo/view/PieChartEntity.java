package com.dj.kpdemo.view;



import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼状图
 * Created by jin
 */

public class PieChartEntity {

    private PieChart mChart;
    private List<PieEntry> mEntries;
    private String[] labels;
//    private int[] mPieColors;
    private ArrayList<Integer> mPieColors = new ArrayList<>();
    private int mValueColor;
    private float mTextSize;
    private PieDataSet.ValuePosition mXValuePosition;
    private PieDataSet.ValuePosition mYValuePosition;

    private PieDataSet dataSet;


    public PieChartEntity(PieChart chart, List<PieEntry> entries, String[] labels,
                          int []chartColor, float textSize, int valueColor, PieDataSet.ValuePosition xvaluePosition, PieDataSet.ValuePosition yvaluePosition) {
        this.mChart = chart;
        this.mEntries = entries;
        this.labels= labels;
//        this.mPieColors = chartColor;
        this.mTextSize= textSize;
        this.mValueColor = valueColor;
        this.mXValuePosition = xvaluePosition;
        this.mYValuePosition = yvaluePosition;
        initPieChart();
    }

    public PieChartEntity(PieChart chart, List<PieEntry> entries, String[] labels,
                          int []chartColor, float textSize, int valueColor) {
        this(chart, entries, labels, chartColor, textSize, valueColor, PieDataSet.ValuePosition.INSIDE_SLICE, PieDataSet.ValuePosition.INSIDE_SLICE);
    }

    public PieChartEntity(PieChart chart, List<PieEntry> entries, String[] labels,
                          ArrayList<Integer> chartColor, float textSize, int valueColor, PieDataSet.ValuePosition xvaluePosition, PieDataSet.ValuePosition yvaluePosition) {
        this.mChart = chart;
        this.mEntries = entries;
        this.labels= labels;
        this.mPieColors = chartColor;
        this.mTextSize= textSize;
        this.mValueColor = valueColor;
        this.mXValuePosition = xvaluePosition;
        this.mYValuePosition = yvaluePosition;
        initPieChart();
    }

    public PieChartEntity(PieChart chart, List<PieEntry> entries, String[] labels,
                          ArrayList<Integer>chartColor, float textSize, int valueColor) {
        this(chart, entries, labels, chartColor, textSize, valueColor, PieDataSet.ValuePosition.INSIDE_SLICE, PieDataSet.ValuePosition.INSIDE_SLICE);
    }

    private void initPieChart() {
        mChart.getDescription().setEnabled(false);//取消右下角描述
        //设置描述
        mChart.setExtraOffsets(5, 5, 5, 5);//设置饼状图距离上下左右的偏移量
        mChart.setDragDecelerationFrictionCoef(0.95f);//设置阻尼系数
        //图标的背景色
//        mChart.setBackgroundColor(Color.TRANSPARENT);

        //设置中间文件
        mChart.setDrawCenterText(false);
        // 触摸旋转
        mChart.setRotationAngle(0);//设置饼状图旋转的角度
        // enable rotation of the chart by touch
        // 触摸旋转
        mChart.setRotationEnabled(false);
        mChart.setHighlightPerTapEnabled(false);

        mChart.setDrawEntryLabels(true); //设置是否绘制Label
        setChartData();
        mChart.animateY(100, Easing.EasingOption.EaseInOutQuad);

        //设置每个tab的显示位置 获取图例
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(0f); //x轴的间距
        l.setYEntrySpace(1f);//y轴的间距
        l.setYOffset(0f);//图例的y偏移量
//        l.setXOffset(10f);  //图例x的偏移量
//        l.setTextColor(Color.parseColor("#a1a1a1")); //图例文字的颜色
//        l.setTextSize(13);  //图例文字的大小

        // entry label styling
        mChart.setDrawEntryLabels(true); //设置是否绘制Label
        mChart.setEntryLabelColor(mValueColor);//设置绘制Label的颜色
        mChart.setEntryLabelTextSize(mTextSize);//设置绘制Label的字体大小
//        mChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD); //描述文字的样式

    }


    private void setChartData() {
        dataSet = new PieDataSet(mEntries, "");
        dataSet.setSliceSpace(0f); //设置饼图占比之间的间隔
        dataSet.setSelectionShift(5f);
//        dataSet.setEntryLabelsColor(mValueColor);
        //填充每个区域的颜色
        dataSet.setColors(mPieColors);
//        //        文字的大小
//        dataSet.setValueTextSize(14);
////        文字的颜色
//        dataSet.setValueTextColor(Color.RED);
////        文字的样式
//        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);

//        // 当值位置为外边线时，表示线的颜色。
//        dataSet.setValueLineColor(Color.parseColor("#a1a1a1"));
////        设置Y值的位置是在圆内还是圆外
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
////        设置Y轴描述线和填充区域的颜色一致
//        dataSet.setUsingSliceColorAsValueLineColor(false);

        //dataSet.setSelectionShift(0f);
        dataSet.setYValuePosition(mYValuePosition); //数字显示在内外
        dataSet.setXValuePosition(mXValuePosition); //标签显示在内外
        dataSet.setValueLineColor(mValueColor);
//        dataSet.setValueTextSize(10f);

        dataSet.setSelectionShift(10f);
//        当值位置为外边线时，表示线的前半段长度。
        dataSet.setValueLinePart1Length(0.4f);
        //        当值位置为外边线时，表示线的后半段长度。
        dataSet.setValueLinePart2Length(0.4f);

        //      当ValuePosits为OutsiDice时，指示偏移为切片大小的百分比
//        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLineColor(mValueColor);

//        //设置饼状Item被选中时变化的距离
//        dataSet.setSelectionShift(5f);

        //填充数据
        PieData data = new PieData(dataSet);
//        格式化显示的数据为%百分比
        data.setValueFormatter(new PercentFormatter());
//        文字的大小
        data.setValueTextSize(mTextSize);
//        文字的颜色
        data.setValueTextColor(mValueColor);

        mChart.setData(data);
        // undo all highlights
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    /**
     * 是否绘制label
     * @param b
     */
    public void setDrawEntryLabels (boolean b) {
        mChart.setDrawEntryLabels(b);
    }

    public void setValueLinePart1Length (float f) {
        dataSet.setValueLinePart1Length(f);
    }
    public void setValueLinePart2Length (float f) {
        dataSet.setValueLinePart2Length(f);
    }

    /**
     * 中心圆是否可见
     * @param holeColor 中心圆颜色
     * @param holeRadius 半径
     * @param transRadius 透明圆半径
     */
    public void setHoleEnabled (int holeColor, float holeRadius, float transRadius) {
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(holeColor);
//        mChart.setTransparentCircleColor(transColor);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(holeRadius);
        mChart.setTransparentCircleRadius(transRadius);
    }

    /**
     * 中心圆是否可见
     * @param holeColor 中心圆颜色
     * @param holeRadius 半径
     * @param transColor 透明圆颜色
     * @param transRadius 透明圆半径
     */
    public void setHoleEnabled (int holeColor, float holeRadius, int transColor ,float transRadius) {
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(holeColor);
        mChart.setTransparentCircleColor(holeColor);//设置半透明圆圈的颜色
        mChart.setTransparentCircleAlpha(transColor);//设置半透明圆圈的透明度
        mChart.setHoleRadius(holeRadius);
        mChart.setTransparentCircleRadius(transRadius);
    }

    /**
     * 饼图类型 -设置为圆环
     * @param b
//     * @param f 设置中间洞的大小
     */
    public void setDrawHoleEnabled(boolean b){
        mChart.setDrawHoleEnabled(b);
//        mChart.setHoleRadius(f);
    }

    /**
     * 设置百分比是否显示
     * @param f
     */
    public void setDrawValues(boolean f){
        dataSet.setDrawValues(f);
    }

    /**
     * 设置饼图占比之间的间隔
     * @param f
     */
    public void setSliceSpace(float f){
        dataSet.setSliceSpace(f);
    }

    /**
     * <p>说明文字是否可见</p>
     * @param enabled true 可见,默认可见
     */
    public void setLegendEnabled(boolean enabled) {
        mChart.getLegend().setEnabled(enabled);
        mChart.invalidate();
    }

    /**
     * 显示成百分比
     * @param showPercent
     */
    public void setPercentValues (boolean showPercent) {
        mChart.setUsePercentValues(showPercent);
    }
}
