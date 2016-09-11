package com.vishal.retrofit.Sqlite;

/**
 * Created by MUCHHA on 9/8/2016.
 */
public class UserSqlite {

    String name;
    String gmail;
    String city;
    public UserSqlite(String name ,String gmail,String city){
        this.name = name;
        this.gmail = gmail;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
