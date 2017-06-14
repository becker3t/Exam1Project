package com.example.android.examproject.details;

import android.os.Bundle;

import com.example.android.examproject.list.MainActivity;

/**
 * Created by Android on 6/13/2017.
 */

public class DetailsPresenter implements UserDetailsContract.Presenter {

    private UserDetailsContract.View view;

    public DetailsPresenter(UserDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void GetUserData(Bundle b) {
        if(b != null) {
            view.SetUserData(b.getString(MainActivity.IMG_URL_TAG), b.getString(MainActivity.NAME_TAG),
                            b.getString(MainActivity.ADDRESS_TAG), b.getString(MainActivity.EMAIL_TAG));
        }
    }
}
