package com.github.tungan5055.yourmoney.Frament;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;


public class ThreeFragment extends Fragment {
    DecimalFormat defomat = new DecimalFormat("#,###,###,###,###,###");
   private static ThreeFragment inst;
    public void onStart() {
        super.onStart();
        inst = this;
    }
    private ThuChiDAO dao;
    TextView tienthu, tienchi, kq, txtkq;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        super.onCreate(savedInstanceState);
        tienchi = (TextView) view.findViewById(R.id.tienchi);
        tienthu = (TextView) view.findViewById(R.id.tienthu);
        kq = (TextView) view.findViewById(R.id.conlai);
        txtkq = (TextView)view.findViewById(R.id.txtkq);

        PieChart pieChart = (PieChart) view.findViewById(R.id.chart);
        ArrayList<Entry> entries = new ArrayList<>();
        dao = new ThuChiDAO(view.getContext());
        dao.open();
        int thu = 0;
        int chi = 0;
        try {
            thu = dao.getTong_thu("All");
            chi = dao.getTong_chi("All");
            int tinh = thu - chi;
            if (tinh <= 100000 ){
                txtkq.setText("Hãy vào menu->báo cáo để xem chi tiết hơn! ");
            } else {
                txtkq.setText("Bạn đã quản lý khá tốt tài chính vào tháng này, hãy vào menu->báo cáo để xem chi tiết hơn!");
            }
            tienthu.setText(String.valueOf(defomat.format(thu)));
            tienchi.setText(String.valueOf(defomat.format(chi)));
            kq.setText(String.valueOf(defomat.format(thu - chi)));
        } catch (NullPointerException e) {
            tienthu.setText(String.valueOf(thu));
            tienchi.setText(String.valueOf(chi));
            kq.setText(String.valueOf(thu - chi));
        }

        entries.add(new Entry(thu, 0));
        entries.add(new Entry(chi, 1));


        PieDataSet dataset = new PieDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Khoản thu");
        labels.add("Khoản chi");


        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        pieChart.setDescription("Description");
        pieChart.setData(data);
        pieChart.animateY(5000);
        pieChart.setCenterTextSize(9);

        PieChart mChart = pieChart;
        //Mau o giua
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);
        return view;

    }
}



