package com.example.android.examproject.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.android.examproject.entities.Result;
import com.example.android.examproject.entities.User;
import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android on 6/13/2017.
 */

public class UserListPresenter implements UserListContract.Presenter{

    private static final String RETROFIT_BASE_URL ="https://randomuser.me/";

    private UserInfoHelper helper;

    private ArrayList<UserInfo> listUserInfo;

    UserListContract.View view;

    public UserListPresenter(UserListContract.View view) {
        this.view = view;
        helper = new UserInfoHelper((Context) view);
        listUserInfo = new ArrayList<>();
    }

    public Bundle getDataBundle (int position) {
        Bundle myBundle = new Bundle();
        myBundle.putString(MainActivity.IMG_URL_TAG, listUserInfo.get(position).getImageUrl());
        myBundle.putString(MainActivity.NAME_TAG, listUserInfo.get(position).getName());
        myBundle.putString(MainActivity.ADDRESS_TAG, listUserInfo.get(position).getAddress());
        myBundle.putString(MainActivity.EMAIL_TAG, listUserInfo.get(position).getEmail());
        return myBundle;
    }

    @Override
    public void populateUserList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<User> call = service.getTwentyUsers();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {
                if(response.isSuccessful()) {
                    User example = response.body();

                    for(Result r: example.getResults()) {
                        final String userImgUrl = r.getPicture().getMedium();
                        final String userName = helper.getNameString(r);
                        final String userAddress = helper.getAddressString(r);
                        final String userEmail = helper.getEmailString(r);

                        listUserInfo.add(new UserInfo(userImgUrl, userName, userAddress, userEmail));
                    }

                    view.showUserList(listUserInfo);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                //do stuff on failure
                view.showErrorMessage(t.getMessage());
            }
        });
    }
}
