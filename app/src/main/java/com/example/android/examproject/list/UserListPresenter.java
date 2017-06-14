package com.example.android.examproject.list;

import android.content.Context;
import android.os.Bundle;

import com.example.android.examproject.data.RetrofitService;
import com.example.android.examproject.entities.Result;
import com.example.android.examproject.entities.User;
import com.example.android.examproject.entities.UserInfo;
import com.example.android.examproject.utils.UserInfoHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Android on 6/13/2017.
 */

public class UserListPresenter implements UserListContract.Presenter{

    private UserInfoHelper helper;

    private List<UserInfo> listUserInfo;

    private RetrofitService service;

    UserListContract.View view;

    public UserListPresenter(UserListContract.View view, RetrofitService service, UserInfoHelper helper) {
        this.view = view;
        this.service = service;
        this.helper = helper;
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
        Call<User> call = service.getUsers(20);
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
                else {
                    view.showDataErrorMessage();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                //do stuff on failure
                view.showNetworkErrorMessage();
            }
        });
    }
}
