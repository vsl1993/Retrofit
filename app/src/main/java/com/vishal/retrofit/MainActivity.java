package com.vishal.retrofit;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
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
import com.vishal.retrofit.Sqlite.UserSqlite;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<User> users;
    UserDBHelper userDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUserFromNetwork();
        userDBHelper = new UserDBHelper(this);
        Cursor cursor = userDBHelper.fetchUser();
        if(cursor.moveToFirst()){

            List<UserSqlite> arrayList = new ArrayList<>();
            do{

                String name = cursor.getString(0);
                String gmail = cursor.getString(1);
                String city = cursor.getString(2);
                UserSqlite userSqlite = new UserSqlite(name,gmail,city);
                arrayList.add(userSqlite);

            }while (cursor.moveToNext());

            UserAdapter userAdapter = new UserAdapter(this,arrayList);
            ListView listView = (ListView)findViewById(R.id.list_view);
            listView.setAdapter(userAdapter);

        }

    }

    public void addUserFromNetwork(){
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
            }
        });

    }

}
