package com.myapplication.sqldatabasedemo.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.R;
import com.myapplication.sqldatabasedemo.user.recycler_view.UserListRecyclerView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        SQLiteDatabase db = openOrCreateDatabase("profile",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS inforamtion (person_id INTEGER PRIMARY KEY AUTOINCREMENT,person_name VARCHAR ,person_email VARCHAR,person_contect VARCHAR,preson_about VARCHAR,imgName VARCHAR);");
        Cursor c = db.rawQuery("SELECT * FROM inforamtion",null);

        if(c.getCount() == 0 )
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }
        else
        {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, UserListRecyclerView.class);
                    startActivity(i);
                    finish();
                }
            },2000);
        }
    }
}