package com.example.android.examproject.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.examproject.R;

public class DetailsActivity extends AppCompatActivity implements UserDetailsContract.View{

    DetailsPresenter presenter;

    ImageView userImgVw;
    TextView userNameTv;
    TextView userAddressTv;
    TextView userEmailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        presenter = new DetailsPresenter(this);

        userImgVw = (ImageView) findViewById(R.id.userDetailImg);
        userNameTv = (TextView) findViewById(R.id.userNameDetailTv);
        userAddressTv = (TextView) findViewById(R.id.userAddressDetailTv);
        userEmailTv = (TextView) findViewById(R.id.userEmailDetailTv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.GetUserData(getIntent().getExtras());
    }

    @Override
    public void SetUserData(String url, String name, String address, String email) {
        Glide.with(this).load(url).into(userImgVw);
        userNameTv.setText(name);
        userAddressTv.setText(address);
        userEmailTv.setText(email);
    }
}
