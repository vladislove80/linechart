package com.example.testchart.chart.data;

import android.util.Log;

import com.example.testchart.chart.interfaces.dataset.ILineDataSet;

/**
 * Data object that encapsulates all data associated with a LineChart.
 */
public class LineData extends BaseLineData<ILineDataSet<Entry>> {
    private static final String TAG = "LineData";

    public LineData(ILineDataSet<Entry>... dataSets) {
        super(dataSets);
        Log.d(TAG, "LineData: ");
    }
}
