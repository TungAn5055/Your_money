package com.github.tungan5055.yourmoney.Edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.github.tungan5055.yourmoney.Image.layout_list_item;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by TungAn on 11/21/2016.
 */

public class layout_edit_add extends FragmentActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    public static final String DATEPICKER_TAG = "datepicker";
    Button btnexit, btnok, btnnhom;
    EditText editDate0, editsotien0, editghichu0;
    TextView texttenkhoan0;
    ThuChiDAO dao;
    LinearLayout linearLayout;
    String type;
    DecimalFormat defomat = new DecimalFormat("#,###,###,###,###,###,###,###");

    private Calendar time;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add);
        btnexit = (Button) findViewById(R.id.btnexit);

        editDate0 = (EditText) findViewById(R.id.editdate);
        texttenkhoan0 = (TextView) findViewById(R.id.edittenkhoan);
        editghichu0 = (EditText) findViewById(R.id.editghichu);
        editsotien0 = (EditText) findViewById(R.id.edittien);

        btnok = (Button) findViewById(R.id.btnok);
        btnnhom = (Button) findViewById(R.id.btnnhom);

        btnexit.setOnClickListener(this);
        btnok.setOnClickListener(this);
        btnnhom.setOnClickListener(this);
        linearLayout = (LinearLayout)findViewById(R.id.click);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_nhomo = new Intent(layout_edit_add.this, layout_list_item.class);
                startActivityForResult(intent_nhomo, 1);
            }
        });


        editsotien0.addTextChangedListener(new TextWatcher() {
            boolean isManualChange = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isManualChange) {
                    isManualChange = false;
                    return;
                }
                try {
                    String value = s.toString().replace(",", "");
                    String reverseValue = new StringBuilder(value).reverse().toString();
                    StringBuilder finalValue = new StringBuilder();

                    for (int i = 1; i <= reverseValue.length(); i++) {

                        char val = reverseValue.charAt(i - 1);
                        finalValue.append(val);
                        if (i % 3 == 0 && i != reverseValue.length() && i > 0) {
                            finalValue.append(",");
                        }
                    }
                    isManualChange = true;
                    editsotien0.setText(finalValue.reverse());
                    editsotien0.setSelection(finalValue.length());
                } catch (Exception e) {
                    Toast.makeText(layout_edit_add.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editsotien0.setText(getIntent().getStringExtra("sotien").toString());
        editDate0.setText(getIntent().getStringExtra("date"));
        editghichu0.setText(getIntent().getStringExtra("note"));
        texttenkhoan0.setText(getIntent().getStringExtra("imagename"));
        int imageResource = getResources().getIdentifier(getIntent().getStringExtra("imagesource"), null, getPackageName());
        btnnhom.setBackgroundResource(imageResource);
        btnnhom.setTag(getIntent().getStringExtra("imagesource"));
        type = getIntent().getStringExtra("type");
        dao = new ThuChiDAO(this);
        dao.open();

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


        findViewById(R.id.btndate).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });


        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }

        }
    }

    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        month = month + 1;
        String abc = +year + "-" + month + "-" + day;
        editDate0.setText(abc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnexit:
                Intent intent = new Intent(layout_edit_add.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnok:
                time = Calendar.getInstance();
                SimpleDateFormat dft = null;
                dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String strDate = dft.format(time.getTime());
                if (editDate0.getText().toString().equals("")) {
                    editDate0.setText(strDate);
                }
                ThuChiDTO dto = new ThuChiDTO();
                dto.setSotien(Integer.parseInt(editsotien0.getText().toString().replaceAll("[,-]", "")));
                dto.setTenkhoan(texttenkhoan0.getText().toString());
                dto.setNgaythang(editDate0.getText().toString());
                dto.setGhichu(editghichu0.getText().toString());
                dto.setImage_source(btnnhom.getTag().toString());
                dao.suathuchi(dto,type, getIntent().getStringExtra("id"));
                dao.close();
                Intent intent0 = new Intent(layout_edit_add.this, MainActivity.class);
                startActivity(intent0);
                break;
            case R.id.btnnhom:
                Intent intent_nhom = new Intent(layout_edit_add.this, layout_list_item.class);
                startActivityForResult(intent_nhom, 1);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle bu = data.getExtras();
                texttenkhoan0.setText(bu.getString("name"));
                int imageResource = getResources().getIdentifier(bu.getString("source"), null, getPackageName());
                btnnhom.setBackgroundResource(imageResource);
                btnnhom.setTag(bu.getString("source"));
            }
        }
    }
}
