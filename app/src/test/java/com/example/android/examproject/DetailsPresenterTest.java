package com.example.android.examproject;

import android.os.Bundle;

import com.example.android.examproject.details.DetailsPresenter;
import com.example.android.examproject.details.UserDetailsContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by Android on 6/14/2017.
 */

public class DetailsPresenterTest {

    private DetailsPresenter presenter;

    @Mock
    UserDetailsContract.View detailsView;
    @Mock
    Bundle dataBundle;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new DetailsPresenter(detailsView);
    }

    @Test
    public void failToGetUserData() {
        //execute presenter data fetch
        presenter.GetUserData(null);

        //verify correct call happens
        verify(detailsView).ErrorDisplayingUserData();
    }

    @Test
    public void confirmGetUserData() {
        //execute presenter data fetch
        presenter.GetUserData(dataBundle);

        //verify correct call happens
        verify(detailsView).DisplayUserData(null, null, null, null);
    }

}
