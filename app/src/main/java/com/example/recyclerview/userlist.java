package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.recyclerview.Model.User;

import java.util.List;

public class userlist extends AppCompatActivity {
 RecyclerView userlistview;
 List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        userlistview = findViewById(R.id.userlistview);
        Intent i = getIntent();
        users = (List<User>) i.getSerializableExtra("allusers");
        userAdapter adapter = new userAdapter(this,users);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userlistview.setLayoutManager(layoutManager);
        userlistview.setAdapter(adapter);

    }
}
