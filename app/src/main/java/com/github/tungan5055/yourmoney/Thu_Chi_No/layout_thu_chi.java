package com.github.tungan5055.yourmoney.Thu_Chi_No;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.tungan5055.yourmoney.Adapter.CustomArrayListAdapter;
import com.github.tungan5055.yourmoney.Edit.layout_edit_add;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TungAn on 11/24/2016.
 */

public class layout_thu_chi  extends Activity {

    ThuChiDAO dao;
    ListView listView;
    CustomArrayListAdapter customArraylistAdapter;
    List<ThuChiDTO> list;
    String ghichu, type;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private FloatingActionButton fab4;
    DecimalFormat defomat = new DecimalFormat("#,###,###,###,###,###");
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thu_chi);

        fab1 = (FloatingActionButton) findViewById(R.id.fab_thu);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_chi);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_no);
        fab4 = (FloatingActionButton) findViewById(R.id.fab_chitiet);

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);
        fab4.setOnClickListener(clickListener);

        listView = (ListView)findViewById(R.id.listView);
        list = new ArrayList<ThuChiDTO>();
        dao = new ThuChiDAO(this);
        dao.open();
        //load du lieu ra list view
        list = dao.laydanhsachthuchi();
        customArraylistAdapter = new CustomArrayListAdapter(this, R.layout.customlistview, list);
        listView.setAdapter(customArraylistAdapter);
        customArraylistAdapter.notifyDataSetChanged();
        registerForContextMenu(listView);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflaters;
        inflaters = new MenuInflater(this);
        inflaters.inflate(R.menu.menu_xoa, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.xoa:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                ThuChiDTO thuChiDTO = list.get(menuInfo.position);
                String  chart =String.valueOf(String.valueOf(thuChiDTO.getSotien()).charAt(0));
                if (chart.equals("-")) {
                    dao.xoachi(thuChiDTO);
                } else {
                    dao.xoathu(thuChiDTO);
                }
                customArraylistAdapter.remove(thuChiDTO);
                customArraylistAdapter.notifyDataSetChanged();
                break;
            case R.id.sua:
                AdapterView.AdapterContextMenuInfo menuInfo0 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                ThuChiDTO thuChiDTO0 = list.get(menuInfo0.position);
                Intent intent = new Intent(this, layout_edit_add.class);
                intent.putExtra("sotien", String.valueOf(thuChiDTO0.getSotien()));
                intent.putExtra("imagename", thuChiDTO0.getTenkhoan().toString());
                intent.putExtra("imagesource", thuChiDTO0.getImage_source());
                intent.putExtra("date", thuChiDTO0.getNgaythang());
                intent.putExtra("id", String.valueOf(thuChiDTO0.getId()));
                String  chart0 =String.valueOf(String.valueOf(thuChiDTO0.getSotien()).charAt(0));
                if (chart0.equals("-")) {
                    type = "chi";
                    ghichu = dao.getghichuchi(String.valueOf(thuChiDTO0.getId()));
                } else {
                    type = "thu";
                    ghichu = dao.getghichu(String.valueOf(thuChiDTO0.getId()));
                }
                intent.putExtra("note", ghichu);
                intent.putExtra("type", type);

                startActivity(intent);
                break;
        }
        return true;
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_thu:
                    Intent intent = new Intent(layout_thu_chi.this, Layout_add.class);
                    startActivity(intent);
                    break;
                case R.id.fab_chi:
                    Intent intent2 = new Intent(layout_thu_chi.this, Layout_add_chi.class);
                    startActivity(intent2);
                    break;
                case R.id.fab_no:
                    Intent intent3 = new Intent(layout_thu_chi.this, Layout_add_khoanno.class);
                    startActivity(intent3);
                    break;
                case R.id.fab_chitiet:
                    Intent intent4 = new Intent(layout_thu_chi.this, MainActivity.class);
                    startActivity(intent4);
                    break;

            }
        }
    };
}
