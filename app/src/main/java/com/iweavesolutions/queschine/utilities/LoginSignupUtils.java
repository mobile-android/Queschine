package com.iweavesolutions.queschine.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class LoginSignupUtils {

    public static boolean isValidEmail(String email) {
        if (Utils.isNullOrEmpty(email)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$");
            Matcher matcher = pattern.matcher(email);
            return matcher.find();
        }
    }
}
