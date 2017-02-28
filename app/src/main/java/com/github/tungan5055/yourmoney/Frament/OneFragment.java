package com.github.tungan5055.yourmoney.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.github.tungan5055.yourmoney.Adapter.CustomArrayListAdapter;
import com.github.tungan5055.yourmoney.Edit.layout_edit_add;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;


public class OneFragment extends Fragment {
    ThuChiDAO dao;
    ListView listView;
    CustomArrayListAdapter customArraylistAdapter;
    List<ThuChiDTO> list;
    String ghichu, type;

    public OneFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        list = new ArrayList<ThuChiDTO>();
        dao = new ThuChiDAO(view.getContext());
        dao.open();
        //load du lieu ra list view
        list = dao.laydanhsachthuchi();
        customArraylistAdapter = new CustomArrayListAdapter(view.getContext(), R.layout.customlistview, list);
        listView.setAdapter(customArraylistAdapter);
        customArraylistAdapter.notifyDataSetChanged();
        registerForContextMenu(listView);
        return view;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflaters;
        inflaters = new MenuInflater(getContext());
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
                Intent intent = new Intent(getContext(), layout_edit_add.class);
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
}
