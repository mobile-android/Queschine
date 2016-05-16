package com.iweavesolutions.queschine.customviews;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.iweavesolutions.queschine.R;
import com.iweavesolutions.queschine.utilities.Utils;

/**
 * Created by bharath.simha on 16/05/16.
 */
public class CustomButton extends AppCompatButton {
    public CustomButton(Context context) {
        super(context);
        if (!isInEditMode())
            onInit(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            onInit(context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            onInit(context);
    }

    private void onInit(Context context) {
        setBackgroundColor(Utils.getColor(context, R.color.sign_up_bg));
        setTextColor(Utils.getColor(context, android.R.color.black));
    }
}
