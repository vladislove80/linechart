package com.example.testchart.chart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;

import com.example.testchart.chart.animation.ChartAnimator;
import com.example.testchart.chart.highlight.Highlight;
import com.example.testchart.chart.interfaces.dataprovider.ChartInterface;
import com.example.testchart.chart.interfaces.dataset.IDataSet;
import com.example.testchart.chart.utils.Utils;
import com.example.testchart.chart.utils.ViewPortHandler;

/**
 * Superclass of all render classes for the different data types (line, bar, ...).
 */
public abstract class DataRenderer extends Renderer {
    private static final String TAG = "DataRenderer";

    /**
     * the animator object used to perform animations on the chart data
     */
    final ChartAnimator mAnimator;

    /**
     * main paint object used for rendering
     */
    final Paint mRenderPaint;

    /**
     * paint used for highlighting values
     */
    final Paint mHighlightPaint;

    private final Paint mDrawPaint;

    /**
     * paint object for drawing values (text representing values of chart
     * entries)
     */
    final Paint mValuePaint;

    DataRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(viewPortHandler);
        Log.d(TAG, "DataRenderer: ");
        this.mAnimator = animator;

        mRenderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRenderPaint.setStyle(Style.FILL);

        mDrawPaint = new Paint(Paint.DITHER_FLAG);

        mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setColor(Color.rgb(63, 63, 63));
        mValuePaint.setTextAlign(Align.CENTER);
        mValuePaint.setTextSize(Utils.convertDpToPixel(9f));

        mHighlightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHighlightPaint.setStyle(Paint.Style.STROKE);
        mHighlightPaint.setStrokeWidth(2f);
        mHighlightPaint.setColor(Color.rgb(255, 187, 115));
    }

    boolean isDrawingValuesAllowed(ChartInterface chart) {
        return chart.getData().getEntryCount() < chart.getMaxVisibleCount()
                * mViewPortHandler.getScaleX();
    }

    /**
     * Applies the required styling (provided by the DataSet) to the value-paint
     * object.
     *
     * @param set
     */
    void applyValueTextStyle(IDataSet set) {

        mValuePaint.setTypeface(set.getValueTypeface());
        mValuePaint.setTextSize(set.getValueTextSize());
    }

    /**
     * Initializes the buffers used for rendering with a new size. Since this
     * method performs memory allocations, it should only be called if
     * necessary.
     */
    public abstract void initBuffers();

    /**
     * Draws the actual data in form of lines, bars, ... depending on Renderer subclass.
     *
     * @param c
     */
    public abstract void drawData(Canvas c);

    /**
     * Loops over all Entrys and draws their values.
     *
     * @param c
     */
    public abstract void drawValues(Canvas c);

    /**
     * Draws the value of the given entry by using the provided iValueFormatter(ValueFormatter).
     *
     * @param c         canvas
     * @param valueText label to draw
     * @param x         position
     * @param y         position
     * @param color
     */
    public abstract void drawValue(Canvas c, String valueText, float x, float y, int color);

    /**
     * Draws any kind of additional information (e.g. line-circles).
     *
     * @param c
     */
    public abstract void drawExtras(Canvas c);

    /**
     * Draws all highlight indicators for the values that are currently highlighted.
     *
     * @param c
     * @param indices the highlighted values
     */
    public abstract void drawHighlighted(Canvas c, Highlight[] indices);
}

