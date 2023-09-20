package com.myapplication.sqldatabasedemo.login.img_select;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.R;
import com.myapplication.sqldatabasedemo.login.LoginActivity;

import java.util.HashMap;
import java.util.Map;

public class ImgSelect extends AppCompatActivity {

    GridView gridView;

    Map<Integer,Integer> imgData = new HashMap<Integer, Integer>() {
    };
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_select);

        imgData.put(0,R.drawable.itachi);
        imgData.put(1,R.drawable.kakashi);
        imgData.put(2,R.drawable.madara);
        imgData.put(3,R.drawable.naruto);
        imgData.put(4,R.drawable.sasuke);
        imgData.put(5,R.drawable.juraya);

        gridView = findViewById(R.id.select);

        db = openOrCreateDatabase("pofile", MODE_PRIVATE, null);
        ImgSelectAdepter imgSelect = new ImgSelectAdepter(imgData, this);
        gridView.setAdapter(imgSelect);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int imgselect1 = imgData.get(position);
                Intent intent = new Intent(ImgSelect.this, LoginActivity.class);
                Bundle b = new Bundle();
                b.putInt(imgData.get(position).toString(),imgselect1);
                b.putString("imgName",imgData.get(position).toString());
                intent.putExtras(b);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

