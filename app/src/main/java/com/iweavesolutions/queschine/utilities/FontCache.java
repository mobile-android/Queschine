package com.iweavesolutions.queschine.utilities;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

public class FontCache {

    private static Map<String, Typeface> cache = new HashMap<String, Typeface>();

    public static Typeface getFont(Context context, String font) {

        Typeface typeface = cache.get(font);
        if (typeface != null)
            return typeface;

        typeface = Typeface.createFromAsset(context.getAssets(), font);
        cache.put(font, typeface);
        return typeface;
    }
}
