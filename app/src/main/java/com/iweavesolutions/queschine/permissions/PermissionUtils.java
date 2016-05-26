package com.iweavesolutions.queschine.permissions;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;


/**
 * Created by raaj.gopal on 16/05/16.
 */
public class PermissionUtils extends Activity {

    public PermissionUtils(Context context, String permission) {
        hasPermission(context, permission);
    }

    public boolean hasPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
