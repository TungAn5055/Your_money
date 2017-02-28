package com.github.tungan5055.yourmoney.Bank;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLBank.BankControl;
import com.github.tungan5055.yourmoney.SQLBank.BankView;

import java.util.ArrayList;

/**
 * Created by TungAn on 11/18/2016.
 */

public class layout_select_tknh_viettinbank extends FragmentActivity  {
    ListView stkListView;
    BankView bankView;
    BankControl bankControl;
    ArrayList<String> list;
    private FloatingActionButton fab2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_tknh);
        stkListView = (ListView) findViewById(R.id.list_stk);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_home);

        fab2.setOnClickListener(clickListener);
        int sdkVersion = Build.VERSION.SDK_INT;
        if(sdkVersion <= 20){
            bankView = new BankView();
            ContentResolver contentResolver = this.getContentResolver();
            Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
            int indexBody = smsInboxCursor.getColumnIndexOrThrow("body");
            int indexAddress = smsInboxCursor.getColumnIndex("address");
            int id =  smsInboxCursor.getColumnIndex("_id");
            smsInboxCursor.moveToFirst();
            do {
                if (smsInboxCursor.getCount() > 0) {
                    String data = smsInboxCursor.getString(indexAddress );
                    if (data.equals("8149") || data.equals("Viettinbank")) {
                        bankControl = new BankControl(this);
                        bankControl.open();
                        String idmess = smsInboxCursor.getString(id);
                        if (bankControl.check_id(idmess) == 0) {
                            String str = smsInboxCursor.getString(indexBody);
                            String resut[] = str.split("[|:]");
                            if(!resut[0].equals("Vietinbank")){
                                break;
                            }
                            bankControl = new BankControl(this);
                            bankControl.open();
                            BankView bankView = new BankView();
                            bankView.setBankSotk(resut[4]);
                            bankView.setBankTien(resut[8]);
                            bankView.setBankTienbt(Integer.valueOf(resut[6].replaceAll("[VND|,|+]", "")));
                            if (String.valueOf(resut[6].charAt(0)).equals("-")) {
                                bankView.setType("ra");
                            }
                            if (String.valueOf(resut[6].charAt(0)).equals("+")) {
                                bankView.setType("vao");
                            }
                            bankView.setIdsms(idmess);
                            String date[] = resut[1].split("/");
                            String date0[] = date[2].split(" ");
                            bankView.setBankDate(date0[0] + "-" + date[1] + "-" + date[0] + " " + date0[1] + ":" + resut[2]);
                            bankView.setBankLydo(resut[10]);
                            bankControl.CapnhatkVietin(bankView);
                            bankControl.close();
                        }
                        bankControl.close();
                    }
                }
            } while (smsInboxCursor.moveToNext());
        }
        else {
            int GET_MY_PERMISSION = 1;
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_SMS)
                    != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_SMS)){
                }
                else{

                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},GET_MY_PERMISSION);
                }
            }
        }
        list = new ArrayList<String>();
        bankControl = new BankControl(this);
        bankControl.open();
        list =  bankControl.laystk_vietin();
        bankControl.close();
        ArrayAdapter<String> customArraylistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,  list);
        stkListView.setAdapter((ListAdapter) customArraylistAdapter);
        if(stkListView.getAdapter().getCount() == 0){
            Toast.makeText(layout_select_tknh_viettinbank.this, "Bạn không nhận được tin nhắn nào của ngân hàng này.", Toast.LENGTH_SHORT).show();
        }
        if(stkListView.getAdapter().getCount() == 1){
            Intent intent = new Intent(layout_select_tknh_viettinbank.this, Layout_bank_vietinbank.class);
            intent.putExtra("stk",stkListView.getItemAtPosition(0).toString());
            startActivity(intent);
            stkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent intent = new Intent(layout_select_tknh_viettinbank.this, Layout_bank_vietinbank.class);
                    intent.putExtra("stk",stkListView.getItemAtPosition(position).toString());
                    startActivity(intent);
                }
            });
        } else {
            stkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent intent = new Intent(layout_select_tknh_viettinbank.this, Layout_bank_vietinbank.class);
                    intent.putExtra("stk",stkListView.getItemAtPosition(position).toString());
                    startActivity(intent);
                }
            });
        }

    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_home:
                    Intent intent = new Intent(layout_select_tknh_viettinbank.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                bankView = new BankView();
                ContentResolver contentResolver = this.getContentResolver();
                Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
                int indexBody = smsInboxCursor.getColumnIndexOrThrow("body");
                int indexAddress = smsInboxCursor.getColumnIndex("address");
                int id =  smsInboxCursor.getColumnIndex("_id");
                smsInboxCursor.moveToFirst();
                do {
                    if (smsInboxCursor.getCount() > 0) {
                        String data = smsInboxCursor.getString(indexAddress);
                        if (data.equals("8149") || data.equals("Vietinbank")) {
                            bankControl = new BankControl(this);
                            bankControl.open();
                            String idmess = smsInboxCursor.getString(id);
                            if (bankControl.check_id(idmess) == 0) {
                                String str = smsInboxCursor.getString(indexBody);
                                String resut[] = str.split("[|:]");
                                bankControl = new BankControl(this);
                                bankControl.open();
                                BankView bankView = new BankView();
                                bankView.setBankSotk(resut[4]);
                                bankView.setBankTien(resut[8]);
                                bankView.setBankTienbt(Integer.valueOf(resut[6].replaceAll("[VND|,|+]", "")));
                                if (String.valueOf(resut[6].charAt(0)).equals("-")) {
                                    bankView.setType("ra");
                                }
                                if (String.valueOf(resut[6].charAt(0)).equals("+")) {
                                    bankView.setType("vao");
                                }
                                bankView.setIdsms(idmess);
                                String date[] = resut[1].split("/");
                                String date0[] = date[2].split(" ");
                                bankView.setBankDate(date0[0] + "-" + date[1] + "-" + date[0] + " " + date0[1] + ":" + resut[2]);
                                bankView.setBankLydo(resut[10]);
                                bankControl.CapnhatkVietin(bankView);
                                bankControl.close();
                            }
                            bankControl.close();
                        }
                    }
                } while (smsInboxCursor.moveToNext());
                }
        }
    };
}
