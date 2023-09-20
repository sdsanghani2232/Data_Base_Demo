package com.myapplication.sqldatabasedemo.user.other;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetail extends AppCompatActivity {

    TextView id,name,email,contact,about;
    CircleImageView DPImg;
    SQLiteDatabase db;
    Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        id = findViewById(R.id.id);
        name = findViewById(R.id.emp_name);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        about = findViewById(R.id.about);
        DPImg = findViewById(R.id.person_img);
        i = getIntent();

        db = openOrCreateDatabase("profile", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM inforamtion WHERE person_id = '"+i.getIntExtra("id",0)+"'",null);

        while (c.moveToNext()) {
            id.setText(c.getString(0));
            name.setText(c.getString(1));
            email.setText(c.getString(2));
            contact.setText(c.getString(3));
            about.setText(c.getString(4));
            DPImg.setImageResource(c.getInt(5));
        }
    }
}