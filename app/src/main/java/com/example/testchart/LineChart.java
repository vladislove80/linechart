package com.example.testchart;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.example.testchart.chart.charts.LineChartBase;
import com.example.testchart.chart.components.YAxis;
import com.example.testchart.chart.data.LineData;
import com.example.testchart.chart.interfaces.dataprovider.LineDataProvider;
import com.example.testchart.chart.renderer.LineChartRenderer;

public class LineChart extends LineChartBase<LineData> implements LineDataProvider {
    private static final String TAG = "LineChart";

    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        Log.d(TAG, "LineChart init: ");
        mRenderer = new LineChartRenderer(this, mAnimator, mViewPortHandler);
    }

    @Override
    public LineData getLineData() {
        return mData;
    }

    @Override
    public YAxis getAxis(YAxis.AxisDependency dependency) {
        return null;
    }

    @Override
    protected void onDetachedFromWindow() {
        // releases the bitmap in the renderer to avoid oom error
        if (mRenderer instanceof LineChartRenderer) {
            ((LineChartRenderer) mRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }
}
