package com.LeelaGroup.AgrawalFedration.medical;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.BusinessImage;
import com.LeelaGroup.AgrawalFedration.Medical_Pojos.Medical;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.business.BusinessModuleClick;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by hp on 08-Jul-17.
 */

public class TopSearchAdapter extends RecyclerView.Adapter<TopSearchAdapter.MyViewHilderTop> {

    private List<Medical> list;
    private Context context;

    public TopSearchAdapter(List<Medical> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHilderTop onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_top_search,parent,false);
        return new MyViewHilderTop(view,context,list);
    }

    @Override
    public void onBindViewHolder(MyViewHilderTop holder, int position) {

        Glide.with(context).load(list.get(position).getMed_logo()).into(holder.imageView);
        holder.Service_name.setText(list.get(position).getBusinessName1());
        holder.Service_contact.setText(list.get(position).getBusinessContact1());
        holder.Service_Address.setText(list.get(position).getBusinessAddress1());

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static  final class MyViewHilderTop extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView Service_name,Service_contact,Service_Address;
        TextView name,contact,Address;
        Typeface font;
       private Context context;
       private List<Medical> list;
        public MyViewHilderTop(View itemView ,Context context,List<Medical> list) {
            super(itemView);
            this.context=context;
            this.list=list;

            itemView.setOnClickListener(this);

            font = Typeface.createFromAsset( context.getAssets(), "fontawesome-webfont.ttf" );

            imageView=(ImageView) itemView.findViewById(R.id.imageView);

            name=(TextView) itemView.findViewById(R.id.name);
            name.setTypeface(font);
            contact=(TextView) itemView.findViewById(R.id.contact);
            contact.setTypeface(font);
            Address=(TextView) itemView.findViewById(R.id.Address);
            Address.setTypeface(font);

            Service_name=(TextView) itemView.findViewById(R.id.Service_name);
            Service_contact=(TextView) itemView.findViewById(R.id.Service_contact);
            Service_Address=(TextView) itemView.findViewById(R.id.Service_Address);


        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Medical medical=this.list.get(position);
            Intent intent=new Intent(this.context,TopSearchItemClick.class);


            intent.putExtra("flagstaus",medical.getFlagstatus());

            intent.putExtra("med_image",medical.getMed_logo());
            intent.putExtra("med_name",medical.getBusinessName1());
            intent.putExtra("med_addr",medical.getBusinessAddress1());
            intent.putExtra("med_pincode",medical.getBusinessPincode1());
            intent.putExtra("med_phone",medical.getBusinessContact1());
            intent.putExtra("med_country",medical.getCountry());
            intent.putExtra("med_state",medical.getState());
            intent.putExtra("med_city",medical.getCity());
            intent.putExtra("med_open_time",medical.getOpenTime());
            intent.putExtra("med_close_time",medical.getCloseTime());
            intent.putExtra("med_website",medical.getBusinessWebsite1());
            intent.putExtra("med_about",medical.getAboutBusiness1());


            intent.putExtra("med_cont_name",medical.getMed_cont_name());
            intent.putExtra("med_cont_phone",medical.getMed_cont_phone());
            intent.putExtra("med_cont_desig",medical.getMed_cont_desig());
            intent.putExtra("med_cont_email",medical.getMed_cont_email());

            this.context.startActivity(intent);

        }
    }
}
