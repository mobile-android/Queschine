package com.iweavesolutions.queschine.utilities;

import android.util.Log;

import java.util.Date;

/**
 * Created by bharath.simha on 05/15/16.
 */
public class QSCNLogger {

    private static final String TAG = "Logger";

    /**
     * Only log statements that are higher than LOG_LEVEL.
     * <p/>
     * Recommended settings: Debugging: Set this to Log.VERBOSE Internal builds:
     * Set this to Log.DEBUG Release builds: Set this to Log.INFO
     */
    public static final int LOG_LEVEL = Log.VERBOSE;
    private static final boolean stackTraceEnabled = true;
    //private static int flags;

    /**
     * Change this when testing in a non-android environment (ie in eclipse)
     */
    public static final boolean JVM = false;

    /**
     * Convenience, for when caller doesn't want to set a tag
     */
    public static final String DEFAULT_TAG = "Debug";

    // Note: Not threadsafe
    private static String methodName;

    // Note: Not threadsafe
    private static String className;

    public static boolean isReleaseBuild = false;

    public static int verbose(String message) {
        return verbose(DEFAULT_TAG, message);
    }

    @SuppressWarnings("unused")
    public static int verbose(String tag, String message) {

        if (LOG_LEVEL <= Log.VERBOSE) {
            if (JVM || isReleaseBuild) {
                // don't log
                return 0;
            } else {
                return Log.v(DEFAULT_TAG + tag, message);
            }
        } else {
            return 0;
        }
    }

    public static int debug(String message) {
        return debug(DEFAULT_TAG, message);
    }

    @SuppressWarnings("unused")
    public static int debug(String tag, String message) {

        if (LOG_LEVEL <= Log.DEBUG) {
            if (JVM || isReleaseBuild) {
                // don't log
                return 0;
            } else {
                return Log.d(tag, message);

            }
        } else {
            return 0;
        }
    }

    public static int info(String message) {
        return info(DEFAULT_TAG, message);
    }

    @SuppressWarnings("unused")
    public static int info(String tag, String message) {

        if (LOG_LEVEL <= Log.INFO) {
            if (JVM || isReleaseBuild) {
                // don't log
                return 0;
            } else {
                return Log.i(DEFAULT_TAG + tag, message);
            }
        } else {
            return 0;
        }
    }

    public static int warn(String message) {
        return warn(DEFAULT_TAG, message);
    }

    @SuppressWarnings("unused")
    public static int warn(String tag, String message) {

        if (LOG_LEVEL <= Log.WARN) {
            if (JVM || isReleaseBuild) {
                return 0;
                // don't log
            } else {
                return Log.w(DEFAULT_TAG + tag, message);
            }
        } else {
            return 0;
        }
    }

    public static int warn(String message, Throwable tr) {
        return warn(DEFAULT_TAG, message, tr);
    }

    @SuppressWarnings("unused")
    public static int warn(String tag, String message, Throwable tr) {

        if (LOG_LEVEL <= Log.WARN) {
            if (JVM || isReleaseBuild) {
                return 0;
                // don't log
            } else {
                return Log.w(DEFAULT_TAG + tag, message, tr);
            }
        } else {
            return 0;
        }
    }

    @SuppressWarnings("unused")
    public static int error(String tag, String message, Throwable tr) {

        if (LOG_LEVEL <= Log.ERROR) {
            if (JVM || isReleaseBuild) {
                return 0;
                // don't log
            } else {
                return Log.e(DEFAULT_TAG + tag, message, tr);
            }
        } else {
            return 0;
        }
    }

    public static int error(String tag, String msg) {

        if (LOG_LEVEL <= Log.ERROR) {
            if (JVM || isReleaseBuild) {
                return 0;
                // don't log
            } else {
                return Log.e(DEFAULT_TAG + tag, msg);
            }
        } else {
            return 0;
        }
    }

    public static int startMonitor(String message) {
        //return monitor(true, message, 0);
        return 0;
    }

    public static int stopMonitor(String message) {
        //return monitor(false, message, 0);
        return 0;
    }

    public static int monitor(boolean isStart, String message, int depth) {
//        getCallingMethodParams(depth + 1);
//        return verbose("PERF", "Class: " + className + " ## Method: "
//                + methodName + " ## " + message
//                + (isStart ? " StartedAt: " : " EndedAt:   ") + new Date());
        return 0;
    }

    public static int methodStart() {
        //return methodLogger(true, null, 0);
        return 0;
    }

    public static int methodEnd() {
        //return methodLogger(false, null, 0);
        return 0;
    }

    public static int methodStart(String message) {
        //return methodLogger(true, message, 0);
        return 0;
    }

    public static int methodEnd(String message) {
        //return methodLogger(false, message, 0);
        return 0;
    }

    // TODO: Make this work across different threads.  Right now it's a hack
    private static int methodLogger(boolean isStart, String message, int depth) {
        getCallingMethodParams(depth + 1);
        return verbose("PERF", "Class: " + className + " ## Method: "
                + methodName
                + (isStart ? " ## StartedAt: " : " ## EndedAt:   ")
                + new Date() + " ## Optional Message: "
                + (message == null ? "None" : message));
    }

    public static void getCallingMethodParams(int depth) {
        if (LOG_LEVEL <= Log.INFO && !JVM) {
            final StackTraceElement[] ste = Thread.currentThread()
                    .getStackTrace();
            methodName = ste[depth + 4/* ste.length - 1 - depth */]
                    .getMethodName(); // Thank you Tom Tresansky
            className = ste[depth + 4/* ste.length - 1 - depth */]
                    .getClassName();
        }
    }

    public static void printStackTrace(Throwable throwable) {
        if (stackTraceEnabled || !isReleaseBuild) {
            throwable.printStackTrace();
        }
    }
}
