package com.github.tungan5055.yourmoney.Image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import com.github.tungan5055.yourmoney.Adapter.CustomArrayListImage;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

/**
 * Created by TungAn on 11/7/2016.
 */

public class layout_list_item extends Activity implements View.OnClickListener {
    Button btnback;
    ThuChiDAO dao;
    ListView listView;
    CustomArrayListImage customArrayListImage;
    List<ThuChiDTO> list0;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_item);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.list);
        list0 = new ArrayList<ThuChiDTO>();
        dao = new ThuChiDAO(this);
        dao.open();
        //load du lieu ra list view
        list0 = dao.layimage();
        customArrayListImage = new CustomArrayListImage(layout_list_item.this, R.layout.list_single_image, list0);
        listView.setAdapter(customArrayListImage);
        customArrayListImage.notifyDataSetChanged();
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> MainActivity, View view,
                                    int position, long id) {
                String name = customArrayListImage.getItem(position).getImage_name();
                String source = customArrayListImage.getItem(position).getImage_source();
                getIntent().putExtra("name", name);
                getIntent().putExtra("source", source);
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        list0 = dao.layimage();
        customArrayListImage = new CustomArrayListImage(layout_list_item.this, R.layout.list_single_image, list0);
        listView.setAdapter(customArrayListImage);
        customArrayListImage.notifyDataSetChanged();
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnback:
                this.finish();
                break;
            case R.id.fab:
                Intent intent = new Intent(layout_list_item.this, layout_list_item_new.class);
                startActivity(intent );
                break;
        }
    }
}