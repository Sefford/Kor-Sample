package com.sefford.kor.samples.utils;

import org.apache.http.conn.util.InetAddressUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by German Viscuso <germanviscuso@gmail.com> on 10/29/14.
 */
@Singleton
public class NetworkUtils {


    public String pingError = null;
    public static final int REACHABLE_TIMEOUT = 250;

    @Inject
    public NetworkUtils() {
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @param useIPv4 true=return ipv4, false=return ipv6
     * @return address or empty string
     */
    public List<String> getIpAddress(boolean useIPv4) {
        List<String> devices = new ArrayList<String>();
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress().toUpperCase();
                        boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        if (useIPv4) {
                            if (isIPv4)
                                devices.add(sAddr);
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 port suffix
                                devices.add(delim < 0 ? sAddr : sAddr.substring(0, delim));
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            // TODO properly log socket exception here
        }
        return devices;
    }

    /**
     * getPingStats interprets the text result of a Linux ping command
     * <p/>
     * Set pingError on error and return null
     * <p/>
     * http://en.wikipedia.org/wiki/Ping
     * <p/>
     * PING 127.0.0.1 (127.0.0.1) 56(84) bytes of data.
     * 64 bytes from 127.0.0.1: icmp_seq=1 ttl=64 time=0.251 ms
     * 64 bytes from 127.0.0.1: icmp_seq=2 ttl=64 time=0.294 ms
     * 64 bytes from 127.0.0.1: icmp_seq=3 ttl=64 time=0.295 ms
     * 64 bytes from 127.0.0.1: icmp_seq=4 ttl=64 time=0.300 ms
     * <p/>
     * --- 127.0.0.1 ping statistics ---
     * 4 packets transmitted, 4 received, 0% packet loss, time 0ms
     * rtt min/avg/max/mdev = 0.251/0.285/0.300/0.019 ms
     * <p/>
     * PING 192.168.0.2 (192.168.0.2) 56(84) bytes of data.
     * <p/>
     * --- 192.168.0.2 ping statistics ---
     * 1 packets transmitted, 0 received, 100% packet loss, time 0ms
     * <p/>
     * # ping 321321.
     * ping: unknown host 321321.
     * <p/>
     * 1. Check if output contains 0% packet loss : Branch to success -> Get stats
     * 2. Check if output contains 100% packet loss : Branch to fail -> No stats
     * 3. Check if output contains 25% packet loss : Branch to partial success -> Get stats
     * 4. Check if output contains "unknown host"
     *
     * @param s
     */
    public String getPingStats(String s) {
        if (s.contains("0% packet loss")) {
            int start = s.indexOf("/mdev = ");
            int end = s.indexOf(" ms\n", start);
            s = s.substring(start + 8, end);
            String stats[] = s.split("/");
            return stats[2];
        } else if (s.contains("100% packet loss")) {
            pingError = "100% packet loss";
            return null;
        } else if (s.contains("% packet loss")) {
            pingError = "partial packet loss";
            return null;
        } else if (s.contains("unknown host")) {
            pingError = "unknown host";
            return null;
        } else {
            pingError = "unknown error in getPingStats";
            return null;
        }
    }

    public boolean checkReachability(String host) {
        try {
            return InetAddress.getByName(host).isReachable(REACHABLE_TIMEOUT);
        } catch (IOException e) {
            return false;
        }
    }
}

