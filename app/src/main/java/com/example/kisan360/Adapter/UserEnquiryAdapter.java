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

import com.example.kisan360.Model.UserEnquiryModel;
import com.example.kisan360.R;

import java.util.ArrayList;
import java.util.List;

public class UserEnquiryAdapter extends RecyclerView.Adapter<UserEnquiryAdapter.ViewHolder> {
    Context context;
    List<UserEnquiryModel> enquiryModels = new ArrayList<UserEnquiryModel>();

    public UserEnquiryAdapter(Context context, List<UserEnquiryModel> enquiryModels) {
        this.context = context;
        this.enquiryModels = enquiryModels;
    }

    @NonNull
    @Override
    public UserEnquiryAdapter.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_enquiry_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserEnquiryAdapter.ViewHolder viewHolder, int i) {
            final UserEnquiryModel userEnquiryModel=enquiryModels.get(i);
            viewHolder.tv_p_name.setText(userEnquiryModel.getName());
            viewHolder.tv_c_name.setText(userEnquiryModel.getPhone());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                alertbox.setMessage("Name :- \t"+userEnquiryModel.getName()+"\nPhone :- \t"+userEnquiryModel.getPhone()+"\nRemark :\t"+userEnquiryModel.getRemark());
                alertbox.setTitle("Enquiry Details");

                alertbox.setPositiveButton("Call User", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:'"+userEnquiryModel.getPhone()+"'"));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(callIntent);
                    }
                }).setNegativeButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertbox.show();


               /* Intent intent=new Intent(context, EnquiryDetailsActivity.class);
                intent.putExtra("customer_name")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return enquiryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_p_name,tv_c_name;
        public ViewHolder(@NonNull View view) {
            super(view);

            tv_p_name=view.findViewById(R.id.tv_p_name);
            tv_c_name=view.findViewById(R.id.tv_c_name);
        }
    }
}
