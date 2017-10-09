package com.LeelaGroup.AgrawalFedration.education;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.R;

import java.util.ArrayList;

/**
 * Created by Adwait on 04/07/2017.
 */

public class RulesAdapter extends RecyclerView.Adapter<RulesAdapter.RecyclerViewHolder> {

    ArrayList<EducationRulesContact> contact=new ArrayList<EducationRulesContact>();
    Context context;

    public RulesAdapter(ArrayList<EducationRulesContact> contact, Context context) {
        this.contact = contact;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.education_rules_card,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
      EducationRulesContact educationRulesContact=contact.get(position);
        holder.textView.setText(educationRulesContact.getRules());

    }

    @Override
    public int getItemCount() {
        return contact.size();
    }

   public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
       TextView textView;

       public RecyclerViewHolder(View itemView) {
           super(itemView);
           textView=(TextView) itemView.findViewById(R.id.info_text);
       }
   }
}
