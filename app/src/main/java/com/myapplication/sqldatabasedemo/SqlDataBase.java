package com.myapplication.sqldatabasedemo;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.R;

public class SqlDataBase extends AppCompatActivity
{

    EditText id,name,sal;
    Button save,update,delete,show,show_all;
    SQLiteDatabase db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_data_base);
        getValue();
        createDataBse();

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(id.getText().toString().equals(""))
                {
                    msgalert("Alert", "Enter the Id...");
                } else if (name.getText().toString().equals(""))
                {
                    msgalert("Alert", "Enter the Name...");
                } else if ( sal.getText().toString().equals(""))
                {
                    msgalert("Alert", "Enter the Salary...");
                }
                else
                {
                    if(existsData())
                    {
                        msgalert("Alert", "Id all ready Exists..");
                    }
                    else {
                        db.execSQL("INSERT INTO empinfo VALUES ('"+id.getText().toString()+"'," +
                            "'"+name.getText().toString()+"','"+sal.getText().toString()+"')");

                        removeData();
                        msgalert("Success", "Data Inserted Successfully");
                    }
                }
            }
        });

        show_all.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                 c = db.rawQuery("SELECT * FROM empinfo",null);
                if(c.getCount() == 0)
                {
                    msgalert("Error ..","No Data Found");

                }
                else
                {
                    StringBuffer bf = new StringBuffer();
                    while (c.moveToNext())
                    {
                        bf.append("Id : " + c.getString(0) + "\n");
                        bf.append("Name : " + c.getString(1) + "\n");
                        bf.append("Salary : " + c.getString(2) + "\n\n");
                    }
                    msgalert("Information ",bf.toString());
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                c = db.rawQuery("SELECT * FROM empinfo WHERE empid = '"+id.getText().toString()+"'",null);
                removeData();
                if (id.getText().toString().equals(""))
                {
                    msgalert("Error ..","Enter the id....");
                }
                else if(c.getCount() == 0)
                {
                    msgalert("Error ..","No Data Found");
                }
                else
                {
                    c = db.rawQuery("SELECT * FROM empinfo WHERE empid = '"+id.getText().toString()+"'",null);
                    removeData();
                    StringBuffer bf = new StringBuffer();
                    while (c.moveToNext())
                    {
                        bf.append("Id : " + c.getString(0) + "\n");
                        bf.append("Name : " + c.getString(1) + "\n");
                        bf.append("Salary : " + c.getString(2) + "\n\n");
                        break;
                    }
                    msgalert("Information ",bf.toString());
                }
            }

        });

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                c = db.rawQuery("SELECT * FROM empinfo WHERE empid = '"+id.getText().toString()+"'",null);
                if(c.getCount() == 0)
                {
                    msgalert("Error ..","No Data Found");
                }
                else
                {
                    if(!name.getText().toString().equals("") && sal.getText().toString().equals(""))
                    {
                        db.execSQL("UPDATE empinfo SET empname = '"+name.getText().toString()+"' WHERE empid = '"+id.getText().toString()+"'");
                        removeData();
                        msgalert("Update","Name Update successfully");
                    }
                    else if(!sal.getText().toString().equals("") && name.getText().toString().equals(""))
                    {
                        db.execSQL("UPDATE empinfo SET empsal = '"+sal.getText().toString()+"' WHERE empid = '"+id.getText().toString()+"'");
                        removeData();
                        msgalert("Update","Salary Update successfully");
                    }
                    else
                    {
                        db.execSQL("UPDATE empinfo SET empname = '"+name.getText().toString()+"',empsal = '"+sal.getText().toString()+"' WHERE empid = '"+id.getText().toString()+"'");
                        removeData();
                        msgalert("Update","Update successfully");
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                c = db.rawQuery("SELECT * FROM empinfo WHERE empid = '"+id.getText().toString()+"'",null);
                if(c.getCount() == 0)
                {
                    msgalert("Error ..","No Data Found");
                }
                else
                {
                    db.execSQL("DELETE FROM empinfo WHERE empid = '"+id.getText().toString()+"'");
                    removeData();
                    msgalert("Delete","Delete the Data");
                }
            }
        });
    }

    private void removeData()
    {
        id.setText("");
        name.setText("");
        sal.setText("");
    }

    private boolean existsData()
    {
        c = db.rawQuery("SELECT * FROM empinfo;",null);
        while (c.moveToNext())
        {
            if(id.getText().toString().equals(c.getString(0)))
            {
                return true;
            }
        }
        return false;
    }


    private void getValue()
    {
        id = findViewById(R.id.emp_id);
        name = findViewById(R.id.emp_name);
        sal = findViewById(R.id.emp_salary);
        save = findViewById(R.id.save);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        show = findViewById(R.id.show);
        show_all = findViewById(R.id.show_all);
    }
    private void createDataBse()
    {
        db = openOrCreateDatabase("employees",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS empinfo (empid VARCHAR ,empname VARCHAR , empsal VARCHAR);");

    }
    public void msgalert(String title, String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}