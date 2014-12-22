package com.sefford.kor.samples.core.networking;

import com.sefford.kor.samples.utils.SLog;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Error Handler handles the API Error
 */
@Singleton
public class ApiErrorHandler implements ErrorHandler {

    static final String TAG = "FeverNetwork";
    final SLog log;

    @Inject
    public ApiErrorHandler(SLog log) {
        this.log = log;
    }

    @Override
    public Throwable handleError(RetrofitError cause) {
        log.e(TAG, cause.getMessage(), cause.getCause());
        return cause.getCause();
    }
}
