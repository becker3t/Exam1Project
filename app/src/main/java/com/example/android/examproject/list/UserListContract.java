package com.example.android.examproject.list;

import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 6/13/2017.
 */

public interface UserListContract {

    interface View {
        void showDataErrorMessage();

        void showNetworkErrorMessage();

        void showUserList(List<UserInfo> listInfo);
    }

    interface Presenter {
        void populateUserList();
    }

}
