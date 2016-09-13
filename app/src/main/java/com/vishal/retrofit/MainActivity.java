package com.vishal.retrofit;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.vishal.retrofit.Sqlite.UserDBHelper;
import com.vishal.retrofit.Sqlite.UserSqlite;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener {

    List<User> users;
    UserDBHelper userDBHelper;
    List<UserSqlite> arrayList;
    UserAdapter userAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText name, gmail, city;
    RecyclerView recyclerView;

    public static final String SEND_ARRAY_KEY = "arryalist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name_id);
        gmail = (EditText) findViewById(R.id.gmail_id);
        city = (EditText) findViewById(R.id.city_id);

        recyclerView = (RecyclerView)findViewById(R.id.recycle_view_id);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        boolean mboolean = false;

        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) {
            // do the thing for the first time
            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.commit();
            addUserFromNetwork();
        }
        getUserFromDatabase();

        swipeRefreshLayout.setOnRefreshListener(this);


    }

    public void getUserFromDatabase() {

        userDBHelper = new UserDBHelper(this);
        arrayList = userDBHelper.getUser();
        userAdapter = new UserAdapter(this, arrayList);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));







    }


    public void addUserFromNetwork() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final UserServices userServices = retrofit.create(UserServices.class);


        userServices.getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users = response.body();
                userDBHelper = new UserDBHelper(MainActivity.this);
                userDBHelper.inserUserIntoDatabase(users);
                Toast.makeText(MainActivity.this, "Users is inserted in database", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please check your Network", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setEnabled(false);
            }
        });

    }

    public void addUser(View view) {

        String name1 = name.getText().toString();
        String gmail1 = gmail.getText().toString();
        String city1 = city.getText().toString();

        if (TextUtils.isEmpty(name1) || TextUtils.isEmpty(gmail1) || TextUtils.isEmpty(city1)) {
            if (TextUtils.isEmpty(name1)) {
                name.setError("Pls add name");
            }
            if (TextUtils.isEmpty(gmail1)) {
                gmail.setError("Pls add gmail");
            }
            if (TextUtils.isEmpty(city1)) {
                city.setError("Pls add city");
            }


        } else {
            userDBHelper.inserUser(name1, gmail1, city1);
            getUserFromDatabase();
            name.setText("");
            gmail.setText("");
            city.setText("");

        }


    }

//    @Override
//    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//        userDBHelper.deleteUser(arrayList.get(position).getName(), arrayList.get(position).getGmail(), arrayList.get(position).getCity());
//        arrayList.remove(position);
//        userAdapter.notifyDataSetChanged();
//        return true;
//    }

    @Override
    public void onRefresh() {
        getUserFromDatabase();
        swipeRefreshLayout.setRefreshing(false);
    }



//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(this, UserDetailActivity.class);
//        intent.putExtra("pos", id);
//        startActivity(intent);
//
//    }
}
