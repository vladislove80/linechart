package com.example.testchart.chart.formatter;


import android.util.Log;

import com.example.testchart.chart.data.LineData;
import com.example.testchart.chart.interfaces.dataprovider.LineDataProvider;
import com.example.testchart.chart.interfaces.dataset.ILineDataSet;

/**
 * Default formatter that calculates the position of the filled line.
 *
 */
public class DefaultFillFormatter implements IFillFormatter {
    private static final String TAG = "DefaultFillFormatter";

    @Override
    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
        Log.d(TAG, "getFillLinePosition: ");

        float fillMin;
        float chartMaxY = dataProvider.getYChartMax();
        float chartMinY = dataProvider.getYChartMin();

        LineData data = dataProvider.getLineData();

        if (dataSet.getYMax() > 0 && dataSet.getYMin() < 0) {
            fillMin = 0f;
        } else {

            float max, min;

            if (data.getYMax() > 0)
                max = 0f;
            else
                max = chartMaxY;
            if (data.getYMin() < 0)
                min = 0f;
            else
                min = chartMinY;

            fillMin = dataSet.getYMin() >= 0 ? min : max;
        }

        return fillMin;
    }
}
