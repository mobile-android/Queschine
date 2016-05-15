package com.iweavesolutions.queschine.customviews;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by bharath.simha on 15/05/16.
 */
public class LoadingDialog extends ProgressDialog {
    public LoadingDialog(Context context) {
        super(context);
        onInit(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        onInit(context);
    }

    private void onInit(Context context) {
        setMessage("Loading...");
        setCancelable(false);
    }

    public void setLoadingDialogMessage(String message) {
        setMessage(message);
    }

    public void setLoadingDialogCanceleable(boolean isCanceleable) {
        setCancelable(isCanceleable);
    }
}
