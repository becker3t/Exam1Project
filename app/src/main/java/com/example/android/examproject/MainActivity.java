package com.example.android.examproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.examproject.entities.Result;
import com.example.android.examproject.entities.User;
import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final String MESSAGE_RESPONSE_CODE = "com.example.android.RESTApiPractice.MESSAGE_RESPONSE_CODE";
    private static final String MESSAGE_RESPONSE = "com.example.android.RESTApiPractice.MESSAGE_RESPONSE";
    private static final String MESSAGE_BODY = "com.example.android.RESTApiPractice.MESSAGE_BODY";
    private static final String RETROFIT_BASE_URL ="https://randomuser.me/";

    private ArrayList<UserInfo> listUserInfo;

    RecyclerView recyclerView;
    UserRecyclerAdapter recyclerAdapter;

    Button populateUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listUserInfo = new ArrayList<>();

        populateUserBtn = (Button) findViewById(R.id.btnPopulateUsers);
        populateUserBtn.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerAdapter = new UserRecyclerAdapter(listUserInfo, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPopulateUsers:
                PopulateUserList();
                break;
            default:
                return;
        }
    }

    private void PopulateUserList() {
        for (int i = 0; i < 20; i++) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RETROFIT_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitService service = retrofit.create(RetrofitService.class);
            Call<User> call = service.getExampleUser();
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {
                    if(response.isSuccessful()) {
                        User example = response.body();

                        for(Result r: example.getResults()) {
                            final String userName = getNameString(r);
                            final String userAddress = getAddressString(r);
                            final String userEmail = getEmailString(r);

                            listUserInfo.add(new UserInfo(userName, userAddress, userEmail));
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<User> call, Throwable t) {
                    //do stuff on failure
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        }
    }

    private String getNameString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(getString(R.string.user_name_label));
        builder.append(" ");
        builder.append(r.getName().getFirst());
        builder.append(" ");
        builder.append(r.getName().getLast());
        return builder.toString();
    }

    private String getAddressString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(getString(R.string.user_address_label));
        builder.append(" ");
        builder.append(r.getLocation().getStreet());
        builder.append(", ");
        builder.append(r.getLocation().getCity());
        builder.append(", ");
        builder.append(r.getLocation().getState());
        builder.append(" ");
        builder.append(r.getLocation().getPostcode());
        return builder.toString();
    }

    private String getEmailString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(getString(R.string.user_email_label));
        builder.append(" ");
        builder.append(r.getEmail());
        return builder.toString();
    }
}
