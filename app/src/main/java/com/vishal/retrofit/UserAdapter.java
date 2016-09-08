package com.vishal.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vishal.retrofit.Sqlite.UserDBHelper;
import com.vishal.retrofit.Sqlite.UserSqlite;

import java.util.List;

/**
 * Created by Minuteman on 8/26/2016.
 */
public class UserAdapter extends BaseAdapter {

    List<UserSqlite> users;
    Context context;

    public UserAdapter(Context context, List<UserSqlite> objects) {
        super();
        users = objects;
        this.context = context;
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public UserSqlite getItem(int position) {
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

        UserSqlite user = getItem(position);
        TextView name = (TextView) newlistView.findViewById(R.id.id_name);
        name.setText(user.getName());

        TextView email = (TextView) newlistView.findViewById(R.id.id_Email);
        email.setText(user.getGmail());
        TextView city = (TextView) newlistView.findViewById(R.id.id_city);
        city.setText(user.getCity());





        return newlistView;
    }
}
