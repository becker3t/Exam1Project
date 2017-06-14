package com.example.android.examproject.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.examproject.R;
import com.example.android.examproject.entities.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 6/12/2017.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>{

    private List<UserInfo> userList;
    private Context context;
    private ListItemClickListener listItemClickListener;

    public UserRecyclerAdapter(List<UserInfo> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);
//        itemView.setOnClickListener();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserInfo currentUser = userList.get(position);
//        holder.userImgVw.setImage(currentUser.getImageUrl());
        Glide.with(context).load(currentUser.getImageUrl()).into(holder.userImgVw);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView userImgVw;
        TextView userNameTv;
        TextView userAddressTv;
        TextView userEmailTv;

        public ViewHolder(View v) {
            super(v);
            userImgVw = (ImageView) v.findViewById(R.id.userImg);
            userNameTv = (TextView) v.findViewById(R.id.userNameRecycleTv);
            userAddressTv = (TextView) v.findViewById(R.id.userAddressRecycleTv);
            userEmailTv = (TextView) v.findViewById(R.id.userEmailRecycleTv);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listItemClickListener != null) {
                listItemClickListener.OnItemClick(v, getAdapterPosition());
            }
        }
    }

    public void setListItemClickListener (ListItemClickListener listener) {
        this.listItemClickListener = listener;
    }

    public interface ListItemClickListener {
        void OnItemClick(View v, int position);
    }
}
