package com.github.tungan5055.yourmoney.Thu_Chi_No;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.github.tungan5055.yourmoney.SoNo.Layout_sono;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

/**
 * Created by an on 5/8/2016.
 */
public class Layout_add_khoanno extends FragmentActivity implements DatePickerDialog.OnDateSetListener,View.OnClickListener {
    public static final String DATEPICKER_TAG = "datepicker";
    Button btnexit,btnok;
    EditText editDate0,edittenkhoan0,editsotien0,editghichu0;
    ThuChiDAO dao;
    ThuChiDTO dto;
    Layout_add_khoanno khoanno;
    int Tong_no;
    private Calendar time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_khoanno);
        editDate0 = (EditText)findViewById(R.id.editngaytra);
        edittenkhoan0= (EditText)findViewById(R.id.editnguoivay);
        editghichu0 = (EditText)findViewById(R.id.editghichu);
        editsotien0 = (EditText)findViewById(R.id.edittien);

        btnexit=(Button)findViewById(R.id.btnexit);
        btnok=(Button)findViewById(R.id.btnok);
        btnexit.setOnClickListener(this);
        btnok.setOnClickListener(this);

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
                    Toast.makeText(Layout_add_khoanno.this, e.toString(), Toast.LENGTH_SHORT).show();
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
                    editDate0.setError("Enter a valid date: YYYY-MM-DD");
                } else {
                    editDate0.setError(null);
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        String abc = + year + "-" + month + "-" + day;
        Toast.makeText(Layout_add_khoanno.this, abc, Toast.LENGTH_LONG).show();
        editDate0.setText(abc);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnexit :
                Intent intent = new Intent(Layout_add_khoanno.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnok :
                if(isValidDate(String.valueOf(editDate0.getText())) == 0){
                    Toast.makeText(Layout_add_khoanno.this, "Bạn đã nhập sai định dạng ngày", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(editDate0.getText().equals("") || editsotien0.getText().equals("") || edittenkhoan0.getText().equals("")){
                    Toast.makeText(Layout_add_khoanno.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    time = Calendar.getInstance();
                    SimpleDateFormat dft ;
                    dft = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String strDate = dft.format(time.getTime());
                    if(editDate0.getText().toString().equals("")){
                        editDate0.setText(strDate);
                    }
                    ThuChiDTO dto= new ThuChiDTO();
                    dto.setSotien(Integer.valueOf(editsotien0.getText().toString().replace(",","")));
                    dto.setTenkhoan(edittenkhoan0.getText().toString());
                    dto.setNgaythang(editDate0.getText().toString());
                    dto.setGhichu(editghichu0.getText().toString());
                    dao.themkhoanvay(dto);
                    dao.close();
                    Toast.makeText(this,editsotien0.getText().toString().replace(",",""), Toast.LENGTH_SHORT ).show();
                    Intent intent0 = new Intent(Layout_add_khoanno.this, Layout_sono.class);
                    startActivity(intent0);
                }

                break;
        }
    }

    public static int isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String array[] = inDate.split("-");
        if(3000 < Integer.valueOf(array[0]) || Integer.valueOf(array[0]) < 0 || Integer.valueOf(array[1])> 31 || Integer.valueOf(array[1])< 0||Integer.valueOf(array[2])>30|| Integer.valueOf(array[0])<0){
            return 0;
        }
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return 0;
        }
        return 1;
    }

}