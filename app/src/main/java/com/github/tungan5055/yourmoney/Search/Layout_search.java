package com.github.tungan5055.yourmoney.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.github.tungan5055.yourmoney.Adapter.Custom_List_Search;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

/**
 * Created by TungAn on 11/15/2016.
 */

public class Layout_search extends FragmentActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener{
    ThuChiDAO dao;
    ListView listView;
    Custom_List_Search custom_List_Search;
    List<ThuChiDTO> list;
    CheckBox chkhoanthu, chkhoanchi, chkhoanno, chbank;
    EditText edittext;
    RadioButton raten, rangay;
    Button search,btndate;
    private FloatingActionButton fab2;
    public static final String DATEPICKER_TAG = "datepicker";
    int isthu = 0;
    int ischi = 0;
    int isno = 0;
    int isbank = 0;
    int isten = 0;
    int isngay = 0;
    String text;
    final Calendar calendar = Calendar.getInstance();

    final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance( this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);
        chkhoanthu = (CheckBox) findViewById(R.id.chkhoanthu);
        chkhoanchi = (CheckBox) findViewById(R.id.chkhoanchi);
        chkhoanno = (CheckBox) findViewById(R.id.chkhoanno);
        chbank = (CheckBox) findViewById(R.id.chbank);
        edittext = (EditText) findViewById(R.id.editText2);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_home);
        fab2.setOnClickListener(clickListener);
        search = (Button) findViewById(R.id.btnsearch);
        search.setOnClickListener(clickListener);
        btndate = (Button)findViewById(R.id.btndate);
        btndate.setOnClickListener(clickListener);

        btndate.setVisibility(View.GONE);
        edittext.setVisibility(View.VISIBLE);

        raten = (RadioButton) findViewById(R.id.raten);
        rangay = (RadioButton) findViewById(R.id.rangaythang);
        rangay.setOnClickListener(clickListener);
        raten.setOnClickListener(clickListener);



        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<ThuChiDTO>();
        dao = new ThuChiDAO(this);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_home:
                    Intent intent1 = new Intent(Layout_search.this, MainActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btnsearch:
                    text = edittext.getText().toString();
                    if(!chkhoanthu.isChecked() && !chkhoanchi.isChecked() && chkhoanno.isChecked() && !chbank.isChecked() ){
                        Toast.makeText(Layout_search.this, "Bạn phải chọn ít nhất một loại giao dịch để tìm kiếm.", Toast.LENGTH_SHORT).show();
                    }
                    if (!raten.isChecked() && !rangay.isChecked()){
                        Toast.makeText(Layout_search.this, "Vui lòng chọn kiểu bạn muốn tiềm kiếm theo.", Toast.LENGTH_SHORT).show();
                    }
                    if(text.equals(null)){
                        Toast.makeText(Layout_search.this, "Vui lòng nhập để tìm kiếm.", Toast.LENGTH_SHORT).show();
                    }
                    if (chkhoanthu.isChecked()) {
                        isthu = 1;
                    }
                    if (chkhoanchi.isChecked()) {
                        ischi = 1;
                    }
                    if (chkhoanno.isChecked()) {
                        isno = 1;
                    }
                    if (chbank.isChecked()) {
                        isbank = 1;
                    }
                    if (raten.isChecked()) {
                        isten = 1;
                    }
                    if (rangay.isChecked()) {
                        isngay = 1;
                    }
                    dao.open();
                    list = dao.laydanhsachsearch(isthu, ischi, isno, isbank, isten, isngay, text);
                    custom_List_Search = new Custom_List_Search(Layout_search.this, R.layout.list_search, list);
                    if (custom_List_Search.equals(null)) {
                        listView.setAdapter(null);
                    } else {
                        listView.setAdapter(custom_List_Search);
                    }
                    custom_List_Search.notifyDataSetChanged();
                    break;
                case R.id.raten:
                    btndate.setVisibility(View.GONE);
                    edittext.setVisibility(View.VISIBLE);
                    edittext.setHint("Nhập tên để tìm kiếm...");
                    break;
                case R.id.rangaythang:
                    edittext.setVisibility(View.GONE);
                    btndate.setVisibility(View.VISIBLE);
                    btndate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            datePickerDialog.setYearRange(1985, 2028);
                            datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
                        }
                    });
                    break;
            }
        }
    };




    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        month = month + 1;
        String date = +year + "-" + month + "-" + day;
        btndate.setVisibility(View.GONE);
        edittext.setVisibility(View.VISIBLE);
        edittext.setText(date);
    }
}
