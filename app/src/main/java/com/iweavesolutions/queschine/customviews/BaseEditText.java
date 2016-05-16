package com.iweavesolutions.queschine.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iweavesolutions.queschine.R;
import com.iweavesolutions.queschine.utilities.Utils;

/**
 * Created by bharath.simha on 16/05/16.
 */
@SuppressWarnings("DefaultFileTemplate")
public abstract class BaseEditText extends FrameLayout {
    protected EditText editText;
    protected TextView hintFocusText;
    protected String hint;
    protected Animation animFadein;
    protected Animation animFadeout;

    public BaseEditText(Context context) {
        super(context);
        if (!isInEditMode())
            configureView();
    }

    public BaseEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            configureView();
    }

    public BaseEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            configureView();
    }

    /**
     * In this method calling setFocusWatcher() is required to get desired animation effect.
     */
    protected abstract void configureView();

    protected void setFocusWatcher(final EditText edittext, final Animation animFadein, final Animation animFadeout, final ImageButton closeBtn) {

        edittext.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String content = ((EditText) v).getText().toString();
                edittext.setHintTextColor(Utils.getColor(getContext(), R.color.hintActivated));
                if (Utils.isNullOrEmpty(content)) {
                    if (hasFocus) {
                        hintFocusText.setTextColor(Utils.getColor(getContext(), R.color.hintActivated));
                        hintFocusText.setText(hint);
                        hintFocusText.setVisibility(VISIBLE);
                        hintFocusText.startAnimation(animFadein);
                        edittext.setHint("");
                    } else {
                        hintFocusText.setTextColor(Utils.getColor(getContext(), android.R.color.white));
                        hintFocusText.startAnimation(animFadeout);
                        new CountDownTimer(350, 350) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                edittext.setHint(hint);
                                hintFocusText.setVisibility(INVISIBLE);
                            }
                        }.start();

                    }
                    if (closeBtn != null)
                        closeBtn.setVisibility(GONE);
                } else {
                    if (hasFocus) {
                        hintFocusText.setTextColor(Utils.getColor(getContext(), R.color.hintActivated));
                        if (closeBtn != null)
                            closeBtn.setVisibility(VISIBLE);
                    } else {
                        hintFocusText.setTextColor(Utils.getColor(getContext(), android.R.color.white));
                        if (closeBtn != null)
                            closeBtn.setVisibility(GONE);
                    }
                    hintFocusText.setText(hint);
                    hintFocusText.setVisibility(VISIBLE);
                }
            }
        });
    }

    public void setTypeface(Typeface typeface) {
        editText.setTypeface(typeface);
    }

    public void setInputType(int typeTextVariationPassword) {
        editText.setInputType(typeTextVariationPassword);
    }

    public String getText() {
        return editText.getText().toString().trim();
    }

    public void setSelection(int length) {
        editText.setSelection(length);
    }

    public void addTextChangedListener(TextWatcher watcher) {
        editText.addTextChangedListener(watcher);
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setHint(String hint) {
        this.hint = hint;
        editText.setHint(hint);
    }


    public void setImeOptions(int imeOptions) {
        editText.setImeOptions(imeOptions);
    }

    public EditText getEditText() {
        return this.editText;
    }

    public void removeTextChangedListener(TextWatcher watcher) {
        editText.removeTextChangedListener(watcher);
    }

    public void customClearFocus() {
        editText.clearFocus();
    }

    public void setEditorActionListener(TextView.OnEditorActionListener softNextListner) {
        editText.setOnEditorActionListener(softNextListner);
    }

}
