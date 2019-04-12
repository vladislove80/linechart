package com.example.testchart.chart.data;

import com.example.testchart.chart.interfaces.dataset.IDataSet;

/**
 * Baseclass for Line data.
 */
public abstract class BaseLineData<T extends IDataSet<? extends Entry>> extends ChartData<T> {

    BaseLineData(T... sets) {
        super(sets);
    }
}
