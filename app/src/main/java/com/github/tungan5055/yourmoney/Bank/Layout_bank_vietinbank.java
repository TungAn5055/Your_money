package com.github.tungan5055.yourmoney.Bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.tungan5055.yourmoney.Adapter.CustomArrayListBank;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.Report.Layout_Baocao_Bank;
import com.github.tungan5055.yourmoney.SQLBank.BankControl;
import com.github.tungan5055.yourmoney.SQLBank.BankView;

/**
 * Created by TungAn on 11/16/2016.
 */

public class Layout_bank_vietinbank extends FragmentActivity {
    ListView smsListView;
    TextView  txtnametk, txtsd;
    BankControl bankControl;
    List<BankView> list;
    CustomArrayListBank customArraylistAdapter;
    BankView bankView;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_bank_vietinbank);
        txtnametk = (TextView) findViewById(R.id.txtnametk);
        txtsd = (TextView) findViewById(R.id.txtsd);
        smsListView = (ListView) findViewById(R.id.SMSList);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_baocao);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_home);

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        bankView = new BankView();

        String stk = getIntent().getStringExtra("stk");
        txtnametk.setText(stk);
        list = new ArrayList<BankView>();
        bankControl = new BankControl(this);
        bankControl.open();
        list = bankControl.laydsbank(stk);
        txtsd.setText(bankControl.gettien().toString());
        bankControl.close();
        customArraylistAdapter = new CustomArrayListBank(this, R.layout.custombank, list);
        smsListView.setAdapter(customArraylistAdapter);
        customArraylistAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_home:
                    Intent intent = new Intent(Layout_bank_vietinbank.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fab_baocao:
                    Intent intent0 = new Intent(Layout_bank_vietinbank.this, Layout_Baocao_Bank.class);
                    intent0.putExtra("stk", getIntent().getStringExtra("stk"));
                    startActivity(intent0);
                    break;
            }
        }
    };
}