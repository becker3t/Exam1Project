package com.example.android.examproject.list;

import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;

/**
 * Created by Android on 6/13/2017.
 */

public interface UserListContract {

    interface View {
        void showErrorMessage(String error);

        void showUserList(ArrayList<UserInfo> listInfo);
    }

    interface Presenter {
        void populateUserList();
    }

}
