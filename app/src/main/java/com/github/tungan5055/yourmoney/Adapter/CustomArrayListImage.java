package com.github.tungan5055.yourmoney.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

/**
 * Created by TungAn on 11/8/2016.
 */

public class CustomArrayListImage extends ArrayAdapter<ThuChiDTO> {
    Context context;
    int resource;
    List<ThuChiDTO> object;
    TextView name;
    ImageView imageView;

    public CustomArrayListImage(Context context, int resource, List<ThuChiDTO> object) {
        super(context, resource, object);
        this.context = context;
        this.resource = resource;
        this.object =object;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource,parent,false);
        name = (TextView) view.findViewById(R.id.txt);
        imageView = (ImageView) view.findViewById((R.id.img));
        name.setText(object.get(position).getImage_name().toString());
        int imageResource = view.getResources().getIdentifier(object.get(position).getImage_source(), null, context.getPackageName());
        imageView.setImageResource(imageResource);

        return view;
    }
}