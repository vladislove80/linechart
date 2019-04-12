package com.example.testchart.chart.interfaces.dataset;

import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;

import com.example.testchart.chart.data.Entry;
import com.example.testchart.chart.data.LineDataSet;
import com.example.testchart.chart.formatter.IFillFormatter;

public interface ILineDataSet<T extends Entry> extends IDataSet<T> {

    /**
     * Returns the color that is used for drawing the highlight indicators.
     *
     * @return
     */
    int getHighLightColor();

    /**
     * Returns true if vertical highlight indicator lines are enabled (drawn)
     *
     * @return
     */
    boolean isVerticalHighlightIndicatorEnabled();

    /**
     * Returns true if vertical highlight indicator lines are enabled (drawn)
     *
     * @return
     */
    boolean isHorizontalHighlightIndicatorEnabled();

    /**
     * Returns the line-width in which highlight lines are to be drawn.
     *
     * @return
     */
    float getHighlightLineWidth();

    /**
     * Returns the DashPathEffect that is used for highlighting.
     *
     * @return
     */
    DashPathEffect getDashPathEffectHighlight();

    /**
     * Returns the color that is used for filling the line surface area.
     *
     * @return
     */
    int getFillColor();

    /**
     * Returns the drawable used for filling the area below the line.
     *
     * @return
     */
    Drawable getFillDrawable();

    /**
     * Returns the alpha value that is used for filling the line surface,
     * default: 85
     *
     * @return
     */
    int getFillAlpha();

    /**
     * Returns the stroke-width of the drawn line
     *
     * @return
     */
    float getLineWidth();

    /**
     * Returns true if filled drawing is enabled, false if not
     *
     * @return
     */
    boolean isDrawFilledEnabled();

    /**
     * Set to true if the DataSet should be drawn filled (surface), and not just
     * as a line, disabling this will give great performance boost. Please note that this method
     * uses the canvas.clipPath(...) method for drawing the filled area.
     * For devices with API level < 18 (Android 4.3), hardware acceleration of the chart should
     * be turned off. Default: false
     *
     * @param enabled
     */
    void setDrawFilled(boolean enabled);

    /**
     * Returns the drawing mode for this line dataset
     *
     * @return
     */
    LineDataSet.Mode getMode();

    /**
     * Returns the intensity of the cubic lines (the effect intensity).
     * Max = 1f = very cubic, Min = 0.05f = low cubic effect, Default: 0.2f
     *
     * @return
     */
    float getCubicIntensity();

    @Deprecated
    boolean isDrawCubicEnabled();

    @Deprecated
    boolean isDrawSteppedEnabled();

    /**
     * Returns the size of the drawn circles.
     */
    float getCircleRadius();

    /**
     * Returns the hole radius of the drawn circles.
     */
    float getCircleHoleRadius();

    /**
     * Returns the color at the given index of the DataSet's circle-color array.
     * Performs a IndexOutOfBounds check by modulus.
     *
     * @param index
     * @return
     */
    int getCircleColor(int index);

    /**
     * Returns the number of colors in this DataSet's circle-color array.
     *
     * @return
     */
    int getCircleColorCount();

    /**
     * Returns true if drawing circles for this DataSet is enabled, false if not
     *
     * @return
     */
    boolean isDrawCirclesEnabled();

    /**
     * Returns the color of the inner circle (the circle-hole).
     *
     * @return
     */
    int getCircleHoleColor();

    /**
     * Returns true if drawing the circle-holes is enabled, false if not.
     *
     * @return
     */
    boolean isDrawCircleHoleEnabled();

    /**
     * Returns the DashPathEffect that is used for drawing the lines.
     *
     * @return
     */
    DashPathEffect getDashPathEffect();

    /**
     * Returns true if the dashed-line effect is enabled, false if not.
     * If the DashPathEffect object is null, also return false here.
     *
     * @return
     */
    boolean isDashedLineEnabled();

    /**
     * Returns the IFillFormatter that is set for this DataSet.
     *
     * @return
     */
    IFillFormatter getFillFormatter();
}
