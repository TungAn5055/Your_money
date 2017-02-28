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
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLBank.BankControl;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by TungAn on 5/16/2016.
 */
public class Layout_Baocao_Bank_VietCombank extends AppCompatActivity {
    private BankControl bankControl;
    TextView tienthu,tienchi,kq,textmon, txtsotk;
    LinearLayout setmon;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private Context mContext;
    DecimalFormat defomat = new DecimalFormat("#,###,###,###");
    int thu = 0;
    int chi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bank_baocao_vietcom);
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
    textmon = (TextView)findViewById(R.id.textmon);
    txtsotk = (TextView)findViewById(R.id.txtsotk);
    kq = (TextView)findViewById(R.id.conlai);

    setmon = (LinearLayout)this.findViewById(R.id.setmon);
    fab1 = (FloatingActionButton) findViewById(R.id.fab_chitiet);
    fab2 = (FloatingActionButton) findViewById(R.id.fab_home);
    fab1.setOnClickListener(clickListener);
    fab2.setOnClickListener(clickListener);

    txtsotk.setText(getIntent().getStringExtra("stk"));
    setmon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showFilterPopup(view);
        }
    });


    PieChart pieChart = (PieChart) findViewById(R.id.chart);
    ArrayList<Entry> entries = new ArrayList<>();
    bankControl = new BankControl(this);
    bankControl.open();
    try {
        thu = bankControl.gettien_thu_vietcom((String) textmon.getText(), getIntent().getStringExtra("stk"));
        chi = bankControl.gettien_chi_vietcom((String) textmon.getText(), getIntent().getStringExtra("stk"));
        tienthu.setText( String.valueOf(defomat.format(thu)));
        tienchi.setText( String.valueOf(defomat.format(chi)));
        kq.setText( String.valueOf(defomat.format(thu+chi)));
    } catch (NullPointerException e){
    }
    entries.add(new Entry(thu, 0));
    entries.add(new Entry(-chi, 1));
    PieDataSet dataset = new PieDataSet(entries, "# of Calls");

    ArrayList<String> labels = new ArrayList<String>();
    labels.add("Tiền thu");
    labels.add("Tiền chi");

    PieData data = new PieData(labels, dataset);
    dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
    pieChart.setDescription("Description");
    pieChart.setData(data);
    pieChart.animateY(5000);
    pieChart.setCenterTextSize(3);
    PieChart mChart = pieChart;
    mChart.setCenterText("Tài khoản ngân hàng");
    mChart.setCenterTextSize(9);
    //Mau o giua
    mChart.setDrawHoleEnabled(true);
    mChart.setHoleColor(Color.WHITE);
    bankControl.close();
}
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_home:
                    Intent intent1 = new Intent(Layout_Baocao_Bank_VietCombank.this, MainActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.fab_chitiet:
                    Intent intent2 = new Intent(Layout_Baocao_Bank_VietCombank.this, layout_details_bank0.class);
                    intent2.putExtra("mon",(String) textmon.getText() );
                    intent2.putExtra("thu",thu );
                    intent2.putExtra("chi",chi);
                    intent2.putExtra("kq",thu+chi );
                    startActivity(intent2);
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


