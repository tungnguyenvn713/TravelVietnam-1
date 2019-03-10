package com.example.admin.demomyvietnam.ActionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.demomyvietnam.R;
import com.example.admin.demomyvietnam.Adapter.ThanhphoAdapter;
import com.example.admin.demomyvietnam.database;
import com.example.admin.demomyvietnam.entity.thanhpho;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;


public class KhamPhaFragment extends Fragment implements TextWatcher {
    List<thanhpho> thanhphoList=new ArrayList<>();
    private RecyclerView recyclerView;
    private ThanhphoAdapter adapter;
    private database db;
    private MaterialSearchBar sbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_kham_pha, container, false);
        // Inflate the layout for this fragment
        recyclerView= view.findViewById(R.id.id_re_thanhpho);
        sbar=view.findViewById(R.id.toolbar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //db
        db=new database(getContext());
        db.createdb();
        thanhphoList=db.getThanhPho();
        adapter=new ThanhphoAdapter(thanhphoList,getContext());
        recyclerView.setAdapter(adapter);
        sbar.addTextChangeListener(this);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.action_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String newtxt=s.toString();


    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
