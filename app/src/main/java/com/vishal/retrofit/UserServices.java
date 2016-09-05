package com.vishal.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Minuteman on 8/26/2016.
 */
public interface UserServices {

    @GET("/users")
    Call<List<User>> getUser();

    @POST("/login")
    Call<List<User>> login();



}
