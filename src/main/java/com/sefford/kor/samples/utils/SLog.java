package com.sefford.kor.samples.utils;

import android.util.Log;
import com.sefford.kor.common.interfaces.Loggable;
import retrofit.RestAdapter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Logging and Error facilities
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
@Singleton
public class SLog implements Loggable, RestAdapter.Log {


    @Inject
    public SLog() {
    }

    /**
     * Send an error log message.
     *
     * @param logtag  Used to identify the source of a log message.  It usually identifies
     *                the class or activity where the log call occurs.
     * @param message The message you would like logged.
     */
    public void e(String logtag, String message) {
        Log.e(logtag, message);
    }

    /**
     * Send a error log message and log the exception.
     *
     * @param logtag  Used to identify the source of a log message.  It usually identifies
     *                the class or activity where the log call occurs.
     * @param message The message you would like logged.
     * @param e       An exception to log
     */
    public void e(String logtag, String message, Throwable e) {
        Log.e(logtag, message, e);
    }

    /**
     * Send a debug log message.
     *
     * @param logtag  Used to identify the source of a log message.  It usually identifies
     *                the class or activity where the log call occurs.
     * @param message The message you would like logged.
     */
    public void d(String logtag, String message) {
        Log.d(logtag, message);
    }

    /**
     * Send a warn log message.
     *
     * @param logtag  Used to identify the source of a log message.  It usually identifies
     *                the class or activity where the log call occurs.
     * @param message The message you would like logged.
     */
    public void w(String logtag, String message) {
        Log.w(logtag, message);
    }

    /**
     * Send a warn log message.
     *
     * @param logtag  Used to identify the source of a log message.  It usually identifies
     *                the class or activity where the log call occurs.
     * @param message The message you would like logged.
     */
    public void w(String logtag, String message, Throwable e) {
        Log.w(logtag, message, e);
    }

    /**
     * Send a verbose log message.
     *
     * @param logtag  Used to identify the source of a log message.  It usually identifies
     *                the class or activity where the log call occurs.
     * @param message The message you would like logged.
     */
    public void v(String logtag, String message) {
        Log.v(logtag, message);
    }

    /**
     * Prints a performance Log in debugging level on the format TAG - (element): XXXms
     * <p/>
     * The number of milliseconds will be provided by the start param
     *
     * @param tag     Logging tag
     * @param element Element to analyze
     * @param start   Start time when to make the calculations from
     */
    public void printPerformanceLog(String tag, String element, long start) {
        d(tag, "(" + element + "):" + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void log(String message) {
        d("Network", message);
    }
}