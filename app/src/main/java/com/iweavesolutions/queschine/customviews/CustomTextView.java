package com.iweavesolutions.queschine.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.iweavesolutions.queschine.utilities.FontCache;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        if (!isInEditMode())
            init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            init(context);
    }

    private void init(Context context) {
        setTypeface(setCustomTypeFace(context));
    }

    private Typeface setCustomTypeFace(Context context) {
        String font = "fonts/bebas_neue.ttf";
        return FontCache.getFont(context, font);
    }
}
