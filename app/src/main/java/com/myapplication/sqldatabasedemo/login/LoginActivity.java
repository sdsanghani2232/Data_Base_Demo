package com.myapplication.sqldatabasedemo.login;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.myapplication.R;
import com.myapplication.sqldatabasedemo.login.img_select.ImgSelect;
import com.myapplication.sqldatabasedemo.user.recycler_view.UserListRecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    EditText name, email, contact;
    AppCompatButton save;
    ImageButton edit;
    CircleImageView DP;
    Intent i;
    String selectedImage = "2131165281";

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getIds();
        createDataBase();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(LoginActivity.this, ImgSelect.class);
                startActivityForResult(i, 1);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")) {
                    msgalret("Data empty", "Please Enter the name.. ");
                } else if (email.getText().toString().equals("")) {
                    msgalret("Data empty", "Please Enter the Email.. ");
                } else if (contact.getText().toString().equals("")) {
                    msgalret("Data empty", "Please Enter the Contact.. ");
                } else {
                    db.execSQL("INSERT INTO inforamtion (person_name,person_email,person_contect,preson_about,imgName)VALUES " +
                            "('" + name.getText().toString() + "','" + email.getText().toString() + "','" + contact.getText().toString() + "','NULL','"+selectedImage+"')");
                    removeData();
                    msgalret("Successfully", "Data successfully inserted");
                    i = new Intent(LoginActivity.this, UserListRecyclerView.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    public void msgalret(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

    private void createDataBase() {
        db = openOrCreateDatabase("profile", MODE_PRIVATE, null);
    }

    private void getIds() {
        DP = findViewById(R.id.person_img);
        edit = findViewById(R.id.edit_img);
        name = findViewById(R.id.emp_name);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        save = findViewById(R.id.save);
    }

    private void removeData() {
        name.setText("");
        email.setText("");
        contact.setText("");
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null ) {
                selectedImage = data.getStringExtra("imgName");
                int imgpath = data.getIntExtra(selectedImage,0);
                DP.setImageResource(imgpath);
            }
        }
    }
}
