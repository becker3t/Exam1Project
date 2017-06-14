package com.example.android.examproject;

import com.example.android.examproject.data.RetrofitService;
import com.example.android.examproject.entities.Result;
import com.example.android.examproject.entities.User;
import com.example.android.examproject.entities.UserInfo;
import com.example.android.examproject.list.UserListContract;
import com.example.android.examproject.list.UserListPresenter;
import com.example.android.examproject.utils.UserInfoHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Android on 6/14/2017.
 */

public class UsersPresenterTest {

    private UserListPresenter listPresenter;
    private List<UserInfo> userList;
    private User testUser;

    @Mock
    UserListContract.View userListView;
    @Mock
    RetrofitService service;
    @Mock
    Call<User> call;
    @Mock
    UserInfoHelper helper;
    @Mock
    ResponseBody responseBody;

    @Captor
    ArgumentCaptor<Callback<User>> argumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listPresenter = new UserListPresenter(userListView, service, helper);
        userList = new ArrayList<>();
        testUser = new User();
        testUser.setResults(new ArrayList<Result>(0));
    }

    @Test
    public void shouldFailGettingUsers() {
        //step 1: mock network call
        when(service.getUsers(20)).thenReturn(call);
        Response<User> response = Response.error(500, responseBody);

        //step 2: execute
        listPresenter.populateUserList();

        //step 3: verify methods work
        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, response);

        //step 4: verify interaction with view
        //in this case, view was not supposed to react
        verify(userListView).showDataErrorMessage();
    }

    @Test
    public void shouldFailNetworkRequest() {
        //1
        when(service.getUsers(20)).thenReturn(call);
        Throwable t = new Throwable(new RuntimeException());

        //2
        listPresenter.populateUserList();

        //3
        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(null, t);

        //
        verify(userListView).showNetworkErrorMessage();
    }

    @Test
    public void shouldGetUsersRequest() {
        when(service.getUsers(20)).thenReturn(call);
        Response<User> response = Response.success(testUser);

        listPresenter.populateUserList();

        verify(call).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, response);

        verify(userListView).showUserList(userList);
    }

}
