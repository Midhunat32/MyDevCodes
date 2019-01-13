package com.wisilica.androdev.ui.activity.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(indices = {@Index(value = {"deviceId"}, unique = true)},tableName = "DeviceTable")
public class Devices {

    @NotNull
    @ColumnInfo(name ="devName")
    private String deviceName;

    @NotNull
    @PrimaryKey
    private String deviceId;

    @NotNull
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(@NotNull String deviceName) {
        this.deviceName = deviceName;
    }

    @NotNull
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(@NotNull String deviceId) {
        this.deviceId = deviceId;
    }
}
