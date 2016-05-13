package com.iweavesolutions.queschine.volley;

import com.android.volley.NetworkResponse;
import com.iweavesolutions.queschine.utilities.Utils;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

/**
 * Created by bharath.simha on 05/15/16.
 */
public class ResponseUtils {

    public static Reader getJsonReader(NetworkResponse response) {
        Reader jsonReader = null;

        try {
            boolean isGZipped = isResponseGZipped(response);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(response.data);
            if (!isGZipped) {
                jsonReader = new InputStreamReader(inputStream);
            } else {
                jsonReader = new InputStreamReader(new GZIPInputStream(inputStream));
            }
        } catch (Exception ignored) {
        }
        return jsonReader;
    }

    public static boolean isResponseGZipped(NetworkResponse response) {
        boolean isGZipped = false;
        String contentResponse = response.headers.get("Content-Encoding");
        if (!Utils.isNullOrEmpty(contentResponse) && contentResponse.contains("gzip")) {
            isGZipped = true;
        }
        return isGZipped;
    }
}
