package com.github.tungan5055.yourmoney.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLBank.BankView;

/**
 * Created by an on 4/17/2016.
 */
public class CustomArrayListBank extends ArrayAdapter<BankView> {
    Context context;
    int resource;
    List<BankView> object;
    TextView txttienbienthien,txtlydo,txtngaythang;
    ImageView imageView;

    public CustomArrayListBank(Context context, int resource, List<BankView> object) {
        super(context, resource, object);
        this.context = context;
        this.resource = resource;
        this.object = object;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource,parent,false);
        DecimalFormat defomat = new DecimalFormat("#,###,###,###,###");

        txttienbienthien = (TextView) view.findViewById(R.id.txttienbienthien);
        txtlydo = (TextView) view.findViewById(R.id.txtlydo);
        txtngaythang = (TextView) view.findViewById(R.id.txtngaythang);
        imageView = (ImageView) view.findViewById(R.id.imageView6);


        txtngaythang.setText(object.get(position).getBankDate());
        txttienbienthien.setText(String.valueOf(defomat.format(object.get(position).getBankTienbt())));
        txtlydo.setText(object.get(position).getBankLydo());
        imageView.setImageResource(R.drawable.ic_home);
        return view;
    }
}
