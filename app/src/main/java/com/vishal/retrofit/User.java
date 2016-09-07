package com.vishal.retrofit;

/**
 * Created by Minuteman on 8/26/2016.
 */
public class User {


    String name;
    String email;
    Address address;

    public Address getAddress() {
        return address;
    }

    public static class Address {


        String city;

        public String getCity() {
            return city;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
}
