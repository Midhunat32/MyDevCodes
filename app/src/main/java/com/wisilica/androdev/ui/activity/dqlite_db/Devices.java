package com.wisilica.androdev.ui.activity.dqlite_db;

public class Devices {
    String devicename;
    String deviceId;

    public Devices(String devicename, String deviceId) {
        this.devicename = devicename;
        this.deviceId = deviceId;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
