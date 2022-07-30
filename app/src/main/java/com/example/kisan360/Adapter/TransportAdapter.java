package com.example.kisan360.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kisan360.Model.TransportModel;
import com.example.kisan360.R;

import java.util.ArrayList;
import java.util.List;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.ViewHolder> {
    Context context;
    List<TransportModel> transportModels = new ArrayList<TransportModel>();

    public TransportAdapter(Context context, List<TransportModel> transportModels) {
        this.context = context;
        this.transportModels = transportModels;
    }

    @NonNull
    @Override
    public TransportAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transport_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransportAdapter.ViewHolder viewHolder, final int i) {
        final TransportModel transportModel=transportModels.get(i);
        viewHolder.tv_name.setText(transportModel.getName());
        viewHolder.tv_rate.setText(transportModel.getRate());
        viewHolder.tv_vehicleno.setText(transportModel.getVehicle_no());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setMessage("Are you sure want to call\t"+transportModel.getName());
                alertbox.setTitle("Transport Service");
                alertbox.setIcon(R.drawable.delivery_truck);
                alertbox.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:'"+transportModel.getPhone()+"'"));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(callIntent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertbox.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return transportModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_vehicleno,tv_rate;
        public ViewHolder(@NonNull View view) {
            super(view);

            tv_name=view.findViewById(R.id.tv_name);
            tv_vehicleno=view.findViewById(R.id.tv_vehicleno);
            tv_rate=view.findViewById(R.id.tv_rate);

        }
    }
}
