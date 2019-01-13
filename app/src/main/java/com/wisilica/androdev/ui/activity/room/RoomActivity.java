package com.wisilica.androdev.ui.activity.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.wisilica.androdev.R;
import com.wisilica.androdev.ui.adapter.RecyclerAdapterDevice;
import com.wisilica.androdev.utility.Utils;

import java.util.List;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener,Utils.dbListener {

    TextView tvDevices;
    EditText etDeviceId, etDeviceName;
    Button btnAdd, btnShow;
    Devices devices;
    private DeviceViewModel viewModel;
    RecyclerView rvDevices;
    RecyclerAdapterDevice mAdapter;
    static Utils.dbListener dbListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        initObjects();
        initUi();
        registerListenrs();
        viewModel.deleteAll();
    }


    private void registerListenrs() {
        btnAdd.setOnClickListener(this);
        btnShow.setOnClickListener(this);

    }

    private void initUi() {
        etDeviceId = findViewById(R.id.etDeviceId);
        etDeviceName = findViewById(R.id.etDeviceName);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        rvDevices = findViewById(R.id.rvDevices);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvDevices.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapterDevice();
        rvDevices.setAdapter(mAdapter);
    }

    private void initObjects() {
        viewModel = ViewModelProviders.of(this).get(DeviceViewModel.class);
        dbListener =this;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            addDevice();
        } else if (v.getId() == R.id.btnShow) {
            getAllDevices();
        }
    }

    private void getAllDevices() {
        viewModel.getAllDevices().observe(this, new Observer<List<Devices>>() {
            @Override
            public void onChanged(@Nullable List<Devices> devices) {
                if (devices != null ) {
                    mAdapter.setData(devices);
                }
            }
        });

        selectSpecificRow();
    }

    private void addDevice() {
        viewModel.insert(getDeviceData());
    }

    private Devices getDeviceData() {
        Devices devices = new Devices();
        devices.setDeviceId(etDeviceId.getText().toString());
        devices.setDeviceName(etDeviceName.getText().toString());
        return devices;
    }

    private void selectSpecificRow() {
        viewModel.getSpecificDevice("sony").observe(this, new Observer<Devices>() {
            @Override
            public void onChanged(@Nullable Devices devices) {
                if (devices != null)
                    System.out.println("DEVICE>>>>>>>>>" + devices.getDeviceName() + "  " + devices.getDeviceId());
            }
        });
    }

    @Override
    public void dbInsertionSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RoomActivity.this, "Insertion success", Toast.LENGTH_SHORT).show();
                etDeviceId.setText("");
                etDeviceName.setText("");
            }
        });

    }

    @Override
    public void dbInsertionFailed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RoomActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void dbFetchFailed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RoomActivity.this, "Fetch Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
