package com.github.tungan5055.yourmoney.Image;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.github.tungan5055.yourmoney.R;

/**
 * Created by TungAn on 11/9/2016.
 */

public class GridviewImage extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.ic_1, R.drawable.ic_2,
            R.drawable.ic_3, R.drawable.ic_3,
            R.drawable.ic_4,
            R.drawable.ic_5, R.drawable.ic_6,
            R.drawable.ic_8, R.drawable.ic_7,
            R.drawable.ic_9, R.drawable.ic_11,
            R.drawable.ic_10, R.drawable.ic_13,
            R.drawable.ic_12, R.drawable.ic_15,
            R.drawable.ic_14, R.drawable.ic_17,
            R.drawable.ic_16, R.drawable.ic_19,
            R.drawable.ic_18, R.drawable.ic_20
    };

    // Constructor
    public GridviewImage(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        imageView.setPadding(8, 8, 8, 8);
        return imageView;
    }

}