package com.github.tungan5055.yourmoney.Report;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;


import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;

/**
 * Created by TungAn on 5/16/2016.
 */
public class Layout_Baocao extends AppCompatActivity {
    private ThuChiDAO dao;
    TextView tienthu,tienchi,kq,textmon;
    LinearLayout setmon;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private Context mContext;
    DecimalFormat defomat = new DecimalFormat("#,###,###,###,###,###");
    int thu = 0;
    int chi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_baocao);
        loadActivity();
    }
    @Override
    protected void onResume(){
        loadActivity();
        super.onResume();
    }
public void loadActivity(){
    mContext = getApplicationContext();
    tienchi = (TextView)findViewById(R.id.tienchi);
    tienthu = (TextView)findViewById(R.id.tienthu);
    kq = (TextView)findViewById(R.id.conlai);
    textmon = (TextView)findViewById(R.id.textmon);
    setmon = (LinearLayout)this.findViewById(R.id.setmon);
    fab1 = (FloatingActionButton) findViewById(R.id.fab_chitiet);
    fab2 = (FloatingActionButton) findViewById(R.id.fab_home);
    fab1.setOnClickListener(clickListener);
    fab2.setOnClickListener(clickListener);

    setmon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showFilterPopup(view);
        }
    });

    PieChart pieChart = (PieChart) findViewById(R.id.chart);
    ArrayList<Entry> entries = new ArrayList<>();
    dao = new ThuChiDAO(this);
    dao.open();
    try {
        thu = dao.getTong_thu((String) textmon.getText());
        chi = dao.getTong_chi((String) textmon.getText());
        tienthu.setText( String.valueOf(defomat.format(thu)));
        tienchi.setText( String.valueOf(defomat.format(chi)));
        kq.setText( String.valueOf(defomat.format(thu-chi)));
    } catch (NullPointerException e){
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
    pieChart.setCenterTextSize(3);
    PieChart mChart = pieChart;
    mChart.setCenterText("Báo cáo thu chi");
    mChart.setCenterTextSize(9);
    //Mau o giua
    mChart.setDrawHoleEnabled(true);
    mChart.setHoleColor(Color.WHITE);
}
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_chitiet:
                    Intent intent = new Intent(getBaseContext(), layout_details_bank.class);
                    intent.putExtra("mon",(String) textmon.getText() );
                    intent.putExtra("thu",thu );
                    intent.putExtra("chi",chi);
                    intent.putExtra("kq",thu-chi );
                    startActivity(intent);
                    break;
                case R.id.fab_home:
                    Intent intent1 = new Intent(Layout_Baocao.this, MainActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    };

    private void showFilterPopup(View v) {
        final PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.popupmon, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                textmon.setText(item.getTitle());
                onResume();
                return true;
            }
        });
        popup.show();
    }

}


