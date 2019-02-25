package com.example.admin.demomyvietnam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

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
    public List<diadanh> getDiadanh(){
        List<diadanh> diadiems=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c;
        c=db.rawQuery("SELECT * FROM DIADANH",null);
        c.moveToFirst();
        do{

            //add data :v



        }while (c.moveToNext());
        c.close();

        return diadiems;

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