package com.github.tungan5055.yourmoney.Thu_Chi_No;


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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.github.tungan5055.yourmoney.Image.layout_list_item;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

/**
 * Created by an on 5/2/2016.
 */
public class Layout_add_chi extends FragmentActivity implements DatePickerDialog.OnDateSetListener,View.OnClickListener {
    public static final String DATEPICKER_TAG = "datepicker";
    Button btnexit, btnok, btnnhom;
    EditText editDate0, editsotien0, editghichu0;
    TextView texttenkhoan0;
    ThuChiDAO dao;
    private Calendar time;
    LinearLayout linearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_chi);
        btnexit = (Button) findViewById(R.id.btnexit);
        editDate0 = (EditText)findViewById(R.id.editdate);
        texttenkhoan0 = (TextView) findViewById(R.id.edittenkhoan);
        editghichu0 = (EditText)findViewById(R.id.editghichu);
        editsotien0 = (EditText)findViewById(R.id.edittien);

        btnok=(Button)findViewById(R.id.btnok);
        btnnhom = (Button) findViewById(R.id.btnnhom);


        btnexit.setOnClickListener(this);
        btnok.setOnClickListener(this);
        btnnhom.setOnClickListener(this);

        linearLayout = (LinearLayout)findViewById(R.id.click);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_nhomo = new Intent(Layout_add_chi.this, layout_list_item.class);
                startActivityForResult(intent_nhomo, 1);
            }
        });
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
                    Toast.makeText(Layout_add_chi.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editDate0.addTextChangedListener(new TextWatcher() {
            boolean isManualChange = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String working = s.toString();
                boolean isValid = true;
                if (working.length()!=10) {
                    isValid = false;
                }

                if (!isValid) {
                    editDate0.setError("Hãy nhập theo định dạng: YYYY-MM-DD");
                } else {
                    /*editDate0.setError("");*/
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dao = new ThuChiDAO(this);
        dao.open();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnexit :
                Intent intent = new Intent(Layout_add_chi.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnok :
                if(isValidDate(String.valueOf(editDate0.getText())) == 0){
                    Toast.makeText(Layout_add_chi.this, "Bạn đã nhập sai định dạng ngày", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(editDate0.getText().equals("") || editsotien0.getText().equals("") || texttenkhoan0.getText().equals("")){
                    Toast.makeText(Layout_add_chi.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    time = Calendar.getInstance();
                    SimpleDateFormat dft = null;
                    dft = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String strDate = dft.format(time.getTime());
                    if(editDate0.getText().toString().equals("")){
                        editDate0.setText(strDate);
                    }
                    ThuChiDTO dto= new ThuChiDTO();
                    dto.setSotien(Integer.parseInt(editsotien0.getText().toString().replace(",", "")));
                    dto.setTenkhoan(texttenkhoan0.getText().toString());
                    dto.setNgaythang(editDate0.getText().toString());
                    dto.setGhichu(editghichu0.getText().toString());
                    dto.setImage_source(btnnhom.getTag().toString());
                    dao.themthuchi_chi(dto);
                    Intent intent0 = new Intent(Layout_add_chi.this,MainActivity.class);
                    startActivity(intent0);
                }

                break;
            case R.id.btnnhom:
                Intent intent_nhom = new Intent(Layout_add_chi.this, layout_list_item.class);
                startActivityForResult(intent_nhom, 1);
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        month = month + 1;
        String abc = + year + "-" + month + "-" + day;
        Toast.makeText(Layout_add_chi.this, abc, Toast.LENGTH_LONG).show();
        editDate0.setText(abc);
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
    public static int isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String array[] = inDate.split("-");
        if(3000 < Integer.valueOf(array[0]) || Integer.valueOf(array[0]) < 0 || Integer.valueOf(array[1])> 31 || Integer.valueOf(array[1])< 0||Integer.valueOf(array[2])>30|| Integer.valueOf(array[0])<0){
            return 0;
        }
        dateFormat.setLenient(true);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return 0;
        }
        return 1;
    }

}