package com.vishal.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Minuteman on 8/26/2016.
 */
public class UserAdapter extends BaseAdapter {

    List<User> users;
    Context context;

    public UserAdapter(Context context, List<User> objects) {
        super();
        users = objects;
        this.context = context;
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newlistView = convertView;
        if (newlistView == null) {

            newlistView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        }

        User user = getItem(position);
        TextView name = (TextView) newlistView.findViewById(R.id.id_name);
        name.setText(user.getName());

        TextView email = (TextView) newlistView.findViewById(R.id.id_Email);
        email.setText(user.getEmail());
        TextView city = (TextView) newlistView.findViewById(R.id.id_city);
        city.setText(user.getAddress().getCity());
        TextView lng = (TextView) newlistView.findViewById(R.id.lag);
        lng.setText(user.getAddress().getGeo().getLng());
        TextView lat = (TextView) newlistView.findViewById(R.id.lat);
        lat.setText(user.getAddress().getGeo().getLat());
        TextView comp = (TextView) newlistView.findViewById(R.id.compney_id);
        comp.setText(user.getCompany().toString());


        return newlistView;
    }
}
