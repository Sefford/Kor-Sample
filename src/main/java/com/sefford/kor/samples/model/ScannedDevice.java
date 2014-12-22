package com.sefford.kor.samples.model;

import com.sefford.brender.interfaces.Renderable;
import com.sefford.kor.samples.R;

/**
 * Created by sefford on 16/11/14.
 */
public class ScannedDevice implements Renderable {

    String ip;
    boolean active;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int getRenderableId() {
        return R.layout.item_scanned_device;
    }
}
