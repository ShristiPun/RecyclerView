package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.Model.User;

import java.util.ArrayList;
import java.util.List;

public class userAdapter  extends RecyclerView.Adapter<userAdapter.MyHolder>{

    List<User> userList = new ArrayList<>();
    Context mContext;

    public userAdapter(Context mContext, List<User> userList) {
        this.mContext = mContext;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdesign,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        User user = userList.get(position);
        holder.uname.setText(user.getName());
        holder.imageView.setImageResource(user.getImage());
        holder.pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(mContext, userProfile.class);
                User user = userList.get(position);
                intent.putExtra("name",user.getName());
                intent.putExtra("image",user.getImage());
                intent.putExtra("phone",user.getPhone());
                intent.putExtra("email",user.getEmail());
                intent.putExtra("country",user.getCountry());
                intent.putExtra("dob",user.getDob());
                intent.putExtra("gender",user.getGender());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView uname;
        RelativeLayout pro;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.profile);
            uname = itemView.findViewById(R.id.username);
            pro = itemView.findViewById(R.id.pro);

        }
    }


}

