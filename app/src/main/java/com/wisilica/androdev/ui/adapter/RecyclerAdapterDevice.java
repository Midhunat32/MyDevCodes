package com.wisilica.androdev.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wisilica.androdev.R;
import com.wisilica.androdev.ui.activity.room.DeviceRepository;
import com.wisilica.androdev.ui.activity.room.Devices;
import org.w3c.dom.Text;

import java.util.List;

public class RecyclerAdapterDevice extends RecyclerView.Adapter<RecyclerAdapterDevice.ViewHolder> {

    List<Devices>devicesList;
    Devices devices;

    public  void setData(List<Devices>devices){
        this.devicesList =devices;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  v =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_device, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        devices = devicesList.get(i);
        viewHolder.deviceNmae.setText(devices.getDeviceName());
        viewHolder.deviceId.setText(devices.getDeviceId());
    }

    @Override
    public int getItemCount() {
        if (devicesList != null) {
            return devicesList.size();
        }else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView deviceNmae,deviceId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             deviceNmae =itemView.findViewById(R.id.tvDeviceName);
             deviceId =itemView.findViewById(R.id.tvDeviceId);
        }
    }
}
