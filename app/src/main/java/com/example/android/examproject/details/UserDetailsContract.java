package com.example.android.examproject.details;

import android.os.Bundle;

import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;

/**
 * Created by Android on 6/13/2017.
 */

public interface UserDetailsContract {
    interface View {
        void DisplayUserData(String url, String name, String address, String email);

        void ErrorDisplayingUserData();
    }

    interface Presenter {
        void GetUserData(Bundle b);
    }
}
