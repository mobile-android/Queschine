package com.iweavesolutions.queschine;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iweavesolutions.queschine.utilities.PreferenceManager;
import com.iweavesolutions.queschine.utilities.QSCNLogger;
import com.iweavesolutions.queschine.volley.LruBitmapCache;
import com.iweavesolutions.queschine.volley.NetworkMonitor;
import com.iweavesolutions.queschine.volley.Renderable;
import com.iweavesolutions.queschine.volley.RenderableDeserializer;
import com.iweavesolutions.queschine.volley.RenderableSerializer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class QueschineApplication extends Application {

    static Context context;

    public static final String TAG = QueschineApplication.class
            .getSimpleName();

    private static RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Gson gson;
    private static DisplayMetrics displayMetrics;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManager.getManagerInstance().initialize(getApplicationContext());

        QueschineApplication.context = getApplicationContext();
        NetworkMonitor.initialize(context);

        if (NetworkMonitor.isNetworkFast() == NetworkMonitor.NETWORK_TYPE_SLOW) {
            QSCNLogger.debug("Volley thread pool initialised for slow");
            mRequestQueue = Volley.newRequestQueue(QueschineApplication.context);
        } else {
            QSCNLogger.debug("Volley thread pool initialised for fast");
            mRequestQueue = Volley.newRequestQueue(QueschineApplication.context);
        }

        displayMetrics = QueschineApplication.getAppContext().getResources().getDisplayMetrics();

        initGson();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

    /**
     * Call this method inside onCreate once to get your hash key
     */
    public void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("co.coderiver.facebooklogin_sample", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                QSCNLogger.error("SHA : ", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static Gson getGsonInstance() {
        return QueschineApplication.gson;
    }

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public static Context getAppContext() {
        return QueschineApplication.context;
    }


    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    private void initGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Renderable.class, new RenderableDeserializer());
        gsonBuilder.registerTypeAdapter(Renderable.class, new RenderableSerializer());
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = gsonBuilder.create();
    }

    public static void addToRequestQueue(Request req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        mRequestQueue.add(req);
    }

    public static void addToRequestQueue(Request request) {
        mRequestQueue.add(request);
        QSCNLogger.debug("REQUEST_URL: " + request.getUrl());
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
