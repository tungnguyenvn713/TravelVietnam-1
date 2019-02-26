package com.example.admin.demomyvietnam.ActionFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.demomyvietnam.Adapter.ItemByThanhPhoAdapter;
import com.example.admin.demomyvietnam.R;
import com.example.admin.demomyvietnam.database;
import com.example.admin.demomyvietnam.entity.diadanh;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemByThanhPhoFragment extends Fragment {
    private database db;
    private List<diadanh> diadanhList=new ArrayList<>();
    private ItemByThanhPhoAdapter adapter;
    private RecyclerView recyclerView;

    public ItemByThanhPhoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_item_by_thanh_pho, container, false);
        // Inflate the layout for this fragment
        recyclerView=(RecyclerView) view.findViewById(R.id.id_) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db=new database(getContext());
        Bundle bundle = this.getArguments();
        int key = 0;
        if (bundle != null) {
             key = bundle.getInt("idbythanhpho", 0);
        }
        diadanhList=db.getDiadanh(String.valueOf(key));
        adapter=new ItemByThanhPhoAdapter(diadanhList,getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

}
