package com.vishal.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        TextView name = (TextView)findViewById(R.id.name_detail_id);
        TextView gmail = (TextView)findViewById(R.id.gmail_detail_id);
        TextView city = (TextView)findViewById(R.id.city_id_detail);
        Intent intent = getIntent();
        name.setText(intent.getExtras().getString("name"));
        gmail.setText(intent.getExtras().getString("gmail"));
        city.setText(intent.getExtras().getString("city"));



    }
}
