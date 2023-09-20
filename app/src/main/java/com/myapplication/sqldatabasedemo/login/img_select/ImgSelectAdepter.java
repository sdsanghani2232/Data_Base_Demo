package com.myapplication.sqldatabasedemo.login.img_select;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.myapplication.R;

import java.util.Map;

public class ImgSelectAdepter extends BaseAdapter {
    Map<Integer,Integer> img;
    Context context;
    public ImgSelectAdepter(Map<Integer,Integer> img, Context context) {
        this.img = img;
        this.context = context;
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.img_map,null);

        ImageView imageView = view1.findViewById(R.id.img_map);
        imageView.setImageResource(img.get(position));
        return view1;
    }
}
