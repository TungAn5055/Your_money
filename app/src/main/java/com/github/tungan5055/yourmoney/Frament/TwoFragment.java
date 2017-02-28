package com.github.tungan5055.yourmoney.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.tungan5055.yourmoney.Adapter.Custom_List;
import com.github.tungan5055.yourmoney.Bank.layout_select_tknh_vietcombank;
import com.github.tungan5055.yourmoney.Bank.layout_select_tknh_viettinbank;
import com.github.tungan5055.yourmoney.R;


public class TwoFragment extends Fragment {
    public TwoFragment() {
        // Required empty public constructor
    }
    ListView list;
    String[] web = {
            "VietinBank",
            "VietcomBank",
            "TechcomBank",
            "AgriBank",
            "TpBank",
            "BIDV"
    } ;
    Integer[] imageId = {
            R.drawable.ic_bank_vietinbank,
            R.drawable.ic_bank_vietcombank,
            R.drawable.ic_bank_techcombank,
            R.drawable.ic_bank_agribank,
            R.drawable.ic_bank_tpbank,
            R.drawable.ic_bank_bidv
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_two, container, false);
        Custom_List adapter = new Custom_List(getActivity(), web, imageId);
        list=(ListView)view.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch( position ) {
                    case 0:
                        Intent intent = new Intent(view.getContext(), layout_select_tknh_viettinbank.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent0 = new Intent(view.getContext(), layout_select_tknh_vietcombank.class);
                        startActivity(intent0);
                        break;
                    case 2:
                        Toast.makeText(view.getContext(), "Xin lỗi bạn phần mềm chưa hỗ trợ ngân hàng này!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(view.getContext(), "Xin lỗi bạn phần mềm chưa hỗ trợ ngân hàng này!", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(view.getContext(), "Xin lỗi bạn phần mềm chưa hỗ trợ ngân hàng này!", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(view.getContext(), "Xin lỗi bạn phần mềm chưa hỗ trợ ngân hàng này!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return view;
    }

}
