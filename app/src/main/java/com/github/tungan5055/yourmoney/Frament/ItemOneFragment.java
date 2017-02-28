package com.github.tungan5055.yourmoney.Frament;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.tungan5055.yourmoney.Adapter.ViewPagerAdapter;
import com.github.tungan5055.yourmoney.R;

/**
 * Created by minhpq on 3/29/16.
 */
public class ItemOneFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
   // OneFragment oneFragment;
    Fragment oneFragment;
    TwoFragment twoFragment;
    ThreeFragment threeFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item_one, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        adapter.addFragment(oneFragment, "Sổ giao dịch");
        adapter.addFragment(twoFragment, "Bank");
        adapter.addFragment(threeFragment,"Báo cáo");
        viewPager.setAdapter(adapter);
    }
}
