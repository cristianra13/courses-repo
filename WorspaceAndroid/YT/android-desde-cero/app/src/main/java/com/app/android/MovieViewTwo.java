package com.app.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MovieViewTwo extends LinearLayout {

    public MovieViewTwo(Context context) {
        this(context, null);
    }

    public MovieViewTwo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieViewTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
