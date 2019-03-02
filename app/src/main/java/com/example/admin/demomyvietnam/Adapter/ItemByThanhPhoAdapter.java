package com.example.admin.demomyvietnam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.demomyvietnam.DiadanhActivity;
import com.example.admin.demomyvietnam.R;
import com.example.admin.demomyvietnam.entity.diadanh;
import com.example.admin.demomyvietnam.entity.hinhanh;

import java.util.List;

public class ItemByThanhPhoAdapter extends RecyclerView.Adapter<ItemByThanhPhoAdapter.viewHolder> {
    private List<diadanh> diadanhList;
    private Context context;

    public ItemByThanhPhoAdapter(List<diadanh> diadanhList, Context context) {
        this.diadanhList = diadanhList;
        this.context = context;
    }

    @Override
    public ItemByThanhPhoAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_item_diadanh,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemByThanhPhoAdapter.viewHolder holder, int position) {
        final diadanh ds=diadanhList.get(position);
        hinhanh ha=ds.getHinhanhList().get(2);
        Log.d("hihihi", String.valueOf(ds.getHinhanhList().size()));
        Glide.with(context).load(ha.getHinhanh()).into(holder.hinhanh);
        holder.ten.setText(ds.getTen());
        holder.loai.setText(ds.getLoai());
        holder.gia.setText(ds.getGiave());
        /*
        sự kiện click xem đang click vào địa danh nào đồng thời gửi id cho Activity
         */
        holder.hinhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+ds.getTen(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, DiadanhActivity.class);
                intent.putExtra("id",String.valueOf(ds.getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return diadanhList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private ImageView hinhanh,yeuthich;
        private TextView ten,loai,gia;

        public viewHolder(View itemView) {
            super(itemView);
            hinhanh=(ImageView) itemView.findViewById(R.id.id_item_diadanh_hinh);
            yeuthich=(ImageView) itemView.findViewById(R.id.id_item_diadanh_rate);
            ten=(TextView) itemView.findViewById(R.id.id_item_diadanh_ten);
            loai=(TextView) itemView.findViewById(R.id.id_item_diadanh_loai);
            gia=(TextView)itemView.findViewById(R.id.id_item_diadanh_gia);

        }
    }
}
