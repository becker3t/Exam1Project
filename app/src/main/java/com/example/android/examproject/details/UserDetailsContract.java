package com.example.android.examproject.details;

import android.os.Bundle;

import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;

/**
 * Created by Android on 6/13/2017.
 */

public interface UserDetailsContract {
    interface View {
        void SetUserData(String url, String name, String address, String email);
    }

    interface Presenter {
        void GetUserData(Bundle b);
    }
}
