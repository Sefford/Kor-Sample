package com.sefford.kor.samples.core.business.responses;


import com.sefford.kor.samples.model.ScannedDevice;

import java.util.List;

/**
 * Created by sefford on 3/11/14.
 */
public class ScanResponse extends InteractorResponse {

    final List<ScannedDevice> devices;

    public ScanResponse(List<ScannedDevice> devices) {
        this.devices = devices;
    }

    public List<ScannedDevice> getDevices() {
        return devices;
    }
}
