package com.vishal.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vishal.retrofit.Sqlite.UserDBHelper;
import com.vishal.retrofit.Sqlite.UserSqlite;

import org.w3c.dom.UserDataHandler;

import java.util.Collections;
import java.util.List;

/**
 * Created by Minuteman on 8/26/2016.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>  {

    LayoutInflater layoutInflater;
    List<UserSqlite> arraylist=Collections.emptyList();
    Context context;
    UserAdapter userAdapter;



    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView name,gmail,city;
        View mView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            name = (TextView)itemView.findViewById(R.id.id_name);
            gmail = (TextView)itemView.findViewById(R.id.id_Email);
            city = (TextView)itemView.findViewById(R.id.id_city);


        }

    }










    public UserAdapter(Context context,List<UserSqlite> arraylist){
        this.arraylist = arraylist;
        layoutInflater =LayoutInflater.from(context);



    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =  layoutInflater.inflate(R.layout.list_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.name.setText(arraylist.get(position).getName());
        holder.gmail.setText(arraylist.get(position).getGmail());
        holder.city.setText(arraylist.get(position).getCity());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("res",holder.getLayoutPosition());
                context.startActivity(intent);
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int position = holder.getAdapterPosition();
                UserDBHelper userDBHelper = new UserDBHelper(v.getContext());
                userDBHelper.deleteUser(arraylist.get(position).getName(), arraylist.get(position).getGmail(), arraylist.get(position).getCity());
                arraylist.remove(position);
                notifyDataSetChanged();

                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }



//    List<UserSqlite> users;
//    Context context;
//
//    public UserAdapter(Context context, List<UserSqlite> objects) {
//        super();
//        users = objects;
//        this.context = context;
//    }
//
//
//    @Override
//    public int getCount() {
//        return users.size();
//    }
//
//    @Override
//    public UserSqlite getItem(int position) {
//        return users.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//        if (convertView == null) {
//
//            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
//
//        }
//
//        UserSqlite user = getItem(position);
//        TextView name = (TextView) convertView.findViewById(R.id.id_name);
//        name.setText(user.getName());
//
//        TextView email = (TextView) convertView.findViewById(R.id.id_Email);
//        email.setText(user.getGmail());
//        TextView city = (TextView) convertView.findViewById(R.id.id_city);
//        city.setText(user.getCity());
//
//
//
//
//
//        return convertView;
//    }
}
