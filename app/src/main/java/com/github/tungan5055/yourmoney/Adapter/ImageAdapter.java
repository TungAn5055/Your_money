package com.github.tungan5055.yourmoney.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.tungan5055.yourmoney.R;

/**
 * Created by TungAn on 11/4/2016.
 */

public class ImageAdapter extends BaseAdapter {
    public Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }
    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageButton imageButton;

        if (convertView == null) {
            imageButton = new ImageButton(mContext);
            imageButton.setLayoutParams(new GridView.LayoutParams(150, 100));
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
            imageButton.setPadding(10, 10, 10, 10);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Toast.makeText(mContext, "ImageButton is clicked!" + id[position], Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            imageButton = (ImageButton) convertView;
        }
        imageButton.setImageResource(mThumbIds[position]);
        return imageButton;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.ic_bank_vietinbank, R.drawable.ic_bank_vietcombank,
            R.drawable.ic_bank_bidv, R.drawable.ic_bank_techcombank,
            R.drawable.ic_bank_tpbank, R.drawable.ic_bank_agribank,
    };
    public String[] id = {
            "viettinbank","viecombank",
            "bidv","techcombank",
            "tpbank","agribank",
    };
}

