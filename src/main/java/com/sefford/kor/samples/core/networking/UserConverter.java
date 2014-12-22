package com.sefford.kor.samples.core.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sefford.kor.samples.core.business.responses.GetUserResponse;
import com.sefford.kor.samples.deserializers.UserResponseDeserializer;
import retrofit.converter.GsonConverter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sefford on 16/11/14.
 */
@Singleton
public class UserConverter extends GsonConverter {
    @Inject
    public UserConverter(GsonBuilder builder) {
        super(configure(builder));
    }

    private static Gson configure(GsonBuilder builder) {
        builder.registerTypeAdapter(GetUserResponse.class, new UserResponseDeserializer());
        return builder.create();
    }


}
