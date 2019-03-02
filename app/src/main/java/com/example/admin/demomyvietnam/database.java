package com.example.admin.demomyvietnam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.util.Log;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.example.admin.demomyvietnam.entity.diadanh;
import com.example.admin.demomyvietnam.entity.hinhanh;
import com.example.admin.demomyvietnam.entity.thanhpho;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteOpenHelper {
    private String PATH="";
    private static String DB_NAME="Mvietnam.db";
    private Context mcontext=null;
    private final String TAG="MYVIETNAM22";

    @SuppressLint("SdCardPath")
    public database(Context context) {
        super(context, DB_NAME, null, 1);
        if(Build.VERSION.SDK_INT>17){
            PATH=context.getApplicationInfo().dataDir+"/databases/";
        }else {
            PATH="/data/data/"+context.getPackageName()+"/databases/";
        }
        mcontext=context;
    }
    public boolean checkdb(){
        SQLiteDatabase db=null;
        try{

            String path=PATH+DB_NAME;
            db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);


        }catch (Exception e){
            Log.d("123zx","Lỗi kìa"+e);

        }
        if(db!=null)
            db.close();
        return db!=null?true:false;
    }
    public void CoppyDB(){

        try {
            InputStream myin=mcontext.getAssets().open(DB_NAME);
            String outputfile=PATH+DB_NAME;
            OutputStream myout= new FileOutputStream(outputfile);

            byte [] buffer=new byte[1024];
            int legth;
            while ((legth=myin.read(buffer))>0){
                myout.write(buffer,0,legth);

            }

            myout.flush();
            myout.close();
            myin.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void OpenDB(){
        String path=PATH+DB_NAME;
        SQLiteDatabase .openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void createdb(){
        boolean check=checkdb();
        if(checkdb()){


        }else {
            this.getWritableDatabase();
            CoppyDB();
        }
    }

    public List<hinhanh> getHinhanh(String idbydiadanh) {
        List<hinhanh> ha=new ArrayList<>();
       SQLiteDatabase db=this.getWritableDatabase();
       Cursor c;
        c=db.rawQuery("SELECT * FROM HINHANH WHERE iddiadanh ='"+idbydiadanh+"'",null);
        c.moveToFirst();
        do{
        //add data :v
            int id=c.getInt(c.getColumnIndex("idhinhanh"));
            int iddiadanh=c.getInt(c.getColumnIndex("iddiadanh"));
            byte [] hinh=c.getBlob(c.getColumnIndex("hinhanh"));
            ha.add(new hinhanh(id,iddiadanh,hinh));
        }while (c.moveToNext());
        c.close();
        Log.d(TAG, "getHinhanh: lll "+ha.size());
        return ha;
    }
    public List<diadanh> getDiadanh(String idbythanhpho){
        List<diadanh> diadanhList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c;
        c=db.rawQuery("SELECT * FROM DIADANH WHERE idthanhpho ='"+idbythanhpho+"'",null);
        c.moveToFirst();
        do{
             //add data :v
             int id=c.getInt(c.getColumnIndex("iddiadanh"));
             int idthanhpho=c.getInt(c.getColumnIndex("idthanhpho"));
             String ten=c.getString(c.getColumnIndex("tendiadanh"));
             String mota=c.getString(c.getColumnIndex("motadiadanh"));
             String loai=c.getString(c.getColumnIndex("loaidiadanh"));
             String giave=c.getString(c.getColumnIndex("giave"));
             String ngaymocua=c.getString(c.getColumnIndex("ngaymocua"));
             String giomocua=c.getString(c.getColumnIndex("giomocua"));
            List<hinhanh> hinhanhList =getHinhanh(String.valueOf(id));
             diadanhList.add(new diadanh(id,idthanhpho,ten,mota,loai,giave,ngaymocua,giomocua,hinhanhList));

        }while (c.moveToNext());
        c.close();
        Log.d(TAG, "getDiadanh: "+diadanhList.size());
        return diadanhList;

    }
    public List<diadanh> getDiadanhByid(String ids){
        List<diadanh> diadanhList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c;
        c=db.rawQuery("SELECT * FROM DIADANH WHERE iddiadanh ='"+ids+"'",null);
        c.moveToFirst();
        do{
            //add data :v
            int id=c.getInt(c.getColumnIndex("iddiadanh"));
            int idthanhpho=c.getInt(c.getColumnIndex("idthanhpho"));
            String ten=c.getString(c.getColumnIndex("tendiadanh"));
            String mota=c.getString(c.getColumnIndex("motadiadanh"));
            String loai=c.getString(c.getColumnIndex("loaidiadanh"));
            String giave=c.getString(c.getColumnIndex("giave"));
            String ngaymocua=c.getString(c.getColumnIndex("ngaymocua"));
            String giomocua=c.getString(c.getColumnIndex("giomocua"));
            List<hinhanh> hinhanhList =getHinhanh(String.valueOf(id));
            diadanhList.add(new diadanh(id,idthanhpho,ten,mota,loai,giave,ngaymocua,giomocua,hinhanhList));

        }while (c.moveToNext());
        c.close();
        Log.d(TAG, "getDiadanh: "+diadanhList.size());
        return diadanhList;

    }
    public int getLong( Cursor cursor, int columnIndex )
    {
        int value = 0;

        try
        {
            if ( !cursor.isNull( columnIndex ) )
            {
                Log.d(TAG, "getLong " + columnIndex );
                value = cursor.getInt( columnIndex );
            }
        }
        catch ( Throwable tr )
        {
            Log.d(TAG, "getLong " + columnIndex, tr );
        }

        return value;
    }

    public List<thanhpho> getThanhPho(){

        List<thanhpho> tplist=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c;
        c=db.rawQuery("SELECT * FROM THANHPHO",null);
        c.moveToFirst();
        do{
            int id=c.getInt(c.getColumnIndex("idthanhpho"));
            String ten=c.getString(c.getColumnIndex("tenthanhpho"));
            byte[] blob=c.getBlob(c.getColumnIndex("hinhanh"));
            thanhpho tp=new thanhpho(id,ten,blob);
            //add data :v
            tplist.add(tp);
            blob=null;


        }while (c.moveToNext());
        c.close();

        return tplist;
    }

    private byte[] ConvertBlobToByte(Blob blob){
        int blobLength = 0;
        byte[] blobAsBytes=null;
        try {
            blobLength = (int) blob.length();
            blobAsBytes = blob.getBytes(1, blobLength);
            //release the blob and free up memory.
            blob.free();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return blobAsBytes;
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}