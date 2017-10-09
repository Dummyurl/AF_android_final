package com.LeelaGroup.AgrawalFedration.business;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.Business_Pojo.My_Add_Card_Pojo;
import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.Network.ApiClient;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.Service.Business_ServiceAPI;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USer on 18-07-2017.
 */

public class Business_My_Add_Adapter extends RecyclerView.Adapter<Business_My_Add_Adapter.MyAddHolder> {

    private ArrayList<My_Add_Card_Pojo> arrayList;
    private Context context;


    public Business_My_Add_Adapter(ArrayList<My_Add_Card_Pojo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyAddHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_add_row_layout, parent, false);
        return new MyAddHolder(layoutView, context, arrayList);
    }

    @Override
    public void onBindViewHolder(MyAddHolder holder, int position) {
        holder.tv_card_add_name.setText(arrayList.get(position).getName());
        holder.tv_card_contact_no.setText(arrayList.get(position).getMobile());
        holder.tv_card_email.setText(arrayList.get(position).getEmail());
        holder.tv_card_location.setText(arrayList.get(position).getCityName());
        Glide.with(context).load(arrayList.get(position).getLogo()).into(holder.imageView);
        // notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static final class MyAddHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_card_add_name, tv_card_contact_no, tv_card_email, tv_card_location, tv_card_view_add, tv_card_edit, tv_card_delete;
        ImageView imageView;
        ArrayList<My_Add_Card_Pojo> arrayList;
        Context context;
        LinearLayout layout;

        public MyAddHolder(View itemView, Context context, ArrayList<My_Add_Card_Pojo> arrayList) {
            super(itemView);

            this.arrayList = arrayList;
            this.context = context;

            Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
            TextView button = (TextView) itemView.findViewById(R.id.u_phone);
            TextView t1 = (TextView) itemView.findViewById(R.id.email_id);
            TextView t2 = (TextView) itemView.findViewById(R.id.location);


            button.setTypeface(font);
            t1.setTypeface(font);
            t2.setTypeface(font);

            layout=(LinearLayout)itemView.findViewById(R.id.parent);

            imageView = (ImageView) itemView.findViewById(R.id.img_card);

            tv_card_add_name = (TextView) itemView.findViewById(R.id.tv_card_add_name);
            tv_card_contact_no = (TextView) itemView.findViewById(R.id.tv_card_contact_no);
            tv_card_email = (TextView) itemView.findViewById(R.id.tv_card_email);
            tv_card_location = (TextView) itemView.findViewById(R.id.tv_card_location);

            tv_card_view_add = (TextView) itemView.findViewById(R.id.tv_card_view_add);
           /* tv_card_edit = (TextView) itemView.findViewById(R.id.tv_card_edit);*/
            tv_card_delete = (TextView) itemView.findViewById(R.id.tv_card_delete);

            tv_card_view_add.setOnClickListener(this);
           /* tv_card_edit.setOnClickListener(this);*/
            tv_card_delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_card_view_add: {
                    if (MyUtility.isConnected(context)){

                        int position = getAdapterPosition();
                        My_Add_Card_Pojo pojo = this.arrayList.get(position);
                        Intent intent = new Intent(this.context, Business_View_Click_Activity.class);
                        intent.putExtra("ar_id", pojo.getArId());
                        this.context.startActivity(intent);
                    }else {
                        MyUtility.internetProblem(layout);
                    }
                    break;
                }
               /* case R.id.tv_card_edit: {

                    Intent intent = new Intent(this.context, Businees_Edit_My_Add.class);
                    this.context.startActivity(intent);
                    break;
                }*/
                case R.id.tv_card_delete: {
                    if (MyUtility.isConnected(context)) {
                        final int position = getAdapterPosition();
                        final My_Add_Card_Pojo pojo1 = this.arrayList.get(position);

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are You Sure To Delete????")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Business_ServiceAPI services = ApiClient.getRetrofit().create(Business_ServiceAPI.class);
                                        Call<My_Add_Card_Pojo> call = services.deleteCard(pojo1.getArId());
                                        call.enqueue(new Callback<My_Add_Card_Pojo>() {
                                            @Override
                                            public void onResponse(Call<My_Add_Card_Pojo> call, Response<My_Add_Card_Pojo> response) {
                                                My_Add_Card_Pojo pojo = response.body();
                                                Intent intent = new Intent(context, Business_My_Add.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                context.startActivity(intent);
                                                //Toast.makeText(context, pojo.getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(Call<My_Add_Card_Pojo> call, Throwable t) {
                                                // Toast.makeText(context,"FAil", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setTitle("Alert");
                        alertDialog.show();
                    }
                    else {
                        MyUtility.internetProblem(layout);
                    }
                }


                break;
            }

        }

    }


}

