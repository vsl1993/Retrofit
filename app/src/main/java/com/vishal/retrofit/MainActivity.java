package com.vishal.retrofit;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.vishal.retrofit.Sqlite.UserDBHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<User> users;
    UserAdapter javaAdapter;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.list_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final UserServices userServices = retrofit.create(UserServices.class);


        userServices.getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users = response.body();
                UserDBHelper userDBHelper = new UserDBHelper(MainActivity.this);
                userDBHelper.inserUserIntoDatabase(users);

                javaAdapter = new UserAdapter(MainActivity.this, users);
                listView.setAdapter(javaAdapter);

                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);

                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please check your Network", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setEnabled(false);
            }
        });






        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                users.remove(position);
                javaAdapter.notifyDataSetChanged();

                return true;
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                userServices.getUser();
                Toast.makeText(MainActivity.this, "Refresh", Toast.LENGTH_LONG).show();

            }
        });
    }

}
