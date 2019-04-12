package com.example.testchart.chart.listeners;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.testchart.chart.charts.Chart;
import com.example.testchart.chart.highlight.Highlight;

public abstract class ChartTouchListener<T extends Chart<?>> extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
    private static final String TAG = "ChartTouchListener";

    public enum ChartGesture {
        NONE, DRAG, X_ZOOM, Y_ZOOM, PINCH_ZOOM, ROTATE, SINGLE_TAP, DOUBLE_TAP, LONG_PRESS, FLING
    }

    /**
     * the last touch gesture that has been performed
     **/
    ChartGesture mLastGesture = ChartGesture.NONE;

    // states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int X_ZOOM = 2;
    static final int Y_ZOOM = 3;
    static final int PINCH_ZOOM = 4;
    static final int POST_ZOOM = 5;
    protected static final int ROTATE = 6;

    /**
     * integer field that holds the current touch-state
     */
    int mTouchMode = NONE;

    /**
     * the last highlighted object (via touch)
     */
    private Highlight mLastHighlighted;

    /**
     * the gesturedetector used for detecting taps and longpresses, ...
     */
    final GestureDetector mGestureDetector;

    /**
     * the chart the listener represents
     */
    final T mChart;

    ChartTouchListener(T chart) {
        this.mChart = chart;
        Log.d(TAG, "ChartTouchListener: ");

        mGestureDetector = new GestureDetector(chart.getContext(), this);
    }

    /**
     * Calls the OnChartGestureListener to do the start callback
     *
     * @param me
     */
    void startAction(MotionEvent me) {

        OnChartGestureListener l = mChart.getOnChartGestureListener();

        if (l != null)
            l.onChartGestureStart(me, mLastGesture);
    }

    /**
     * Calls the OnChartGestureListener to do the end callback
     *
     * @param me
     */
    void endAction(MotionEvent me) {

        OnChartGestureListener l = mChart.getOnChartGestureListener();

        if (l != null)
            l.onChartGestureEnd(me, mLastGesture);
    }

    /**
     * Sets the last value that was highlighted via touch.
     *
     * @param high
     */
    public void setLastHighlighted(Highlight high) {
        mLastHighlighted = high;
    }

    /**
     * Perform a highlight operation.
     *
     * @param e
     */
    void performHighlight(Highlight h, MotionEvent e) {

        if (h == null || h.equalTo(mLastHighlighted)) {
            mChart.highlightValue(null, true);
            mLastHighlighted = null;
        } else {
            mChart.highlightValue(h, true);
            mLastHighlighted = h;
        }
    }

    /**
     * returns the distance between two points
     *
     * @param eventX
     * @param startX
     * @param eventY
     * @param startY
     * @return
     */
    static float distance(float eventX, float startX, float eventY, float startY) {
        float dx = eventX - startX;
        float dy = eventY - startY;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
