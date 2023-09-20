package com.myapplication.sqldatabasedemo.user.recycler_view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myapplication.R;
import com.myapplication.sqldatabasedemo.login.LoginActivity;
import com.myapplication.sqldatabasedemo.user.recycler_view.adapter.RecyclerAdapter;

import java.util.ArrayList;

public class UserListRecyclerView extends AppCompatActivity {

    RecyclerView rv;
    FloatingActionButton fb;
    ArrayList<Integer> id = new ArrayList();
    ArrayList<Integer> imgId = new ArrayList();
    ArrayList<String> name = new ArrayList();
    ArrayList<String> email = new ArrayList();
    ArrayList<String> contact = new ArrayList();
    SQLiteDatabase db;
    Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_recycler_view);

        rv = findViewById(R.id.userlist);
        fb = findViewById(R.id.addData);
        db = openOrCreateDatabase("profile",MODE_PRIVATE,null);
        c = db.rawQuery("SELECT * FROM inforamtion",null);
        while (c.moveToNext())
        {
            id.add(Integer.valueOf(c.getString(0)));
            name.add(c.getString(1));
            email.add(c.getString(2));
            contact.add(c.getString(3));
            imgId.add(Integer.valueOf(c.getString(5)));
        }
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(id,name,email,contact,imgId,this);
        rv.setAdapter(recyclerAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserListRecyclerView.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}