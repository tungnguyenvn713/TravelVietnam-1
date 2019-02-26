package com.example.admin.demomyvietnam.ActionFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.demomyvietnam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuanlyFragment extends Fragment {


    public QuanlyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Integer i=new Integer("1");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quanly, container, false);
    }

}
