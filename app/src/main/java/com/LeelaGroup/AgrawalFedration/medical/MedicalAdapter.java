package com.LeelaGroup.AgrawalFedration.medical;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.LeelaGroup.AgrawalFedration.Medical_Pojos.Medical;
import com.LeelaGroup.AgrawalFedration.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by USer on 07-07-2017.
 */

public class MedicalAdapter extends Adapter<MedicalAdapter.MyViewHolderMedical> {

    private List<Medical> list;
    private Context context;

    public MedicalAdapter(List<Medical> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolderMedical onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_medical_image,parent,false);
        return new MyViewHolderMedical(view,context,list);
    }

    @Override
    public void onBindViewHolder(MyViewHolderMedical holder, int position) {

        Glide.with(context).load(list.get(position).getMed_logo()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolderMedical extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        private List<Medical> list;
        private Context context;

        public MyViewHolderMedical(View itemView, Context context,List<Medical> list) {
            super(itemView);
            this.list = list;
            this.context = context;
            itemView.setOnClickListener(this);
            imageView=(ImageView) itemView.findViewById(R.id.medical_image);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Medical medical=this.list.get(position);

            Intent intent=new Intent(this.context,SearchResult_Medical.class);
            intent.putExtra("med_about",medical.getAboutBusiness1());
            intent.putExtra("med_addr",medical.getBusinessAddress1());
            intent.putExtra("med_open_time",medical.getOpenTime());
            intent.putExtra("med_close_time",medical.getCloseTime());
            intent.putExtra("med_phone",medical.getBusinessContact1());
            intent.putExtra("med_website",medical.getBusinessWebsite1());
            intent.putExtra("med_cont_name",medical.getMed_cont_name());
            intent.putExtra("med_cont_phone",medical.getMed_cont_phone());
            intent.putExtra("med_cont_desig",medical.getMed_cont_desig());
            intent.putExtra("med_cont_email",medical.getMed_cont_email());
            intent.putExtra("med_image",medical.getMed_logo());
            intent.putExtra("med_name",medical.getBusinessName1());
            intent.putExtra("flagstaus",medical.getFlagstatus());

            this.context.startActivity(intent);
        }
    }
}
