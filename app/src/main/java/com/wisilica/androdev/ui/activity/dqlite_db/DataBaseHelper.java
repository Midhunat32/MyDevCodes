package com.wisilica.androdev.ui.activity.dqlite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="MY_DB";
    private static final String TABLE_SAMPLE="TDEVICE";
    private static final String COLUMN_DEVICE_NAME="DEVICE_NAME";
    private static final String COLUMN_DEVICE_ID="DEVICE_ID";
    private static final int Version=1;
    SQLiteDatabase sqLiteDatabase;

    public DataBaseHelper(Context context) {
        super(context,DB_NAME, null,Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+TABLE_SAMPLE+"( "+" id INTEGER primary key autoincrement," +
                ""+COLUMN_DEVICE_ID+" text not null unique,"+COLUMN_DEVICE_NAME+" text)";
         db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDeviceName(Devices devices){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEVICE_ID,devices.getDeviceId());
        values.put(COLUMN_DEVICE_NAME,devices.getDevicename());
        sqLiteDatabase.insert(TABLE_SAMPLE,null,values);
        sqLiteDatabase.close();
    }


    public List<Devices> readAllDevices(){
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select * FROM "+TABLE_SAMPLE;
        Cursor cursor =  sqLiteDatabase.rawQuery(query,null);
        List<Devices>devicesList =new ArrayList<>();
        if (cursor.moveToFirst()){
            String deviceId,deviceName;
            Devices devices;
            do {
                 deviceId = cursor.getString(1);
                 deviceName = cursor.getString(2);
                 devices = new Devices(deviceName,deviceId);
                 devicesList.add(devices);

            }while(cursor.moveToNext());
        }
        return devicesList;

    }





}
