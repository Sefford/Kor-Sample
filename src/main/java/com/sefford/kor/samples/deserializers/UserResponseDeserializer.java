package com.sefford.kor.samples.deserializers;

import com.google.gson.*;
import com.sefford.kor.samples.core.business.responses.GetUserResponse;
import com.sefford.kor.samples.model.Picture;
import com.sefford.kor.samples.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sefford on 16/11/14.
 */
public class UserResponseDeserializer implements JsonDeserializer<GetUserResponse> {
    @Override
    public GetUserResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final GetUserResponse response = new GetUserResponse();
        final JsonArray root = jsonElement.getAsJsonObject().get("results").getAsJsonArray();
        final List<User> userList = new ArrayList<User>();
        for (JsonElement element : root) {
            final JsonObject jsonUser = element.getAsJsonObject().get("user").getAsJsonObject();
            final User user = new User();
            user.setUsername(jsonUser.get("username").getAsString());
            user.setPicture((Picture) jsonDeserializationContext.deserialize(jsonUser.getAsJsonObject("picture"), Picture.class));
            userList.add(user);
        }
        response.setUserList(userList);
        return response;
    }
}
