package com.example.administrator.takephotodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/9.
 */

public class GridAdapter extends BaseAdapter {
    private ArrayList<TImage> images;
    private Context mContext;

    public GridAdapter(Context context,ArrayList<TImage> images) {
        this.images = images;
        mContext=context;
    }

    @Override
    public int getCount() {
        return null==images?0:images.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_image, parent, false);
            holder.image_view= (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(new File(images.get(position).getCompressPath())).into(holder.image_view);
        return convertView;
    }
    class ViewHolder
    {
        private ImageView image_view;
    }
}
