package com.wisilica.androdev.ui.activity.dqlite_db;

import android.bluetooth.BluetoothClass;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.wisilica.androdev.R;

import java.util.List;

public class DataBaseActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etDeviceId,etDeviceName;
    Button btnAdd,btnShow;
    DataBaseHelper dataBaseHelper;
    Devices device;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        initObjects();
        initUi();
        registerListenrs();

    }

    private void initObjects(){
        dataBaseHelper = new DataBaseHelper(this);
    }

    private void initUi(){
        etDeviceId = findViewById(R.id.etDeviceId);
        etDeviceName = findViewById(R.id.etDeviceId);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);

    }

    private void registerListenrs(){
        btnAdd.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd){
            addDevice();
        }else if (v.getId() == R.id.btnAdd){
            getAllDevices();
        }
    }

    void addDevice(){
        String deviceId = etDeviceId.getText().toString();
        String deviceName = etDeviceName.getText().toString();
        if (!(TextUtils.isEmpty(deviceId))  && !(TextUtils.isEmpty(deviceName))){
            device.setDeviceId(deviceId);
            device.setDevicename(deviceName);
            dataBaseHelper.insertDeviceName(device);
        }else{
            Toast.makeText(this, "Invalid Fields", Toast.LENGTH_SHORT).show();
        }
    }

    void getAllDevices(){
        List<Devices>list =dataBaseHelper.readAllDevices();
        if (list!= null){
            for(Devices dev:list){
                Toast.makeText(this, "Device "+dev.getDevicename()+" "+dev.getDeviceId(), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
