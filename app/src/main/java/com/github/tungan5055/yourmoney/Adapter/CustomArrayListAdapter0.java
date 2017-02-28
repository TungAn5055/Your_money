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
 * Created by an on 4/17/2016.
 */
public class CustomArrayListAdapter0 extends ArrayAdapter<ThuChiDTO> {
    Context context;
    int resource;
    List<ThuChiDTO> object;
    TextView txttenkhoan,txtsotien,txtngaythang;
    DecimalFormat defomat = new DecimalFormat("#,###,###,###,###");
    public CustomArrayListAdapter0(Context context, int resource, List<ThuChiDTO> object) {
        super(context, resource, object);
        this.context = context;
        this.resource = resource;
        this.object =object;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource,parent,false);

        txttenkhoan = (TextView) view.findViewById(R.id.txttenkhoanthu);
        txtsotien = (TextView) view.findViewById(R.id.txtsotien);
       txtngaythang = (TextView) view.findViewById(R.id.txtngaythang);


        txtngaythang.setText(object.get(position).getNgaythang());
        txttenkhoan.setText(object.get(position).getTenkhoan().toString());
        txtsotien.setText(String.valueOf(defomat.format(object.get(position).getSotien())));
        return view;
    }
}
