package com.myapplication.sqldatabasedemo.user.other;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.R;
import com.myapplication.sqldatabasedemo.login.LoginActivity;
import com.myapplication.sqldatabasedemo.login.img_select.ImgSelect;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateActivity extends AppCompatActivity {

    TextView id;
    EditText name,email,contact,about;
    SQLiteDatabase db;
    LoginActivity login;
    Button update;
    Intent i = new Intent();
    CircleImageView img;
    boolean changeImg = false;
    String newImg;
    int imgpath;
    ImageButton edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        i= getIntent();
        id = findViewById(R.id.id);
        name = findViewById(R.id.emp_name);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        about = findViewById(R.id.about);
        update = findViewById(R.id.update);
        img = findViewById(R.id.person_img);
        edit = findViewById(R.id.editImg);

        db = openOrCreateDatabase("profile", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM inforamtion WHERE person_id = '"+i.getIntExtra("id",0)+"'",null);
        while (c.moveToNext()) {
            id.setText(c.getString(0));
            name.setText(c.getString(1));
            email.setText(c.getString(2));
            contact.setText(c.getString(3));
            about.setText(c.getString(4));
            img.setImageResource(c.getInt(5));
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(UpdateActivity.this, ImgSelect.class);
                startActivityForResult(i, 1);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("") || email.getText().toString().equals("") || contact.getText().toString().equals("")
                        || about.getText().toString().equals("")) {
                    login.msgalret("Data empty", "Please Enter the Data.. ");
                } else if (changeImg) {
                    db.execSQL("UPDATE inforamtion SET person_name = '" + name.getText().toString() + "'," +
                            "person_email = '" + email.getText().toString() + "',person_contect = '" + contact.getText().toString() + "'" +
                            ",preson_about = '" + about.getText().toString() + "',imgName = '"+imgpath+"' WHERE person_id = '" + id.getText().toString() + "'");
                    login.msgalret("Update", "DataUpdated.. ");

                } else {
                    db.execSQL("UPDATE inforamtion SET person_name = '" + name.getText().toString() + "'," +
                            "person_email = '" + email.getText().toString() + "',person_contect = '" + contact.getText().toString() + "'" +
                            ",preson_about = '" + about.getText().toString() + "' WHERE person_id = '" + id.getText().toString() + "'");
                    login.msgalret("Update", "DataUpdated.. ");
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null ) {
                newImg = data.getStringExtra("imgName");
                imgpath = data.getIntExtra(newImg,0);
                changeImg = true;
                img.setImageResource(imgpath);
            }
        }
    }
}