package com.github.tungan5055.yourmoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.text.DecimalFormat;

import com.github.tungan5055.yourmoney.Bank.layout_select_bank;
import com.github.tungan5055.yourmoney.Frament.ItemOneFragment;
import com.github.tungan5055.yourmoney.Frament.TwoFragment;
import com.github.tungan5055.yourmoney.Image.layout_list_item_new;
import com.github.tungan5055.yourmoney.Report.Layout_Baocao;
import com.github.tungan5055.yourmoney.SQLLite.ThuChiDAO;
import com.github.tungan5055.yourmoney.Search.Layout_search;
import com.github.tungan5055.yourmoney.SoNo.Layout_sono;
import com.github.tungan5055.yourmoney.Suggest.layout_goiy;
import com.github.tungan5055.yourmoney.Thu_Chi_No.Layout_add;
import com.github.tungan5055.yourmoney.Thu_Chi_No.Layout_add_chi;
import com.github.tungan5055.yourmoney.Thu_Chi_No.Layout_add_khoanno;
import com.github.tungan5055.yourmoney.Thu_Chi_No.layout_thu_chi;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ThuChiDAO dao;
    private FrameLayout mContentFrame;


    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    DecimalFormat defomat = new DecimalFormat("#,###,###,###,###,###,###,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.sono:
                        Snackbar.make(mContentFrame, "Sổ nợ!!", Snackbar.LENGTH_SHORT).show();
                        Intent inSono = new Intent(MainActivity.this, Layout_sono.class);
                        startActivity(inSono);
                        return true;
                    case R.id.baocao:
                        Snackbar.make(mContentFrame, "Báo cáo!", Snackbar.LENGTH_SHORT).show();
                        Intent inBaocao = new Intent(MainActivity.this, Layout_Baocao.class);
                        startActivity(inBaocao);
                        return true;
                    case R.id.tknh:
                        startFragment(new TwoFragment());
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.home:
                        Intent inBaocao9 = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(inBaocao9);
                        return true;
                    case R.id.goiy:
                        Intent inBaocao6 = new Intent(MainActivity.this, layout_goiy.class);
                        startActivity(inBaocao6);
                        return true;
                    case R.id.thuchi:
                        Intent inBaocao8 = new Intent(MainActivity.this, layout_thu_chi.class);
                        startActivity(inBaocao8);
                        return true;
                    case R.id.search:
                        Intent inBaocao7 = new Intent(MainActivity.this, Layout_search.class);
                        startActivity(inBaocao7);
                        return true;

                    default:
                        return true;
                }
            }
        });


        startFragment(new ItemOneFragment());
        mContentFrame = (FrameLayout) findViewById(R.id.nav_contentframe);

        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        frameLayout.getBackground().setAlpha(0);

        final FloatingActionsMenu fabMenu = (FloatingActionsMenu) findViewById(R.id.fab_menu);
        fabMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                frameLayout.getBackground().setAlpha(240);
                frameLayout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        fabMenu.collapse();
                        return true;
                    }
                });
            }

            @Override
            public void onMenuCollapsed() {
                frameLayout.getBackground().setAlpha(0);
                frameLayout.setOnTouchListener(null);
            }
        });

        fab1 = (FloatingActionButton) findViewById(R.id.fab_thu);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_chi);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_no);

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);


    }

    @Override
    protected void onResume() {
        super.onResume();
        dao = new ThuChiDAO(this);
        dao.open();
        int thu = 0;
        int chi = 0;
        try {
            thu = dao.getTong_thu("All");
            chi = dao.getTong_chi("All");
            int tinh = thu - chi;
            setTitle(defomat.format(tinh).toString() + " .đ");


        } catch (NullPointerException e) {
            Toast.makeText(MainActivity.this, "Errors", Toast.LENGTH_LONG).show();
            int tinh = thu - chi;
            setTitle(defomat.format(tinh).toString() + " .đ");
        }

    }

    public void startFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.nav_contentframe, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menuo, menu);
        MenuItem search = (MenuItem) menu.findItem(R.id.action_search);
        search.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this, Layout_search.class);
                startActivity(intent);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.abc0:
                Toast.makeText(MainActivity.this, "Ban vua click vao:" + item.getItemId() + " + " + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.abc2:
                Toast.makeText(MainActivity.this, "Ban vua click vao:" + item.getItemId() + " + " + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.abc1:
                Toast.makeText(MainActivity.this, "Ban vua click vao:" + item.getItemId() + " + " + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_thu:
                    Intent intent = new Intent(MainActivity.this, Layout_add.class);
                    startActivity(intent);
                    break;
                case R.id.fab_chi:
                    Intent intent2 = new Intent(MainActivity.this, Layout_add_chi.class);
                    startActivity(intent2);
                    break;
                case R.id.fab_no:
                    Intent intent3 = new Intent(MainActivity.this, Layout_add_khoanno.class);
                    startActivity(intent3);
                    break;

            }
        }
    };


}