package com.myapplication.sqldatabasedemo.user.list_view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myapplication.R;
import com.myapplication.sqldatabasedemo.login.LoginActivity;
import com.myapplication.sqldatabasedemo.user.list_view.adapter.UserCardAdapter;
import com.myapplication.sqldatabasedemo.user.other.UserDetail;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    ListView lv;
    FloatingActionButton fb;
    SQLiteDatabase db;
    ArrayList<Integer> id = new ArrayList();
    ArrayList<Integer> imgId = new ArrayList();
    ArrayList<String> name = new ArrayList();
    ArrayList<String> email = new ArrayList();
    ArrayList<String> contact = new ArrayList();
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        lv = findViewById(R.id.list);
        fb = findViewById(R.id.person_img);

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

        UserCardAdapter userCardAdapter = new UserCardAdapter(id,name,email,contact,imgId,UserListActivity.this);
        lv.setAdapter(userCardAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(UserListActivity.this, UserDetail.class);
                Toast.makeText(UserListActivity.this, ""+ id.get(position), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserListActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        c.close();
    }
}