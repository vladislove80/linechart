package com.example.testchart.chart.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.util.Log;

import com.example.testchart.chart.utils.Utils;

/**
 * Class representing one entry in the chart
 */
public class Entry implements Parcelable {
    private static final String TAG = "Entry";

    /**
     * the x value
     */
    private float x = 0f;
    /**
     * the y value
     */
    private float y = 0f;
    /**
     * optional spot for additional data this Entry represents
     */
    private Object mData = null;
    /**
     * optional icon image
     */
    private Drawable mIcon = null;

    public Entry() {

        Log.d(TAG, "Entry: ");
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param x the x value
     * @param y the y value (the actual value of the entry)
     */
    public Entry(float x, float y) {
        this.y = y;
        this.x = x;
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param x    the x value
     * @param y    the y value (the actual value of the entry)
     * @param data Spot for additional data this Entry represents.
     */
    private Entry(float x, float y, Object data) {
        this.mData = data;
        this.x = x;
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param x    the x value
     * @param y    the y value (the actual value of the entry)
     * @param icon icon image
     */
    public Entry(float x, float y, Drawable icon) {
        this.mIcon = icon;
        this.x = x;
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param x    the x value
     * @param y    the y value (the actual value of the entry)
     * @param icon icon image
     * @param data Spot for additional data this Entry represents.
     */
    public Entry(float x, float y, Drawable icon, Object data) {
        this.mIcon = icon;
        this.mData = data;
        this.x = x;
    }

    /**
     * Returns the x-value of this Entry object.
     *
     * @return
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x-value of this Entry object.
     *
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * returns an exact copy of the entry
     *
     * @return
     */
    public Entry copy() {
        return new Entry(x, getY(), getData());
    }

    /**
     * Compares value, xIndex and data of the entries. Returns true if entries
     * are equal in those points, false if not. Does not check by hash-code like
     * it's done by the "equals" method.
     *
     * @param e
     * @return
     */
    public boolean equalTo(Entry e) {

        if (e == null)
            return false;

        if (e.getData() != this.getData())
            return false;

        if (Math.abs(e.x - this.x) > Utils.FLOAT_EPSILON)
            return false;

        if (Math.abs(e.getY() - this.getY()) > Utils.FLOAT_EPSILON)
            return false;

        return true;
    }

    /**
     * returns a string representation of the entry containing x-index and value
     */
    @Override
    public String toString() {
        return "Entry, x: " + x + " y: " + getY();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.x);
        dest.writeFloat(this.getY());
        if (getData() != null) {
            if (getData() instanceof Parcelable) {
                dest.writeInt(1);
                dest.writeParcelable((Parcelable) this.getData(), flags);
            } else {
                throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
            }
        } else {
            dest.writeInt(0);
        }
    }

    private Entry(Parcel in) {

        this.x = in.readFloat();
        this.setY(in.readFloat());
        if (in.readInt() == 1) {
            this.setData(in.readParcelable(Object.class.getClassLoader()));
        }
    }

    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>() {
        public Entry createFromParcel(Parcel source) {
            return new Entry(source);
        }

        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    /**
     * Returns the y value of this Entry.
     *
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the icon drawable
     *
     * @param icon
     */
    public void setIcon(Drawable icon) {
        this.mIcon = icon;
    }

    /**
     * Returns the icon of this Entry.
     *
     * @return
     */
    public Drawable getIcon() {
        return mIcon;
    }

    /**
     * Sets the y-value for the Entry.
     *
     * @param y
     */
    private void setY(float y) {
        this.y = y;
    }

    /**
     * Returns the data, additional information that this Entry represents, or
     * null, if no data has been specified.
     *
     * @return
     */
    private Object getData() {
        return mData;
    }

    /**
     * Sets additional data this Entry should represent.
     *
     * @param data
     */
    private void setData(Object data) {
        this.mData = data;
    }
}
