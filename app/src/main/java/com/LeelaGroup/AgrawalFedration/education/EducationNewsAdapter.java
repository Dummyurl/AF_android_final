package com.LeelaGroup.AgrawalFedration.education;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.Education_Pojos.Article;
import com.LeelaGroup.AgrawalFedration.Education_Pojos.EducationNewsPojo;
import com.LeelaGroup.AgrawalFedration.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neeraj on 9/22/2017.
 */

public class EducationNewsAdapter extends RecyclerView.Adapter<EducationNewsAdapter.UpscMyViewHolder>  {

    private ArrayList<Article> arrayList;
    private Context context;

    public EducationNewsAdapter(ArrayList<Article> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public UpscMyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_education_news, parent, false);
        return new UpscMyViewHolder(layoutView,arrayList,context);
    }

    @Override
    public void onBindViewHolder(UpscMyViewHolder holder, int position) {

        String s=arrayList.get(position).getUrlToImage();
        if(s.contains("https:")){
            Glide.with(context).load(s.replaceFirst("https:","http:")).into(holder.imageView);
        }else{
            Glide.with(context).load(s).into(holder.imageView);
        }


        holder.title.setText(arrayList.get(position).getTitle());
        holder.description.setText(arrayList.get(position).getDescription());
        holder.time.setText(arrayList.get(position).getPublishedAt());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static final class UpscMyViewHolder extends RecyclerView.ViewHolder {

        TextView title,auther,description,time,viewmore;
        ImageView imageView;
        ArrayList<Article> arrayList;
        Context context;

        public UpscMyViewHolder(View itemView, final ArrayList<Article> arrayList, final Context context) {
            super(itemView);
            this.arrayList = arrayList;
            this.context = context;


            imageView=(ImageView)itemView.findViewById(R.id.img_news);

            title=(TextView) itemView.findViewById(R.id.tv_title);
            //auther=(TextView) itemView.findViewById(R.id.tv_author);
            description=(TextView) itemView.findViewById(R.id.tv_description);
            time=(TextView) itemView.findViewById(R.id.tv_time);
            viewmore=(TextView) itemView.findViewById(R.id.tv_view_more);

            viewmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    String uri=arrayList.get(position).getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    context.startActivity(intent);
                }
            });

        }




    }
}
