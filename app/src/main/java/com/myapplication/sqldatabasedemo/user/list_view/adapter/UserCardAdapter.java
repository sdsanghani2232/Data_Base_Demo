package com.myapplication.sqldatabasedemo.user.list_view.adapter;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.sqldatabasedemo.user.other.UpdateActivity;
import com.myapplication.sqldatabasedemo.user.list_view.UserListActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserCardAdapter extends BaseAdapter {
    ArrayList<Integer>  id,imgId;
    ArrayList<String>  name,email,contact;
    Context context;

    LayoutInflater inflater;
    SQLiteDatabase db;
    public UserCardAdapter(ArrayList<Integer> id, ArrayList<String>  name, ArrayList<String>  email,ArrayList<String> contact,ArrayList<Integer> imgId, Context context ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.context = context;
        this.imgId = imgId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int i) {
        return id.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

//        Toast.makeText(context, ""+ id.size(), Toast.LENGTH_SHORT).show();

        db = context.openOrCreateDatabase("profile",MODE_PRIVATE,null);
        if(view == null)
        {
            Log.d("ids",id.toString());
            view = inflater.inflate(R.layout.user_card,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.userid = view.findViewById(R.id.userid);
            viewHolder.username =view.findViewById(R.id.name);
            viewHolder.useremail = view.findViewById(R.id.email);
            viewHolder.usercontact = view.findViewById(R.id.mobile);
            viewHolder.delete = view.findViewById(R.id.delete);
            viewHolder.update = view.findViewById(R.id.update);
            viewHolder.DPImg = view.findViewById(R.id.person_img);


            viewHolder.userid.setText(id.get(position).toString());
            viewHolder.username.setText(name.get(position));
            viewHolder.useremail.setText(email.get(position));
            viewHolder.usercontact.setText(contact.get(position));
            viewHolder.DPImg.setImageResource(imgId.get(position));
            view.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.execSQL("DELETE FROM inforamtion WHERE person_id = '"+viewHolder.userid.getText().toString()+"'");
                int index = id.indexOf(Integer.parseInt(viewHolder.userid.getText().toString()));
                id.remove(index);
                notifyDataSetChanged();
                Intent i = new Intent(context, UserListActivity.class);
                startActivity(context,i,null);
            }
        });

        viewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, UpdateActivity.class);
                i.putExtra("id",Integer.parseInt(viewHolder.userid.getText().toString()));
                startActivity(context,i,null);
            }
        });
        return view;
    }



    private static class ViewHolder {
        TextView userid,username,useremail,usercontact;
        ImageButton delete,update;
        CircleImageView DPImg;
    }

}
