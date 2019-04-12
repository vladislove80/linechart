package com.example.testchart.chart.data;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.testchart.chart.formatter.DefaultFillFormatter;
import com.example.testchart.chart.formatter.IFillFormatter;
import com.example.testchart.chart.interfaces.dataset.ILineDataSet;
import com.example.testchart.chart.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LineDataSet extends DataSet<Entry> implements ILineDataSet<Entry> {
    private static final String TAG = "LineDataSet";

    private boolean mDrawVerticalHighlightIndicator = true;
    private boolean mDrawHorizontalHighlightIndicator = true;
    /**
     * the width of the highlight indicator lines
     */
    private float mHighlightLineWidth;
    private int mHighLightColor = Color.rgb(255, 187, 115);
    /**
     * the path effect for dashed highlight-lines
     */
    private DashPathEffect mHighlightDashPathEffect = null;
    /**
     * the drawable to be used for filling the line surface
     */
    private Drawable mFillDrawable;

    /**
     * Drawing mode for this line dataset
     **/
    private LineDataSet.Mode mMode = Mode.LINEAR;

    /**
     * List representing all colors that are used for the circles
     */
    private List<Integer> mCircleColors = null;

    /**
     * the color of the inner circles
     */
    private int mCircleHoleColor = Color.WHITE;

    /**
     * the radius of the circle-shaped value indicators
     */
    private float mCircleRadius = 8f;

    /**
     * the hole radius of the circle-shaped value indicators
     */
    private float mCircleHoleRadius = 4f;

    /**
     * sets the intensity of the cubic lines
     */
    private float mCubicIntensity = 0.2f;

    /**
     * the path effect of this DataSet that makes dashed lines possible
     */
    private DashPathEffect mDashPathEffect = null;

    /**
     * formatter for customizing the position of the fill-line
     */
    private IFillFormatter mFillFormatter = new DefaultFillFormatter();

    /**
     * if true, drawing circles is enabled
     */
    private boolean mDrawCircles = true;

    private boolean mDrawCircleHole = true;
    /**
     * the color that is used for filling the line surface
     */
    private int mFillColor = Color.rgb(140, 234, 255);
    /**
     * transparency used for filling line surface
     */
    private int mFillAlpha = 85;
    /**
     * the width of the drawn data lines
     */
    private float mLineWidth = 2.5f;
    /**
     * if true, the data will also be drawn filled
     */
    private boolean mDrawFilled = false;


    public LineDataSet(List<Entry> yVals, String label) {
        super(yVals, label);
        this.mHighlightLineWidth = Utils.convertDpToPixel(0.5f);
        Log.d(TAG, "LineDataSet: ");

        if (mCircleColors == null) {
            mCircleColors = new ArrayList<>();
        }
        mCircleColors.clear();
        mCircleColors.add(Color.rgb(140, 234, 255));
    }

    public DataSet<Entry> copy() {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < mValues.size(); i++) {
            entries.add(mValues.get(i).copy());
        }
        LineDataSet copied = new LineDataSet(entries, getLabel());
        copied.mCircleColors = mCircleColors;
        copied.mCircleHoleColor = mCircleHoleColor;
        copied.mCircleHoleRadius = mCircleHoleRadius;
        copied.mCircleRadius = mCircleRadius;
        copied.mFillColor = mFillColor;
        copied.mCubicIntensity = mCubicIntensity;
        copied.mDashPathEffect = mDashPathEffect;
        copied.mDrawCircleHole = mDrawCircleHole;
        copied.mDrawCircles = mDrawCircleHole;
        copied.mFillFormatter = mFillFormatter;
        copied.mMode = mMode;
        return copied;
    }

    /**
     * Returns the drawing mode for this line dataset
     *
     * @return
     */
    @Override
    public LineDataSet.Mode getMode() {
        return mMode;
    }

    /**
     * Returns the drawing mode for this LineDataSet
     *
     * @return
     */
    public void setMode(LineDataSet.Mode mode) {
        mMode = mode;
    }

    @Override
    public float getCubicIntensity() {
        return mCubicIntensity;
    }

    /**
     * Sets the radius of the drawn circles.
     * Default radius = 4f, Min = 1f
     *
     * @param radius
     */
    public void setCircleRadius(float radius) {

        if (radius >= 1f) {
            mCircleRadius = Utils.convertDpToPixel(radius);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 1");
        }
    }

    @Override
    public float getCircleRadius() {
        return mCircleRadius;
    }

    /**
     * Sets the hole radius of the drawn circles.
     * Default radius = 2f, Min = 0.5f
     *
     * @param holeRadius
     */
    public void setCircleHoleRadius(float holeRadius) {

        if (holeRadius >= 0.5f) {
            mCircleHoleRadius = Utils.convertDpToPixel(holeRadius);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 0.5");
        }
    }

    @Override
    public float getCircleHoleRadius() {
        return mCircleHoleRadius;
    }

    /**
     * sets the size (radius) of the circle shpaed value indicators,
     * default size = 4f
     * <p/>
     * This method is deprecated because of unclarity. Use setCircleRadius instead.
     *
     * @param size
     */
    @Deprecated
    public void setCircleSize(float size) {
        setCircleRadius(size);
    }

    /**
     * Enables the line to be drawn in dashed mode, e.g. like this
     * "- - - - - -". THIS ONLY WORKS IF HARDWARE-ACCELERATION IS TURNED OFF.
     * Keep in mind that hardware acceleration boosts performance.
     *
     * @param lineLength  the length of the line pieces
     * @param spaceLength the length of space in between the pieces
     * @param phase       offset, in degrees (normally, use 0)
     */
    public void enableDashedLine(float lineLength, float spaceLength, float phase) {
        mDashPathEffect = new DashPathEffect(new float[]{
                lineLength, spaceLength
        }, phase);
    }

    /**
     * This function is deprecated because of unclarity. Use getCircleRadius instead.
     */
    @Deprecated
    public float getCircleSize() {
        return getCircleRadius();
    }

    @Override
    public boolean isDashedLineEnabled() {
        return mDashPathEffect != null;
    }

    @Override
    public DashPathEffect getDashPathEffect() {
        return mDashPathEffect;
    }

    @Override
    public boolean isDrawCirclesEnabled() {
        return mDrawCircles;
    }

    @Deprecated
    @Override
    public boolean isDrawCubicEnabled() {
        return mMode == Mode.CUBIC_BEZIER;
    }

    @Deprecated
    @Override
    public boolean isDrawSteppedEnabled() {
        return mMode == Mode.STEPPED;
    }

    /**
     * ALL CODE BELOW RELATED TO CIRCLE-COLORS
     */

    @Override
    public int getCircleColor(int index) {
        return mCircleColors.get(index);
    }

    @Override
    public int getCircleColorCount() {
        return mCircleColors.size();
    }

    /**
     * Sets the one and ONLY color that should be used for this DataSet.
     * Internally, this recreates the colors array and adds the specified color.
     *
     * @param color
     */
    public void setCircleColor(int color) {
        resetCircleColors();
        mCircleColors.add(color);
    }

    /**
     * resets the circle-colors array and creates a new one
     */
    private void resetCircleColors() {
        if (mCircleColors == null) {
            mCircleColors = new ArrayList<>();
        }
        mCircleColors.clear();
    }

    /**
     * Sets the color of the inner circle of the line-circles.
     *
     * @param color
     */
    public void setCircleHoleColor(int color) {
        mCircleHoleColor = color;
    }

    @Override
    public int getCircleHoleColor() {
        return mCircleHoleColor;
    }

    @Override
    public boolean isDrawCircleHoleEnabled() {
        return mDrawCircleHole;
    }

    @Override
    public IFillFormatter getFillFormatter() {
        return mFillFormatter;
    }

    @Override
    public int getFillColor() {
        return mFillColor;
    }

    /**
     * Sets the color that is used for filling the area below the line.
     * Resets an eventually set "fillDrawable".
     *
     * @param color
     */
    public void setFillColor(int color) {
        mFillColor = color;
        mFillDrawable = null;
    }

    @Override
    public Drawable getFillDrawable() {
        return mFillDrawable;
    }

    /**
     * Sets the drawable to be used to fill the area below the line.
     *
     * @param drawable
     */
    @TargetApi(18)
    public void setFillDrawable(Drawable drawable) {
        this.mFillDrawable = drawable;
    }

    @Override
    public int getFillAlpha() {
        return mFillAlpha;
    }

    /**
     * sets the alpha value (transparency) that is used for filling the line
     * surface (0-255), default: 85
     *
     * @param alpha
     */
    public void setFillAlpha(int alpha) {
        mFillAlpha = alpha;
    }

    /**
     * set the line width of the chart (min = 0.2f, max = 10f); default 1f NOTE:
     * thinner line == better performance, thicker line == worse performance
     *
     * @param width
     */
    public void setLineWidth(float width) {

        if (width < 0.0f)
            width = 0.0f;
        if (width > 10.0f)
            width = 10.0f;
        mLineWidth = Utils.convertDpToPixel(width);
    }

    @Override
    public float getLineWidth() {
        return mLineWidth;
    }

    @Override
    public void setDrawFilled(boolean filled) {
        mDrawFilled = filled;
    }

    @Override
    public boolean isDrawFilledEnabled() {
        return mDrawFilled;
    }

    /**
     * Enables / disables the horizontal highlight-indicator. If disabled, the indicator is not drawn.
     *
     * @param enabled
     */
    private void setDrawHorizontalHighlightIndicator(boolean enabled) {
        this.mDrawHorizontalHighlightIndicator = enabled;
    }

    /**
     * Enables / disables the vertical highlight-indicator. If disabled, the indicator is not drawn.
     *
     * @param enabled
     */
    private void setDrawVerticalHighlightIndicator(boolean enabled) {
        this.mDrawVerticalHighlightIndicator = enabled;
    }

    /**
     * Enables / disables both vertical and horizontal highlight-indicators.
     *
     * @param enabled
     */
    public void setDrawHighlightIndicators(boolean enabled) {
        setDrawVerticalHighlightIndicator(enabled);
        setDrawHorizontalHighlightIndicator(enabled);
    }

    @Override
    public boolean isVerticalHighlightIndicatorEnabled() {
        return mDrawVerticalHighlightIndicator;
    }

    @Override
    public boolean isHorizontalHighlightIndicatorEnabled() {
        return mDrawHorizontalHighlightIndicator;
    }

    /**
     * Sets the width of the highlight line in dp.
     *
     * @param width
     */
    public void setHighlightLineWidth(float width) {
        mHighlightLineWidth = Utils.convertDpToPixel(width);
    }

    @Override
    public float getHighlightLineWidth() {
        return mHighlightLineWidth;
    }

    /**
     * Enables the highlight-line to be drawn in dashed mode, e.g. like this "- - - - - -"
     *
     * @param lineLength  the length of the line pieces
     * @param spaceLength the length of space inbetween the line-pieces
     * @param phase       offset, in degrees (normally, use 0)
     */
    public void enableDashedHighlightLine(float lineLength, float spaceLength, float phase) {
        mHighlightDashPathEffect = new DashPathEffect(new float[]{
                lineLength, spaceLength
        }, phase);
    }

    /**
     * Disables the highlight-line to be drawn in dashed mode.
     */
    public void disableDashedHighlightLine() {
        mHighlightDashPathEffect = null;
    }

    /**
     * Returns true if the dashed-line effect is enabled for highlight lines, false if not.
     * Default: disabled
     *
     * @return
     */
    public boolean isDashedHighlightLineEnabled() {
        return mHighlightDashPathEffect != null;
    }

    @Override
    public DashPathEffect getDashPathEffectHighlight() {
        return mHighlightDashPathEffect;
    }

    public void setHighLightColor(int color) {
        mHighLightColor = color;
    }

    @Override
    public int getHighLightColor() {
        return mHighLightColor;
    }

    @Override
    public float getFormSize() {
        return Float.NaN;
    }

    public enum Mode {
        LINEAR,
        STEPPED,
        CUBIC_BEZIER,
        HORIZONTAL_BEZIER
    }
}

