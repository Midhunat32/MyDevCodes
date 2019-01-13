package com.wisilica.androdev.ui.activity.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.lang.ref.SoftReference;
import java.util.List;

@Dao
public interface DeviceDao {
    @Insert
    void insetDevice(Devices devices);

    @Query("DELETE FROM DeviceTable")
    void deleteAll();

    @Query("SELECT * FROM DeviceTable")//ORDER BY devName ASC
    LiveData<List<Devices>> getAllDevics();

    @Query("SELECT * FROM DeviceTable WHERE deviceId =:deviceName")
    LiveData<Devices> getDSpecificDevice(String deviceName);

}
