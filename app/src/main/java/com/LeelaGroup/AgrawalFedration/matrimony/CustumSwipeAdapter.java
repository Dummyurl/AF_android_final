package com.LeelaGroup.AgrawalFedration.matrimony;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.LeelaGroup.AgrawalFedration.MyUtility;
import com.LeelaGroup.AgrawalFedration.R;
import com.LeelaGroup.AgrawalFedration.matrimony.models.SussessStoriesPojo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Neeraj on 9/2/2017.
 */

public class CustumSwipeAdapter extends PagerAdapter {
    //int [] imgResourses={R.drawable.slide1,R.drawable.slide2,R.drawable.slide4_,R.drawable.seven};
    Context context;
    LayoutInflater layoutInflater;
    LinearLayout layout;
    ArrayList<SussessStoriesPojo> arrayList;

    public CustumSwipeAdapter(ArrayList<SussessStoriesPojo> arrayList, Context context) {
        this.context = context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        final SussessStoriesPojo sussessStoriesPojo=new SussessStoriesPojo();

        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView=(ImageView)item_view.findViewById(R.id.iv);
        TextView textView=(TextView)item_view.findViewById(R.id.tv);
         layout=(LinearLayout) item_view.findViewById(R.id.sl_parent);

        //imageView.setImageResource(imgResourses[position]);
        Glide.with(context).load(arrayList.get(position).getMatSucWedPic()).into(imageView);
        textView.setText(arrayList.get(position).getMatSucGroomName()+" & "+arrayList.get(position).getMatSucBrideName());
        container.addView(item_view);
        item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyUtility.isConnected(context)){
                    SussessStoriesPojo sussessStoriesPojo1=arrayList.get(position);
                    Intent intent=new Intent(context,SuccessStoriesFullViewActivity.class);
                    intent.putExtra("pic_groom",sussessStoriesPojo1.getMatSucGroomPic());
                    intent.putExtra("pic_bride",sussessStoriesPojo1.getMatSucBridePic());
                    intent.putExtra("name_groom",sussessStoriesPojo1.getMatSucGroomName());
                    intent.putExtra("name_bride",sussessStoriesPojo1.getMatSucBrideName());
                    intent.putExtra("mr_date",sussessStoriesPojo1.getMatSucWedDate());
                    intent.putExtra("mr_loc",sussessStoriesPojo1.getMatSucLocation());
                    intent.putExtra("wed_pic",sussessStoriesPojo1.getMatSucWedPic());
                    intent.putExtra("help",sussessStoriesPojo1.getMatSucAfHelp());
                    context.startActivity(intent);
                }else {
                    MyUtility.internetProblem(layout);
                }
            }
        });
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

      /*  int virtualPosition = position % imgResourses.length;
        super.destroyItem(container, virtualPosition, object);*/
        container.removeView((LinearLayout)object);
    }

}
