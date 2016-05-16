package com.iweavesolutions.queschine.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.iweavesolutions.queschine.R;

/**
 * Created by bharath.simha on 16/05/16.
 */
@SuppressWarnings("DefaultFileTemplate")
public class PasswordEditText extends BaseEditText {

    public static final String SHOW = "Show";
    public static final String HIDE = "Hide";
    public static final String PASSWORD = "Enter Your Password";
    protected ViewSwitcher switcher;
    protected TextView show;
    TextView forgot;
    private TextWatcher watcher;
    private boolean isShowClicked = false;

    public PasswordEditText(Context context) {
        super(context);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void configureView() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.password_edit_text, null);
        editText = (EditText) linearLayout.findViewById(R.id.password);
        hintFocusText = (TextView) linearLayout.findViewById(R.id.hintFocusText);
        animFadein = AnimationUtils.loadAnimation(getContext(), R.anim.email_in);
        animFadeout = AnimationUtils.loadAnimation(getContext(), R.anim.email_out);

        setFocusWatcher(editText, animFadein, animFadeout, null);
        setHint(PASSWORD);
        final Typeface typeface = Typeface.create("sans-serif", Typeface.NORMAL);
        switcher = (ViewSwitcher) linearLayout.findViewById(R.id.forgotSwitcher);
        setTypeface(typeface);
        forgot = (TextView) linearLayout.findViewById(R.id.forgot);

        show = (TextView) linearLayout.findViewById(R.id.show);
        show.setTypeface(typeface);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getTag().equals(SHOW)) {
                    isShowClicked = true;
                    setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    show.setText(HIDE);
                    v.setTag(HIDE);
                } else {
                    setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    v.setTag(SHOW);
                    show.setText(SHOW);
                }
                editText.setSelection(editText.getText().length());
            }
        });
        watcher = new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if (switcher != null && (show.getVisibility() != View.VISIBLE)) {
                        switcher.showNext();
                    }
                } else {
                    if (switcher != null) {
                        if (switcher.getDisplayedChild() == 1) {
                            switcher.showPrevious();
                        }
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(final Editable s) {

            }
        };
        addTextChangedListener(watcher);
        addView(linearLayout);
    }

    public void setForgotPasswordListener(View.OnClickListener listner) {
        forgot.setOnClickListener(listner);
    }

    public void hideForgot() {
        if (forgot != null) forgot.setText("");
    }

}
