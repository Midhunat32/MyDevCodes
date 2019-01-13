package com.wisilica.androdev.ui.activity.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.wisilica.androdev.utility.Utils;

import java.util.List;

public class DeviceRepository {
    private DeviceDao mDeviceDao;
    private LiveData<List<Devices>> devices;
    private LiveData<Devices> specificDevice;


    DeviceRepository(Application application) {
        RoomDataBaseDevice db = RoomDataBaseDevice.getInstance(application);
        mDeviceDao = db.deviceDao();
    }


    LiveData<List<Devices>> getAllDevices() {
        devices = mDeviceDao.getAllDevics();
        return devices;
    }

    public void deleteAllData(){
        try{
            mDeviceDao.deleteAll();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void insertDevice(final Devices device) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mDeviceDao.insetDevice(device);
                    RoomActivity.dbListener.dbInsertionSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                    RoomActivity.dbListener.dbInsertionFailed();
                }
            }
        });
        thread.start();
    }

    public LiveData<Devices> getSpecificDevice(String devId) {
        specificDevice = mDeviceDao.getDSpecificDevice(devId);
        return specificDevice;
    }

}

