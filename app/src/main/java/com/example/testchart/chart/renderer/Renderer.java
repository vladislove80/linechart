package com.example.testchart.chart.renderer;

import android.util.Log;

import com.example.testchart.chart.utils.ViewPortHandler;

/**
 * Abstract baseclass of all Renderers.
 *
 */
abstract class Renderer {
    private static final String TAG = "Renderer";

    /**
     * the component that handles the drawing area of the chart and it's offsets
     */
    final ViewPortHandler mViewPortHandler;

    Renderer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
        Log.d(TAG, "Renderer: ");
    }
}

