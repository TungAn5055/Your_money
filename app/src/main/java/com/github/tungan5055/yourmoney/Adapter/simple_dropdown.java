package com.github.tungan5055.yourmoney.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TungAn on 11/25/2016.
 */

public class simple_dropdown extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> object;
    public simple_dropdown(Activity context, ArrayList<String> object) {
        super(context, R.layout.simple_dropdown, object);
        this.context = context;
        this.object = object;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.simple_dropdown, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txttknh);
        txtTitle.setText(object.get(position));
        return rowView;
    }
}