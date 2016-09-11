package com.vishal.retrofit;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vishal.retrofit.Sqlite.CustomSwipeAdapter;
import com.vishal.retrofit.Sqlite.UserDBHelper;
import com.vishal.retrofit.Sqlite.UserSqlite;

import org.w3c.dom.Text;

import java.util.List;

public class UserDetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CustomSwipeAdapter customSwipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Intent intent=getIntent();
        long position = intent.getExtras().getLong("pos");

        viewPager = (ViewPager)findViewById(R.id.view_pager_id);
        UserDBHelper userDBHelper = new UserDBHelper(this);
        List<UserSqlite> arrayList = userDBHelper.getUser();
        customSwipeAdapter = new CustomSwipeAdapter(this,arrayList);
        viewPager.setAdapter(customSwipeAdapter);
        viewPager.setCurrentItem((int) position);

    }
}
