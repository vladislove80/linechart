package com.example.testchart.chart.interfaces.dataprovider;

import com.example.testchart.chart.components.YAxis;
import com.example.testchart.chart.data.BaseLineData;
import com.example.testchart.chart.utils.Transformer;

public interface BaseLineDataProvider extends ChartInterface {

    Transformer getTransformer(YAxis.AxisDependency axis);
    boolean isInverted(YAxis.AxisDependency axis);

    float getLowestVisibleX();
    float getHighestVisibleX();

    BaseLineData getData();
}
