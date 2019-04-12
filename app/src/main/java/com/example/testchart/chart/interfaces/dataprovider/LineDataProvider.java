package com.example.testchart.chart.interfaces.dataprovider;

import com.example.testchart.chart.components.YAxis;
import com.example.testchart.chart.data.LineData;

public interface LineDataProvider extends BaseLineDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
