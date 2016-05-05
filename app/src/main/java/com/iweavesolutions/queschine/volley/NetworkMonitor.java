package com.iweavesolutions.queschine.volley;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class NetworkMonitor extends BroadcastReceiver {
    private static boolean isNetworkAvailable = false;
    private static final String TAG = "NetworkMonitor";
    private static Context context;

    public static final int NETWORK_TYPE_FAST_WIFI = 1;
    public static final int NETWORK_TYPE_FAST_3G = 2;
    public static final int NETWORK_TYPE_SLOW = 3;
    public static final int NETWORK_TYPE_NO_NETWORK = 4;

    /**
     * This constructor is only for Broadcast Listener. If you want to create an
     * object of NetworkMonitor for your use, create it using the other
     * constructor which takes a Context parameter.
     */
    public NetworkMonitor() {
    }

    public static void initialize(Context context) {
        NetworkMonitor.context = context;
    }

    public static boolean isNetworkAvailable() {
        checkNetworkConnectivity(context);
        return isNetworkAvailable;
    }

    public static void checkNetworkConnectivity(Context context) {
        if (context == null) {
            return;
        }

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            boolean isNetAvailable = false;
            for (NetworkInfo ni : netInfo) {
                if (ni.isConnected()) {
                    // Ignore MMS as a usable connection type
                    // Note: There are other types in CM like WIMAX, MOBILE_DUN,
                    // MOBILE_SUPL
                    // that we don't know about. Assume they are usable.
                    if (ni.getType() != ConnectivityManager.TYPE_MOBILE_MMS) {
                        isNetAvailable = true;
                        break;
                    }
                }
            }

            //  QSCNLogger.info(TAG, "Network Availability: " + isNetAvailable);
            if ((isNetAvailable != isNetworkAvailable)) {
                isNetworkAvailable = isNetAvailable;
                // Code for network available, fire event network available
            }

        } catch (Exception e) {
            // QSCNLogger.printStackTrace(e);
        }
    }

    public static NetworkInfo getNetworkType(Context context) {
        NetworkInfo aNetInfo = null;
        if (context != null) {
            try {
                ConnectivityManager aConMgr = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                aNetInfo = aConMgr.getActiveNetworkInfo();
            } catch (Exception e) {
                aNetInfo = null;
            }
        }
        return aNetInfo;
    }

    public static String getNetworkOperatorName() {
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        String operatorName = telephonyManager.getNetworkOperatorName();
        return operatorName;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //  QSCNLogger.info(TAG, "Broadcast Received --- Context is " + context + "   Intent is " + intent);
        checkNetworkConnectivity(context);
    }

    public static void setNetworkAvailable(boolean isNetworkAvailable) {
        NetworkMonitor.isNetworkAvailable = isNetworkAvailable;
    }

    public static boolean isNetworkPresent() {
        ConnectivityManager connectivityManager = (ConnectivityManager) NetworkMonitor.context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }


    public static int isNetworkFast() {
        TelephonyManager telManager;
        if (context != null) {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connManager != null) {
                NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if (mWifi != null && mWifi.isConnected()) {

                    return NETWORK_TYPE_FAST_WIFI;
                }
            }

            telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int cType = telManager.getNetworkType();
            String cTypeString;

			/*
            switch (cType) {
			case 1: cTypeString = "GPRS"; break;
			case 2: cTypeString = "EDGE"; break;
			case 3: cTypeString = "UMTS"; break;
			case 8: cTypeString = "HSDPA"; break;
			case 9: cTypeString = "HSUPA"; break;
			case 10:cTypeString = "HSPA"; break;
			default:cTypeString = "unknown"; break;
			}
			 */

            if (cType > 0 && cType < 3) {
                return NETWORK_TYPE_SLOW;
            } else if (cType > 2) {
                return NETWORK_TYPE_FAST_3G;
            }

            return NETWORK_TYPE_NO_NETWORK;
        } else {
            return -1;
        }
    }

    public static String getNetworkTypeVerbose() {
        int networkType = isNetworkFast();
        if (networkType == NETWORK_TYPE_SLOW) {
            return "2G";
        } else if (networkType == NetworkMonitor.NETWORK_TYPE_FAST_3G) {
            return "3G";
        } else if (networkType == NetworkMonitor.NETWORK_TYPE_FAST_WIFI) {
            return "WiFi";
        } else {
            return "unknown";
        }
    }


}
