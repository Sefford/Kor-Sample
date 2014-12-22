package com.sefford.kor.samples.core.business.delegates;


import com.sefford.kor.interactors.interfaces.UpdateableDelegate;
import com.sefford.kor.samples.core.business.errors.ScanError;
import com.sefford.kor.samples.core.business.responses.ScanResponse;
import com.sefford.kor.samples.model.ScannedDevice;
import com.sefford.kor.samples.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sefford on 3/11/14.
 */
public class DeviceScanDelegate implements UpdateableDelegate<ScanResponse, ScanError> {

    public static final int MAX_IP_ADDRESS = 254;
    final NetworkUtils utils;
    int currentIp = 1;

    public DeviceScanDelegate(NetworkUtils utils) {
        this.utils = utils;
    }

    @Override
    public ScanResponse retrieveNetworkResponse() throws Exception {
        List<String> ipAddresses = utils.getIpAddress(true);
        List<String> hosts = new ArrayList<String>();
        for (String address : ipAddresses) {
            String subHost = address.substring(0, address.lastIndexOf(".") + 1);
            for (int suffix = currentIp; suffix < currentIp + 32 && suffix < MAX_IP_ADDRESS; suffix++) {
                if (utils.checkReachability(subHost + suffix)) {
                    hosts.add(subHost + suffix);
                }
            }
        }
        currentIp += 32;
        final List<ScannedDevice> deviceList = new ArrayList<ScannedDevice>();
        // We create an object from the Realm DB
        for (String address : hosts) {
            final ScannedDevice device = new ScannedDevice();
            device.setIp(address);
            device.setActive(true);
            // Wrap it on a UI model and add it to the list
            deviceList.add(device);
        }
        // Create new Response and send it
        return new ScanResponse(deviceList);
    }

    @Override
    public ScanResponse postProcess(ScanResponse scanResponse) {
        scanResponse.setSuccess(Boolean.TRUE);
        scanResponse.setFromNetwork(Boolean.TRUE);
        return scanResponse;
    }

    @Override
    public void saveToCache(ScanResponse scanResponse) {

    }

    @Override
    public ScanError composeErrorResponse(Exception e) {
        return new ScanError();
    }

    @Override
    public boolean keepLooping() {
        return currentIp < MAX_IP_ADDRESS;
    }

    @Override
    public String getInteractorName() {
        return "DeviceScanInteractor";
    }
}
