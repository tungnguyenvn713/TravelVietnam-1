package com.example.admin.demomyvietnam;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView Selectview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Selectview=(BottomNavigationView) findViewById(R.id.id_bottom_view);
        Selectview.setOnNavigationItemSelectedListener(listener);
        getSupportFragmentManager().beginTransaction().replace(R.id.id_fragment_container,new KhamPhaFragment()).commit();


    }
    BottomNavigationView.OnNavigationItemSelectedListener listener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        Fragment selectft=null;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.id_khampha:
                    selectft=new KhamPhaFragment();
                    break;
                case R.id.id_quanly:
                    selectft=new QuanlyFragment();
                    break;
                case R.id.id_camnang:
                    selectft=new CamnangFragment();
                    break;
                case R.id.id_lichsu:
                    selectft=new LichSuFragment();
                    break;


            }
            getSupportFragmentManager().beginTransaction().replace(R.id.id_fragment_container,selectft).commit();
            return true;
        }
    };
}
