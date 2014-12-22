package com.sefford.kor.samples.core.networking;

import com.sefford.kor.samples.core.business.responses.GetUserResponse;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by sefford on 16/11/14.
 */
public interface RandomUserApi {

    @GET("/")
    GetUserResponse getUsers(@Query("results") int number);
}
