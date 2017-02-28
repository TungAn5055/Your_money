package com.github.tungan5055.yourmoney.SoNo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.github.tungan5055.yourmoney.Adapter.CustomArrayListAdapter0;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;
import com.github.tungan5055.yourmoney.Thu_Chi_No.Layout_add_khoanno;

/**
 * Created by an on 5/8/2016.
 */
public class Layout_sono  extends AppCompatActivity {
    ThuChiDAO dao;
    ListView listView;
    CustomArrayListAdapter0 customArraylistAdapter0;
    List<ThuChiDTO> list;
    Layout_add_khoanno tongno;
    TextView txttong;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    DecimalFormat defomat = new DecimalFormat("#,###,###,###,###");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sono);
        listView = (ListView) findViewById(R.id.listView);
        txttong = (TextView) findViewById(R.id.txttong);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_exit);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_home);
        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);

        list = new ArrayList<ThuChiDTO>();
        dao = new ThuChiDAO(this);
        dao.open();
        //load du lieu ra list view
        list = dao.laydanhsachvay();
        customArraylistAdapter0 = new CustomArrayListAdapter0(this, R.layout.customlistview, list);
        listView.setAdapter(customArraylistAdapter0);
        customArraylistAdapter0.notifyDataSetChanged();

        String abc = String.valueOf(defomat.format(dao.getTong_no()).toString());
        txttong.append(String.valueOf(abc));
        registerForContextMenu(listView);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_exit:
                    Intent intent0 = new Intent(Layout_sono.this, Layout_add_khoanno.class);
                    startActivity(intent0);
                    break;
                case R.id.fab_home:
                    Intent intent1 = new Intent(Layout_sono.this, MainActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    };


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflaters;
        inflaters = new MenuInflater(this);
        inflaters.inflate(R.menu.menu_xoa0, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.xoa:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                ThuChiDTO thuChiDTO = list.get(menuInfo.position);
                String  chart =String.valueOf(String.valueOf(thuChiDTO.getSotien()).charAt(0));
                dao.xoano(thuChiDTO);
                customArraylistAdapter0.remove(thuChiDTO);
                customArraylistAdapter0.notifyDataSetChanged();
                break;
        }
        return true;
    }
}
