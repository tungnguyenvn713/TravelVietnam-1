package com.example.admin.demomyvietnam.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class diadanh implements Parcelable{
    private int id;
    private int idbythanhpho;
    private String ten;
    private String mota;
    private String loai;
    private String giave;
    private String ngaymocua;
    private String giomocua;
    private List<hinhanh> hinhanhList;

    public diadanh(int id, int idbythanhpho, String ten, String mota, String loai, String giave, String ngaymocua, String giomocua, List<hinhanh> hinhanhList) {
        this.id = id;
        this.idbythanhpho = idbythanhpho;
        this.ten = ten;
        this.mota = mota;
        this.loai = loai;
        this.giave = giave;
        this.ngaymocua = ngaymocua;
        this.giomocua = giomocua;
        this.hinhanhList = hinhanhList;
    }

    protected diadanh(Parcel in) {
        id = in.readInt();
        idbythanhpho = in.readInt();
        ten = in.readString();
        mota = in.readString();
        loai = in.readString();
        giave = in.readString();
        ngaymocua = in.readString();
        giomocua = in.readString();
        hinhanhList = in.createTypedArrayList(hinhanh.CREATOR);
    }

    public static final Creator<diadanh> CREATOR = new Creator<diadanh>() {
        @Override
        public diadanh createFromParcel(Parcel in) {
            return new diadanh(in);
        }

        @Override
        public diadanh[] newArray(int size) {
            return new diadanh[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdbythanhpho() {
        return idbythanhpho;
    }

    public void setIdbythanhpho(int idbythanhpho) {
        this.idbythanhpho = idbythanhpho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getGiave() {
        return giave;
    }

    public void setGiave(String giave) {
        this.giave = giave;
    }

    public String getNgaymocua() {
        return ngaymocua;
    }

    public void setNgaymocua(String ngaymocua) {
        this.ngaymocua = ngaymocua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public List<hinhanh> getHinhanhList() {
        return hinhanhList;
    }

    public void setHinhanhList(List<hinhanh> hinhanhList) {
        this.hinhanhList = hinhanhList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(idbythanhpho);
        parcel.writeString(ten);
        parcel.writeString(mota);
        parcel.writeString(loai);
        parcel.writeString(giave);
        parcel.writeString(ngaymocua);
        parcel.writeString(giomocua);
        parcel.writeTypedList(hinhanhList);
    }
}
