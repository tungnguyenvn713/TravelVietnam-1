package com.example.admin.demomyvietnam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class ThanhphoAdapter extends RecyclerView.Adapter<ThanhphoAdapter.holder> {
    List<thanhpho> thanhphoList;
    Context context;

    public ThanhphoAdapter(List<thanhpho> thanhphoList, Context context) {
        this.thanhphoList = thanhphoList;
        this.context = context;
    }

    @Override
    public ThanhphoAdapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_item_thanhpho,parent,false);

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
