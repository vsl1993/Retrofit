package com.vishal.retrofit.Sqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vishal.retrofit.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MUCHHA on 9/10/2016.
 */
public class CustomSwipeAdapter extends PagerAdapter {

    List<UserSqlite> arrayData;
    private Context context;
    private LayoutInflater layoutInflater;
    @BindView(R.id.name_detail_id) TextView name;
    @BindView(R.id.gmail_detail_id) TextView gmail;
    @BindView(R.id.city_detail_id) TextView city;



    public CustomSwipeAdapter(Context context, List<UserSqlite> arrayData) {

        this.context = context;
        this.arrayData = arrayData;
    }

    @Override
    public int getCount() {
        return arrayData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);


        ButterKnife.bind(this,item_view);


//        TextView name = (TextView) item_view.findViewById(R.id.name_detail_id);
//        TextView gmail = (TextView) item_view.findViewById(R.id.gmail_detail_id);
//        TextView city = (TextView) item_view.findViewById(R.id.city_detail_id);
        name.setText(arrayData.get(position).getName());
        gmail.setText(arrayData.get(position).getGmail());
        city.setText(arrayData.get(position).getCity());
        container.addView(item_view);


        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);

    }


}
