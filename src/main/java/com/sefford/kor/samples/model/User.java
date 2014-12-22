package com.sefford.kor.samples.model;

import com.google.gson.annotations.SerializedName;
import com.sefford.brender.interfaces.Renderable;
import com.sefford.kor.repositories.interfaces.RepoElement;
import com.sefford.kor.repositories.interfaces.Updateable;
import com.sefford.kor.samples.R;

/**
 * Created by sefford on 16/11/14.
 */
public class User implements RepoElement<String>, Updateable<User>, Renderable {

    @SerializedName("username")
    String username;
    @SerializedName("picture")
    Picture picture;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String getId() {
        return username;
    }

    @Override
    public User update(User user) {
        this.username = user.username;
        this.picture = user.picture;
        return this;
    }

    @Override
    public int getRenderableId() {
        return R.layout.item_user;
    }
}
