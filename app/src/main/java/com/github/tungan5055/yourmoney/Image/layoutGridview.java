package com.github.tungan5055.yourmoney.Image;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.github.tungan5055.yourmoney.R;

/**
 * Created by TungAn on 11/9/2016.
 */

public class layoutGridview extends Activity implements View.OnClickListener {
    Button btnback, btndone;
    public String[] mThumbIds = {
            "@drawable/ic_1",
            "@drawable.ic_2",
            "@drawable.ic_3",
            "@drawable.ic_3",
            "@drawable/ic_4",
            "@drawable/ic_5",
            "@drawable/ic_6",
            "@drawable/ic_8",
            "@drawable/ic_7",
            "@drawable/ic_9",
            "@drawable/ic_11",
            "@drawable/ic_10",
            "@drawable/ic_13",
            "@drawable/ic_12",
            "@drawable/ic_15",
            "@drawable/ic_14",
            "@drawable/ic_17",
            "@drawable/ic_16",
            "@drawable/ic_19",
            "@drawable/ic_18",
            "@drawable/ic_20"
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gridview);
        btnback = (Button)findViewById(R.id.btnback) ;
        btnback.setOnClickListener(this);
        GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(new GridviewImage(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> MainActivity, View view,
                                    int position, long id) {

                String source = mThumbIds[position];
                getIntent().putExtra("source", source);
                setResult(RESULT_OK, getIntent());
                finish();

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnback:
                this.finish();
                break;
        }
    }

}