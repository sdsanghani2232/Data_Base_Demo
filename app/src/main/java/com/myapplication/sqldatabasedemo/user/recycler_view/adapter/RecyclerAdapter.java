package com.myapplication.sqldatabasedemo.user.recycler_view.adapter;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.R;
import com.myapplication.sqldatabasedemo.user.other.UpdateActivity;
import com.myapplication.sqldatabasedemo.user.other.UserDetail;
import com.myapplication.sqldatabasedemo.user.recycler_view.UserListRecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    static ArrayList<Integer>  id;
    ArrayList<Integer> imgId;
    ArrayList<String>  name,email,contact;
    static Context context;

    public RecyclerAdapter(ArrayList<Integer> id, ArrayList<String>  name, ArrayList<String>  email, ArrayList<String> contact, ArrayList<Integer> imgId,Context context) {
        RecyclerAdapter.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.imgId = imgId;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.userid.setText(id.get(position).toString());
        holder.username.setText(name.get(position));
        holder.usercontact.setText(contact.get(position));
        holder.useremail.setText(email.get(position));
        holder.DPImg.setImageResource(imgId.get(position));
    }
    @Override
    public int getItemCount() {
        return id.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userid,username,useremail,usercontact;
        public ImageButton delete,update;
        public CircleImageView DPImg;
        SQLiteDatabase db;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userid = itemView.findViewById(R.id.userid);
            username =itemView.findViewById(R.id.name);
            useremail = itemView.findViewById(R.id.email);
            usercontact = itemView.findViewById(R.id.mobile);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);
            DPImg = itemView.findViewById(R.id.person_img);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);

            delete.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    db = context.openOrCreateDatabase("profile",MODE_PRIVATE,null);
                    db.execSQL("DELETE FROM inforamtion WHERE person_id = '"+userid.getText().toString()+"'");
                    int index = id.indexOf(Integer.parseInt(userid.getText().toString()));
                    id.remove(index);
                    notifyDataSetChanged();
                    Intent i = new Intent(context, UserListRecyclerView.class);
                    startActivity(context,i,null);
                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, UpdateActivity.class);
                    i.putExtra("id",Integer.parseInt(userid.getText().toString()));
                    startActivity(context,i,null);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, UserDetail.class);
                    i.putExtra("id",Integer.parseInt(userid.getText().toString()));
                    startActivity(context,i,null);

                }
            });
        }
    }
}
