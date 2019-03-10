package com.example.admin.demomyvietnam.ActionFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.demomyvietnam.Adapter.CamnangAdapter;
import com.example.admin.demomyvietnam.R;
import com.example.admin.demomyvietnam.database;
import com.example.admin.demomyvietnam.entity.camnang;

import java.util.ArrayList;
import java.util.List;

public class CamnangFragment extends Fragment {

    private List<camnang> camnangList=new ArrayList<>();
    private CamnangAdapter adapter;
    private RecyclerView recyclerView;
    private database db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_camnang, container, false);
        recyclerView=view.findViewById(R.id.id_camnang_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db=new database(getContext());
        camnangList=db.getcamnang();
        adapter=new CamnangAdapter(getContext(),camnangList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
