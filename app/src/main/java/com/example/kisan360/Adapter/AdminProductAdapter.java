package com.example.kisan360.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kisan360.AdminProductDetailsActivity;
import com.example.kisan360.Model.ProductModel;
import com.example.kisan360.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductAdapter.ViewHolder> {
        Context context;
        List<ProductModel> productModels = new ArrayList<ProductModel>();


        public AdminProductAdapter(Context context, List<ProductModel> productModels) {
            this.context = context;
            this.productModels = productModels;
        }

        @NonNull
        @Override
        public AdminProductAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.products_list_item, viewGroup, false);
            return new AdminProductAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AdminProductAdapter.ViewHolder viewHolder, final int i) {
            final ProductModel productModel = productModels.get(i);
            viewHolder.txtProductName.setText(productModel.getName());
            viewHolder.txtContact.setText(productModel.getSeller_address());

       /* if (productModel.getStatus().equals("1")){
            viewHolder.txtStatus.setText("Available");
            viewHolder.txtStatus.setTextColor(context.getResources().getColor(android.R.color.holo_green_light));

        }else {
            viewHolder.txtStatus.setText("Sold");
            viewHolder.txtStatus.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));

        }*/


            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, AdminProductDetailsActivity.class);
                    intent.putExtra("pk_id",productModel.getPk_id());
                    intent.putExtra("p_name",productModel.getName());
                    intent.putExtra("image",productModel.getImage());
                    intent.putExtra("price",productModel.getPrice());
                    intent.putExtra("desc",productModel.getDescription());
                    intent.putExtra("phone",productModel.getPhone());
                    intent.putExtra("address",productModel.getSeller_address());
                    intent.putExtra("s_name",productModel.getSeller_name());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });



            Glide.with(context)
                    .load(productModel.getImage())
                    .into(viewHolder.ivProduct);
        }

        private void call(String phone) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:'"+phone+"'"));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(callIntent);

        }

        @Override
        public int getItemCount() {
            return productModels.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView ivProduct;
            TextView txtProductName,txtContact,txtStatus;
            public ViewHolder(@NonNull View view) {
                super(view);

                ivProduct=view.findViewById(R.id.ivProduct);
                txtProductName=view.findViewById(R.id.txtProductName);
                txtContact=view.findViewById(R.id.txtContact);
                txtStatus=view.findViewById(R.id.txtStatus);
            }
        }
    }

