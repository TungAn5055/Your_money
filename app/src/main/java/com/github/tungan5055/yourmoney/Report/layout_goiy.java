package com.github.tungan5055.yourmoney.Report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.tungan5055.yourmoney.MainActivity;
import com.github.tungan5055.yourmoney.R;


/**
 * Created by TungAn on 11/25/2016.
 */

public class layout_goiy  extends AppCompatActivity {


    private FloatingActionButton fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_baocao);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_home);
        fab2.setOnClickListener(clickListener);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_home:
                    Intent intent1 = new Intent(layout_goiy.this, MainActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    };
}
