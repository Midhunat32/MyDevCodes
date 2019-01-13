package com.wisilica.androdev.ui.activity.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.wisilica.androdev.ui.activity.dqlite_db.DevicesLite;

@Database(entities = {Devices.class},version = 1)
public abstract class RoomDataBaseDevice extends RoomDatabase {

    public abstract DeviceDao deviceDao();

    private static RoomDataBaseDevice INSTANCE;

    static RoomDataBaseDevice getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDataBaseDevice.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDataBaseDevice.class, "DeviceDataBaase").build();
            }
        }
        return INSTANCE;
    }
}
