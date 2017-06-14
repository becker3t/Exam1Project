package com.example.android.examproject.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.android.examproject.R;
import com.example.android.examproject.entities.Result;

/**
 * Created by Android on 6/13/2017.
 */

public class UserInfoHelper {

    Context context;

    public UserInfoHelper(Context context) {
        this.context = context;
    }

    public String getNameString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(context.getApplicationContext().getString(R.string.user_name_label));
        builder.append(" ");
        builder.append(r.getName().getFirst());
        builder.append(" ");
        builder.append(r.getName().getLast());
        return builder.toString();
    }

    public String getAddressString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(context.getApplicationContext().getString(R.string.user_address_label));
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

    public String getEmailString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(context.getApplicationContext().getString(R.string.user_email_label));
        builder.append(" ");
        builder.append(r.getEmail());
        return builder.toString();
    }
}
