package com.example.android.examproject.data;

import com.example.android.examproject.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android on 6/12/2017.
 */

public interface RetrofitService {

    @GET("api")
    Call<User> getUsers(@Query("results") int count);

}
