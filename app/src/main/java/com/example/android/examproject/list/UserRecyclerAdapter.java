package com.example.android.examproject.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.examproject.R;
import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;

/**
 * Created by Android on 6/12/2017.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>{

    private ArrayList<UserInfo> userList;
    private Context context;

    public UserRecyclerAdapter(ArrayList<UserInfo> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);
//        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Student currentStudent = studentList.get(position);
////        holder.studentImgView.setImageDrawable(context.getResources().getDrawable(R.mipmap.img_the_donald));
//        holder.studentImgView.setImageResource(R.mipmap.img_the_donald);
//        holder.studentNameTv.setText(currentStudent.getStudentName());
        UserInfo currentUser = userList.get(position);
        holder.userNameTv.setText(currentUser.getName());
        holder.userAddressTv.setText(currentUser.getAddress());
        holder.userEmailTv.setText(currentUser.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

//    @Override
//    public void onClick(View v) {
//        int itemPosition = v.getChildLayoutPosition(view);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTv;
        TextView userAddressTv;
        TextView userEmailTv;

        public ViewHolder(View v) {
            super(v);
            userNameTv = (TextView) v.findViewById(R.id.userNameRecycleTv);
            userAddressTv = (TextView) v.findViewById(R.id.userAddressRecycleTv);
            userEmailTv = (TextView) v.findViewById(R.id.userEmailRecycleTv);
        }
    }
}
