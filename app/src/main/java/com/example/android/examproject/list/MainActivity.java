package com.example.android.examproject.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.examproject.R;
import com.example.android.examproject.data.RetrofitService;
import com.example.android.examproject.details.DetailsActivity;
import com.example.android.examproject.entities.Result;
import com.example.android.examproject.entities.User;
import com.example.android.examproject.entities.UserInfo;
import com.example.android.examproject.utils.UserInfoHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UserListContract.View, UserRecyclerAdapter.ListItemClickListener{

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    public static final String IMG_URL_TAG = "image_url";
    public static final String NAME_TAG = "name";
    public static final String ADDRESS_TAG = "address";
    public static final String EMAIL_TAG = "email";

    private static final String RETROFIT_BASE_URL ="https://randomuser.me/";

    UserListPresenter presenter;

    RecyclerView recyclerView;

    Button populateUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateUserBtn = (Button) findViewById(R.id.btnPopulateUsers);
        populateUserBtn.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);

        presenter = new UserListPresenter(this, service, new UserInfoHelper(this));
    }

//    for button click
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPopulateUsers:
                presenter.populateUserList();
                break;
            default:
                return;
        }
    }

    @Override
    public void showDataErrorMessage() {
        Log.d(TAG, "onFailure: Error retrieving network data.");
        Toast.makeText(this, "Error retrieving data.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkErrorMessage() {
        Log.d(TAG, "onFailure: Error connecting to network.");
        Toast.makeText(this, "Network error. Check internet settings", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserList(List<UserInfo> listInfo) {
        updateRecyclerAdapter(listInfo);
    }

    private void updateRecyclerAdapter(List<UserInfo> list) {
        UserRecyclerAdapter recyclerAdapter = new UserRecyclerAdapter(list, this);
        recyclerAdapter.setListItemClickListener(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    //For list item click
    @Override
    public void OnItemClick(View v, int position) {
//        Log.d(TAG, "OnItemClick: holy shit it works!!!!" + position);
        Intent i = new Intent(MainActivity.this, DetailsActivity.class);
        i.putExtras(presenter.getDataBundle(position));
        startActivity(i);
    }
}
