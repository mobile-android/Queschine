package com.iweavesolutions.queschine.utilities;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.android.volley.Request;
import com.iweavesolutions.queschine.QueschineApplication;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class Utils {

    public static void setBackground(View view, int resourceId) {
        setBackground(view, view.getContext().getResources().getDrawable(resourceId));
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static int getColor(Context context, int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(colorResId, null);
        } else {
            return context.getResources().getColor(colorResId);
        }
    }

    public static Drawable getDrawable(Context context, int drawableResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return context.getResources().getDrawable(drawableResId, null);
        } else {
            return context.getResources().getDrawable(drawableResId);
        }
    }

    private static ColorStateList getPressedColorSelector(int pressedColor) {
        return new ColorStateList(
                new int[][]
                        {
                                new int[]{}
                        },
                new int[]
                        {
                                pressedColor
                        }
        );
    }

    private static ColorDrawable getColorDrawableFromColor(int color) {
        return new ColorDrawable(color);
    }

    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null
                && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static int dpToPx(int dp) {
        DisplayMetrics metrics = QueschineApplication.getDisplayMetrics();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
        if (px < 1.0f) {
            px = 0;
        }
        return (int) px;
    }

    private static final int DEFAULT_COUNT = 10;
    private static final String AMPERSAND = "&";
    private static final String EQUAL_TO = "=";

    public static boolean isValidIndianPin(String pin) {
        if (pin == null || pin.length() != 6) {
            return false;
        }
        if (pin.matches("^[1-9][0-9]+")) {
            return true;
        } else {
            return false;
        }
    }


    public static String fetchString(Map<String, Object> jsonMap, String tag) {
        String val = "";
        if (jsonMap != null && isNullOrEmpty(tag)) {
            val = (String) jsonMap.get(tag);
        }
        //adding this because there is a missing null checks somewhere
        if (val == null)
            return "";
        return val;
    }


    public static String arrayToCSV(String... params) {
        StringBuilder result = new StringBuilder();
        for (String string : params) {
            result.append(string);
            result.append(",");
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1) : "";
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
    }

    public static <T> boolean isNullOrEmpty(ArrayList<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T, Y> boolean isNullOrEmpty(Map<T, Y> map) {
        return map == null || map.size() == 0;
    }

    public static <T> boolean isNull(T object) {
        return object == null;
    }


    public static String trim(String str, String pattern) {
        while (str.endsWith(pattern)) {
            str = str.substring(0, str.length() - pattern.length());
        }
        while (str.startsWith(pattern)) {
            str = str.substring(pattern.length(), str.length());
        }
        return str;
    }

    public static boolean isHttps(String url) {
        if (url != null && url.length() > 0) {
            return url.startsWith("https");
        } else {
            return false;
        }
    }

    public static StringBuilder join(ArrayList<String> list, String delimiter) {
        ArrayList<String> copiedList = list;
        StringBuilder stringBuilder = new StringBuilder();
        if (copiedList != null) {
            for (String string : copiedList) {
                stringBuilder.append(string).append(delimiter);
            }
        }
        return stringBuilder;
    }

    public static Map<String, String> splitIntoMap(String text, String delimiter, String separator) {
        HashMap<String, String> ret = new HashMap<String, String>();

        String decodedString = "";
        try {
            decodedString = URLDecoder.decode(text, "UTF-8");
        } catch (Exception ignored) {
        }

        String[] split = decodedString.split(delimiter);
        for (String string : split) {
            if (string.contains(separator)) {
                String[] element = string.split(separator);
                if (element.length == 2)
                    ret.put(element[0], element[1]);
            }
        }
        return ret;
    }

    public static SpannableString getHyperLinkedText(String text) {

        SpannableString ss = new SpannableString(text);
        ss.setSpan(new URLSpan(""), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }

    public static String getHtmlTextWithCss(String offerText) {

        String text = "<html><head>"
                + "<style type=\"text/css\">body{color: #767676; background-color: #fff;font-family:Roboto.Light}"
                + "</style></head>"
                + "<body>"
                + "<div style=\"position:relative;right:7%\">"
                + offerText
                + "</div>"
                + "</body></html>";
        return text;

    }

    public static int countNumberOfToken(String keywordText) {
        StringTokenizer stringTokenizer = new StringTokenizer(keywordText);
        return stringTokenizer.countTokens();
    }


    public static String getSuffixUri(Map<String, Object> paramsMap) {
        StringBuilder suffixUri = new StringBuilder();
        for (String key : paramsMap.keySet()) {
            if (!(isNullOrEmpty(key))) {
                Object val = paramsMap.get(key);
                // in case the value obtained is a map or an array,we ignore as we do not know how to add it to suffix.
                if (val != null) {
                    if (val instanceof String || val instanceof Boolean || val instanceof Integer) {
                        suffixUri.append(AMPERSAND).append(key);
                        suffixUri.append(EQUAL_TO);
                        suffixUri.append(val);
                    }
                }
            }
        }
        String suffixUriString = suffixUri.toString();
        return suffixUriString;
    }

    public static String getSuffixUri(String tag, String val) {
        if (!(isNullOrEmpty(tag)) && !(isNullOrEmpty(val))) {
            String uriAppend = AMPERSAND + tag + EQUAL_TO + val;
            return uriAppend;
        }
        return "";
    }

    public static boolean isValidMobile(String mobile) {
        if (isNullOrEmpty(mobile)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^\\d{10}$");
            Matcher matcher = pattern.matcher(mobile);
            return matcher.find();

        }
    }

    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$");
            Matcher matcher = pattern.matcher(email);
            return matcher.find();

        }
    }

    public static int getMethodType(String methodType) {
        if (!(isNullOrEmpty(methodType))) {
            if (methodType.equalsIgnoreCase("POST")) {
                return Request.Method.POST;
            }
        }
        return Request.Method.GET;
    }


}
