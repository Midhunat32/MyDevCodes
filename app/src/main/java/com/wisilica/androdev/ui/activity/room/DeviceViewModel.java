package com.wisilica.androdev.ui.activity.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.wisilica.androdev.utility.Utils;

import java.util.List;

public class DeviceViewModel extends AndroidViewModel {
    private DeviceRepository mRepository;
    private LiveData<List<Devices>> mAllDevices;
    private LiveData<Devices>deviceName;

    public DeviceViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DeviceRepository(application);
    }

    LiveData<List<Devices>> getAllDevices() {
        mAllDevices = mRepository.getAllDevices();
        return mAllDevices;
    }

    public void insert(Devices devices) {
        mRepository.insertDevice(devices);
    }

    public void deleteAll(){
        mRepository.deleteAllData();
    }

    LiveData<Devices> getSpecificDevice(String devId){
        deviceName = mRepository.getSpecificDevice(devId);
        return deviceName;
    }

}
