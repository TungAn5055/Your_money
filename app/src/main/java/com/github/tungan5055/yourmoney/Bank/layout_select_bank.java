package com.github.tungan5055.yourmoney.Bank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tungan5055.yourmoney.Adapter.Custom_List;
import com.github.tungan5055.yourmoney.R;

/**
 * Created by TungAn on 11/18/2016.
 */

public class layout_select_bank extends Activity {
    ListView list;
    PopupWindow popUpWindow;
    String[] web = {
            "VietinBank",
            "VietcomBank",
            "TechcomBank",
            "AgriBank",
            "TpBank",
            "BIDV"
    } ;
    Integer[] imageId = {
            R.drawable.ic_bank_vietinbank,
            R.drawable.ic_bank_vietcombank,
            R.drawable.ic_bank_techcombank,
            R.drawable.ic_bank_agribank,
            R.drawable.ic_bank_tpbank,
            R.drawable.ic_bank_bidv
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popUpWindow = new PopupWindow(this);
        setContentView(R.layout.layout_select_bank);

        Custom_List adapter = new
                Custom_List(layout_select_bank.this, web, imageId);
        list=(ListView)findViewById(R.id.list);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch( position ) {
                    case 0:
                        Intent intent = new Intent(view.getContext(), layout_select_tknh_viettinbank.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent0 = new Intent(view.getContext(), layout_select_tknh_vietcombank.class);
                        startActivity(intent0);
                        break;
                    case 2:
                        Toast.makeText(view.getContext(), "Xin lỗi bạn phần mềm chưa hỗ trợ ngân hàng này!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(view.getContext(), "Xin lỗi bạn phần mềm chưa hỗ trợ ngân hàng này!", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(view.getContext(), "Xin lỗi bạn phần mềm chưa hỗ trợ ngân hàng này!", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(view.getContext(), "Xin lỗi bạn phần mềm chưa hỗ trợ ngân hàng này!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}





