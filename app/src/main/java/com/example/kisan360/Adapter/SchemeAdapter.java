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

import com.example.kisan360.Model.SchemeModel;
import com.example.kisan360.R;

import java.util.ArrayList;
import java.util.List;

public class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.ViewHolder> {
    Context context;
    List<SchemeModel> schemeModels = new ArrayList<SchemeModel>();

    public SchemeAdapter(Context context, List<SchemeModel> schemeModels) {
        this.context = context;
        this.schemeModels = schemeModels;
    }

    @NonNull
    @Override
    public SchemeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scheme_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SchemeAdapter.ViewHolder viewHolder, final int i) {
        final SchemeModel schemeModel=schemeModels.get(i);

        viewHolder.tv_title.setText(schemeModel.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = schemeModel.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return schemeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        public ViewHolder(@NonNull View view) {
            super(view);
            tv_title=view.findViewById(R.id.tv_title);
        }
    }
}
