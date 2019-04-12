package com.example.testchart.chart.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Object responsible for all animations in the Chart. Animations require API level 11.
 */
public class ChartAnimator {
    private static final String TAG = "ChartAnimator";

    /**
     * object that is updated upon animation update
     */
    private final AnimatorUpdateListener mListener;

    /**
     * The phase of drawn values on the y-axis. 0 - 1
     */
    @SuppressWarnings("WeakerAccess")
    protected final float mPhaseY = 1f;

    /**
     * The phase of drawn values on the x-axis. 0 - 1
     */
    @SuppressWarnings("WeakerAccess")
    protected float mPhaseX = 1f;

    @RequiresApi(11)
    public ChartAnimator(AnimatorUpdateListener listener) {
        Log.d(TAG, "ChartAnimator: ");
        mListener = listener;
    }

    @RequiresApi(11)
    private ObjectAnimator xAnimator(int duration, Easing.EasingFunction easing) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "phaseX", 0f, 1f);
        animatorX.setInterpolator(easing);
        animatorX.setDuration(duration);

        return animatorX;
    }

    /**
     * Animates values along the X axis, in a linear fashion.
     *
     * @param durationMillis animation duration
     */
    @RequiresApi(11)
    public void animateX(int durationMillis) {
        animateX(durationMillis, Easing.Linear);
    }

    /**
     * Animates values along the X axis.
     *
     * @param durationMillis animation duration
     * @param easing         EasingFunction
     */
    @RequiresApi(11)
    private void animateX(int durationMillis, Easing.EasingFunction easing) {

        ObjectAnimator animatorX = xAnimator(durationMillis, easing);
        animatorX.addUpdateListener(mListener);
        animatorX.start();
    }

    /**
     * Gets the Y axis phase of the animation.
     *
     * @return float value of {@link #mPhaseY}
     */
    public float getPhaseY() {
        return mPhaseY;
    }

    /**
     * Gets the X axis phase of the animation.
     *
     * @return float value of {@link #mPhaseX}
     */
    public float getPhaseX() {
        return mPhaseX;
    }

    /**
     * Sets the X axis phase of the animation.
     *
     * @param phase float value between 0 - 1
     */
    public void setPhaseX(float phase) {
        if (phase > 1f) {
            phase = 1f;
        } else if (phase < 0f) {
            phase = 0f;
        }
        mPhaseX = phase;
    }
}

