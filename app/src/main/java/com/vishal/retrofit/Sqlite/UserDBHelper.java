package com.vishal.retrofit.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vishal.retrofit.User;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MUCHHA on 9/5/2016.
 */
public class UserDBHelper extends SQLiteOpenHelper {


    public static final String TABLE_NAME = "user";
    public static final String NAME = "name";
    public static final String GMAIL = "gmail";
    public static final String CITY = "city";


    public static final String DATABASE_NAME = "USERINFO.DB";
    public static final int DATABASE_VERSION = 1;
    public static final String CREATE_QURY = "CREATE TABLE " + TABLE_NAME + "(" + NAME + "," + GMAIL + "," + CITY + ");";

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.v("database", "Database is created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QURY);
        Log.v("database", "table is Insertated");

    }


    public void insertUserIntoDatabase(List<User> users) {


        int i;
        for (i = 0; i < users.size(); i++) {

            String name = users.get(i).getName();
            String gmail = users.get(i).getEmail();
            String city = users.get(i).getAddress().getCity();
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(UserDBHelper.NAME, name);
            contentValues.put(UserDBHelper.GMAIL, gmail);
            contentValues.put(UserDBHelper.CITY, city);
            sqLiteDatabase.insert(UserDBHelper.TABLE_NAME, null, contentValues);
            sqLiteDatabase.close();
        }
        Log.v("database", i + "row inserted");
    }

    public void inserUser(String name, String gmail, String city) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDBHelper.NAME, name);
        contentValues.put(UserDBHelper.NAME, name);
        contentValues.put(UserDBHelper.GMAIL, gmail);
        contentValues.put(UserDBHelper.CITY, city);
        sqLiteDatabase.insert(UserDBHelper.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

    }

    public  List<UserSqlite> getUser() {
        SQLiteDatabase sqLiteDatabase1 = this.getReadableDatabase();
        List<UserSqlite> arrayList = new ArrayList<>();
        String[] projection = {NAME, GMAIL, CITY};
        Cursor cursor = sqLiteDatabase1.query(TABLE_NAME, projection, null, null, null, null, null);
        if (cursor.moveToFirst()) {

            do {

                String name = cursor.getString(0);
                String gmail = cursor.getString(1);
                String city = cursor.getString(2);
                UserSqlite userSqlite = new UserSqlite(name, gmail, city);
                arrayList.add(userSqlite);

            } while (cursor.moveToNext());


        }
        return arrayList;
    }

    public void deleteUser(String name, String gmail, String city) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String where = NAME + "= ? AND " + GMAIL + "= ? AND " + CITY + "=?";
        String[] args = {name, gmail, city};
        sqLiteDatabase.delete(TABLE_NAME, where, args);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
