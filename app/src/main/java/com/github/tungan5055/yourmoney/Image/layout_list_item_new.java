package com.github.tungan5055.yourmoney.Image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.tungan5055.yourmoney.R;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDTO;

/**
 * Created by TungAn on 11/9/2016.
 */

public class layout_list_item_new extends Activity implements View.OnClickListener {
    Button btnback, btndone;
    ImageButton imgbtn;
    TextView edittext0;
    ThuChiDAO dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_item_new);

        btnback = (Button)findViewById(R.id.btnback) ;
        btndone = (Button)findViewById(R.id.btndone) ;
        imgbtn = (ImageButton)findViewById(R.id.imgbtn);
        edittext0 = (EditText)findViewById(R.id.editText0);

        btnback.setOnClickListener( this);
        btndone.setOnClickListener( this);
        imgbtn.setOnClickListener( this);

        imgbtn.setTag("@drawable/ic_add_image");
        dao = new ThuChiDAO(this);
        dao.open();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnback:
                this.finish();
                break;
            case R.id.btndone:
                ThuChiDTO dto = new ThuChiDTO();
                dto.setImage_name(edittext0.getText().toString());
                dto.setImage_source(imgbtn.getTag().toString());
                dao.themimage(dto);
                dao.close();
                this.finish();
                break;
            case  R.id.imgbtn:
                Intent intent = new Intent(layout_list_item_new.this, layoutGridview.class);
                startActivityForResult(intent, 1);
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle bu = data.getExtras();
                int imageResource = getResources().getIdentifier(bu.getString("source"), null, getPackageName());
                imgbtn.setBackgroundResource(imageResource);
                imgbtn.setTag(bu.getString("source"));
            }
        }
    }
}
