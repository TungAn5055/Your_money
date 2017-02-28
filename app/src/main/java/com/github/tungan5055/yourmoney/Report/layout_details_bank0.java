package com.github.tungan5055.yourmoney.Report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLBank.BankControl;

import java.text.DecimalFormat;

/**
 * Created by TungAn on 11/15/2016.
 */

public class layout_details_bank0 extends AppCompatActivity {
    private BankControl dao;
    TextView tienthu, tienchi, kq, txtthumax, txtchimax, txtdate, txtmoneythu, txtmoneychi, txtphandinh;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    String mon;
    DecimalFormat defomat = new DecimalFormat("#,###,###,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details_report0);
        mon = getIntent().getExtras().get("mon").toString();
        tienthu = (TextView) findViewById(R.id.tienthu);
        tienchi = (TextView) findViewById(R.id.tienchi);
        kq = (TextView) findViewById(R.id.conlai);
        txtthumax = (TextView) findViewById(R.id.txttienthumax);
        txtchimax = (TextView)findViewById(R.id.txtchimax);
        txtmoneythu = (TextView)findViewById(R.id.txtmoneythu);
        txtmoneychi = (TextView)findViewById(R.id.txtmoneychi);
        txtphandinh = (TextView)findViewById(R.id.txtphandinh);
        tienthu.setText(String.valueOf(defomat.format(getIntent().getExtras().get("thu"))));
        tienchi.setText(String.valueOf(defomat.format(getIntent().getExtras().get("chi"))));
        kq.setText(String.valueOf(defomat.format(getIntent().getExtras().get("kq"))));

        if (Integer.parseInt( getIntent().getExtras().get("kq").toString()) <= 100000 ){
            txtphandinh.setText("--> Bạn đã vung tay quá chán ở tháng này hãy xiết chặt chi tiêu hơn nữa để đảm bảo tài chính của mình. ");
        } else {
            txtphandinh.setText("--> Bạn đã quản lý khá tốt tài chính vào tháng này hãy theo dõi thường xuyên hơn nữa!");
        }
        fab1 = (FloatingActionButton) findViewById(R.id.fab_chitiet);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_home);
        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        dao = new BankControl(this);
        dao.open();
        txtthumax.setText(dao.getmaxtongthu(mon));
        txtchimax.setText(dao.getmaxtongchi(mon));
        txtmoneythu.setText(String.valueOf(defomat.format(Integer.valueOf(dao.getmaxthumoney(mon)))));
        txtmoneychi.setText(String.valueOf(defomat.format(Integer.valueOf(dao.getmaxchimoney(mon)))));
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_chitiet:
                    Intent intent0 = new Intent(layout_details_bank0.this, Layout_Baocao.class);
                    startActivity(intent0);
                    break;
                case R.id.fab_home:
                    Intent intent1 = new Intent(layout_details_bank0.this, MainActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    };
}

