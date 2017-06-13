package com.example.android.examproject.list;

import com.example.android.examproject.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Android on 6/12/2017.
 */

public interface RetrofitService {

    @GET("api/?results=20")
    Call<User> getTwentyUsers();

}
