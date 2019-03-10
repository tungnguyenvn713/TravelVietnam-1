package com.example.admin.demomyvietnam.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.demomyvietnam.ActionFragment.ItemByThanhPhoFragment;
import com.example.admin.demomyvietnam.R;
import com.example.admin.demomyvietnam.database;
import com.example.admin.demomyvietnam.entity.thanhpho;

import java.util.List;

public class ThanhphoAdapter extends RecyclerView.Adapter<ThanhphoAdapter.holder> {
    List<thanhpho> thanhphoList;
    Context context;
    database db;

    public ThanhphoAdapter(List<thanhpho> thanhphoList, Context context) {
        this.thanhphoList = thanhphoList;
        this.context = context;
    }

    @Override
    public ThanhphoAdapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_item_thanhpho,parent,false);
        db=new database(context);
        return new holder(view);
    }
    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(ThanhphoAdapter.holder holder, int position) {
        final thanhpho tp=thanhphoList.get(position);
        holder.mthanhpho_ten.setText(tp.getmTenthanhpho());
        Glide.with(context).load(tp.getHinhanh()).into(holder.mthanhpho_hinh);
        /*
         * Sự kiện chọn Thành Phố chuyển Sang 1 RecyClerview khác
         * Recyclerview này nhận id thành phố
         * lấy toàn bộ địa danh và hiển thị cho người dùng tiếp tục chọn địa điểm du lịch
         *
         */
        holder.mthanhpho_hinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Đã lấy ID ="+tp.getId(), Toast.LENGTH_SHORT).show();
                //gửi list này bằng bundle qua fragment ItemByThanhphoFragment
                if(db.getDiadanh(String.valueOf(tp.getId())).size()==0){

                    Toast.makeText(context, "Chưa có thông tin", Toast.LENGTH_SHORT).show();

                }else {
                android.support.v4.app.Fragment selectft=new ItemByThanhPhoFragment();
                FragmentTransaction ft=((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putInt("idbythanhpho", tp.getId());
                selectft.setArguments(args);
                ft.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
                ft.replace(R.id.id_fragment_container,selectft).commit();
                }
            }

        });

    }


    @Override
    public int getItemCount() {
        return thanhphoList.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        private TextView mthanhpho_ten;
        private ImageView mthanhpho_hinh;


        public holder(View itemView) {
            super(itemView);
            mthanhpho_ten=(TextView) itemView.findViewById(R.id.id_item_thanhpho_ten);
            mthanhpho_hinh=(ImageView) itemView.findViewById(R.id.id_item_thanhpho_hinhanh);
        }
    }
}
