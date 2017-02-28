package com.github.tungan5055.yourmoney.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

/**
 * Created by TungAn on 11/15/2016.
 */

public class Custom_List_Search extends ArrayAdapter<ThuChiDTO> {
    Context context;
    int resource;
    List<ThuChiDTO> object;
    TextView txttenkhoan, txtsotien, txtngaythang;

    public Custom_List_Search(Context context, int resource, List<ThuChiDTO> object) {
        super(context, resource, object);
        this.context = context;
        this.resource = resource;
        this.object = object;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, parent, false);
        DecimalFormat defomat = new DecimalFormat("#,###,###,###");

        txttenkhoan = (TextView) view.findViewById(R.id.txtname);
        txtngaythang = (TextView) view.findViewById(R.id.txtdate);
        txtsotien = (TextView) view.findViewById(R.id.txtmoney);

        txttenkhoan.setText(object.get(position).getTenkhoan());
        txtsotien.setText(String.valueOf(defomat.format(object.get(position).getSotien())));
        txtngaythang.setText(object.get(position).getNgaythang().toString());

        return view;
    }
}