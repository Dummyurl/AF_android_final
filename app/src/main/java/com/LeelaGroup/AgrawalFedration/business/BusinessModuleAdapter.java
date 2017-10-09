package com.LeelaGroup.AgrawalFedration.business;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessGetSet;
import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessImage;
import com.LeelaGroup.AgrawalFedration.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Adwait on 27/04/2017.
 */

public class BusinessModuleAdapter extends RecyclerView.Adapter<BusinessModuleAdapter.MyViewHolerModule> {
    private ArrayList<BusinessImage> arrayList;
    private Context context;

    public BusinessModuleAdapter(ArrayList<BusinessImage> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public MyViewHolerModule onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.b_row_module_layout, parent, false);

        return new MyViewHolerModule(layoutView,context,arrayList);
    }

    @Override
    public void onBindViewHolder(MyViewHolerModule holder, int position) {
        holder.textView.setText(arrayList.get(position).getCat_name());
        Glide.with(context).load(arrayList.get(position).getCat_img()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static final class MyViewHolerModule extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        private ArrayList<BusinessImage> arrayList;
        private Context context;

        public MyViewHolerModule(View itemView,Context context,ArrayList<BusinessImage> arrayList) {
            super(itemView);
            this.arrayList = arrayList;
            this.context = context;
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            BusinessImage businessImage=this.arrayList.get(position);
            Intent intent=new Intent(this.context,BusinessModuleClick.class);
            intent.putExtra("cat_id",businessImage.getCat_id());
            intent.putExtra("category_name",businessImage.getCat_name());
            this.context.startActivity(intent);
        }
    }
}

