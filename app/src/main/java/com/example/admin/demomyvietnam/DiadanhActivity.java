package com.example.admin.demomyvietnam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.admin.demomyvietnam.Adapter.DacsanAdapter;
import com.example.admin.demomyvietnam.entity.dacsan;
import com.example.admin.demomyvietnam.entity.diadanh;
import com.example.admin.demomyvietnam.entity.hinhanh;

import java.util.ArrayList;
import java.util.List;

public class DiadanhActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView mtendiadanh,mloai,mgiave,mngaymocua,mgiomocua;
    private DacsanAdapter adapter;
    private TextView mmota;
    private ViewFlipper viewPicture;
    private database db;
    private diadanh diadanhs;
    private List<diadanh> diadanhList=new ArrayList<>();
    private List<hinhanh> hinhanhList=new ArrayList<>();
    private List<dacsan> dacsanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_diadanh);
                anhxa();
                getdata();
                for(int i=0;i<3;i++){
                    PlaySlider(Converttobimap(hinhanhList)[i]);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setHasFixedSize(true);
                adapter=new DacsanAdapter(this,dacsanList);
                recyclerView.setAdapter(adapter);
    }
    public Bitmap [] Converttobimap(List<hinhanh> hinhanhs){
        Bitmap bitmapdata []=new Bitmap[3];
        for(int i=0;i<hinhanhs.size();i++){
                hinhanh hinh=hinhanhs.get(i);
                byte bt []=hinh.getHinhanh();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bt, 0, bt.length);
                bitmapdata[i]=bitmap;
        }
        return  bitmapdata;
    }
    public void PlaySlider(Bitmap  hinhanhs){

                ImageView imageView=new ImageView(this);
                imageView.setImageBitmap(hinhanhs);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                viewPicture.addView(imageView);
                viewPicture.setFlipInterval(3000);
                Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
                Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
                viewPicture.setInAnimation( in );
                viewPicture.setOutAnimation(out);
                viewPicture.setAutoStart(true);

    }
    private void getdata() {
                db=new database(this);
                Intent intent=getIntent();
                String id=intent.getStringExtra("id");
                diadanhList=db.getDiadanhByid(id);
                dacsanList=db.getDacsanbyid(id);
                diadanhs=diadanhList.get(0);
                mmota.setText("mô tả  \n"+diadanhs.getMota());
                mloai.setText(diadanhs.getLoai());
                mgiave.setText("giá vé : "+diadanhs.getGiave());
                mngaymocua.setText("ngày mở cửa : "+diadanhs.getNgaymocua());
                mgiomocua.setText("giờ mở cửa : "+diadanhs.getGiomocua());
                mtendiadanh.setText(diadanhs.getTen());
                //
                hinhanhList=diadanhList.get(0).getHinhanhList();
    }

    private void anhxa() {
                recyclerView =(RecyclerView) findViewById(R.id.id_item_diadanh_Recyclerview);
                mtendiadanh=(TextView) findViewById(R.id.id_item_diadanh_ten);
                mloai=(TextView) findViewById(R.id.id_item_diadanh_loai);
                mgiave=(TextView) findViewById(R.id.id_item_diadanh_giave);
                mngaymocua=(TextView) findViewById(R.id.id_item_diadanh_ngaymocua);
                mgiomocua=(TextView) findViewById(R.id.id_item_diadanh_giomocua);
                viewPicture=(ViewFlipper) findViewById(R.id.id_item_diadanh_hinhanh);
                mmota=(TextView) findViewById(R.id.id_item_diadanh_mota);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccentDark_light, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccentDark_light));
        }
    }
}
