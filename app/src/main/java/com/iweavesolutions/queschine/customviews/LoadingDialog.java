package com.iweavesolutions.queschine.customviews;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by bharath.simha on 15/05/16.
 */
public class LoadingDialog extends ProgressDialog {
    public LoadingDialog(Context context) {
        super(context);
        onInit();
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        onInit();
    }

    private void onInit() {
        setMessage("Please Wait...");
        setCancelable(false);
        setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    public void setLoadingDialogMessage(String message) {
        setMessage(message);
    }

    public void setLoadingDialogCancelable(boolean isCancelable) {
        setCancelable(isCancelable);
    }
}
