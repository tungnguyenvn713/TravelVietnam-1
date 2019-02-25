package com.example.admin.demomyvietnam;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import java.util.ArrayList;
import java.util.List;


public class KhamPhaFragment extends Fragment {
    List<thanhpho> thanhphoList=new ArrayList<>();
    private RecyclerView recyclerView;
    private ThanhphoAdapter adapter;
    private database db;
    private IndefinitePagerIndicator pager_indicator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_kham_pha, container, false);
        // Inflate the layout for this fragment
        recyclerView=(RecyclerView) view.findViewById(R.id.id_re_thanhpho);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //db
        db=new database(getContext());
        db.createdb();
        thanhphoList=db.getThanhPho();
        adapter=new ThanhphoAdapter(thanhphoList,getContext());
        recyclerView.setAdapter(adapter);
        pager_indicator=(IndefinitePagerIndicator) view.findViewById(R.id.recyclerview_pager_indicator);
        pager_indicator.attachToRecyclerView(recyclerView);
        return view;
    }

}
