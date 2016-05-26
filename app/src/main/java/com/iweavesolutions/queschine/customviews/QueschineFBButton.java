package com.iweavesolutions.queschine.customviews;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.facebook.login.widget.LoginButton;

/**
 * Created by bharath.simha on 25/05/16.
 */
public class QueschineFBButton extends LoginButton {
    private static final String BUTTON_TEXT = "Sign In using Facebook";

    public QueschineFBButton(Context context) {
        super(context);
    }

    public QueschineFBButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QueschineFBButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(BUTTON_TEXT, type);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        int height = (getCompoundPaddingTop() +
                (int) Math.ceil(Math.abs(fontMetrics.top) + Math.abs(fontMetrics.bottom)) +
                getCompoundPaddingBottom());

        String text = BUTTON_TEXT;
        int logInWidth = measureButtonWidth(text);
        int width = resolveSize(logInWidth, widthMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureButtonWidth(final String text) {
        int textWidth = measureTextWidth(text);
        int width = (getCompoundPaddingLeft() +
                getCompoundDrawablePadding() +
                textWidth +
                getCompoundPaddingRight());
        return width;
    }
}
