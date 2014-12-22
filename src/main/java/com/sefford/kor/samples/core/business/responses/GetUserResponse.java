package com.sefford.kor.samples.core.business.responses;

import com.google.gson.annotations.SerializedName;
import com.sefford.kor.samples.model.User;

import java.util.List;

/**
 * Created by sefford on 16/11/14.
 */
public class GetUserResponse extends InteractorResponse{

    @SerializedName("results")
    List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
