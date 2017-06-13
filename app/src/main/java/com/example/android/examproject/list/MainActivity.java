package com.example.android.examproject.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.examproject.R;
import com.example.android.examproject.entities.Result;
import com.example.android.examproject.entities.User;
import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UserListContract.View{

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

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

        presenter = new UserListPresenter(this);
    }

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
    public void showErrorMessage(String error) {
        Log.d(TAG, "onFailure: " + error);
        Toast.makeText(this, "Error retreiving data.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserList(ArrayList<UserInfo> listInfo) {
        updateRecyclerAdapter(listInfo);
    }

    private void updateRecyclerAdapter(ArrayList<UserInfo> list) {
        UserRecyclerAdapter recyclerAdapter = new UserRecyclerAdapter(list, this);
        recyclerView.setAdapter(recyclerAdapter);
//        recyclerAdapter.notifyDataSetChanged();
    }

//    private void PopulateUserList() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RETROFIT_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RetrofitService service = retrofit.create(RetrofitService.class);
//        Call<User> call = service.getTwentyUsers();
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {
//                if(response.isSuccessful()) {
//                    User example = response.body();
//
//                    for(Result r: example.getResults()) {
//                        final String userName = helper.getNameString(r);
//                        final String userAddress = helper.getAddressString(r);
//                        final String userEmail = helper.getEmailString(r);
//
//                        listUserInfo.add(new UserInfo(userName, userAddress, userEmail));
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<User> call, Throwable t) {
//                //do stuff on failure
//                Log.d(TAG, "onFailure: " + t.getMessage());
//            }
//        });
//    }
}
