package com.example.admin.demomyvietnam.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class hinhanh implements Parcelable {
    private int id;
    private int idbydiadanh;
    private byte [] hinhanh;

    public hinhanh(int id, int idbydiadanh, byte[] hinhanh) {
        this.id = id;
        this.idbydiadanh = idbydiadanh;
        this.hinhanh = hinhanh;
    }

    protected hinhanh(Parcel in) {
        id = in.readInt();
        idbydiadanh = in.readInt();
        hinhanh = in.createByteArray();
    }

    public static final Creator<hinhanh> CREATOR = new Creator<hinhanh>() {
        @Override
        public hinhanh createFromParcel(Parcel in) {
            return new hinhanh(in);
        }

        @Override
        public hinhanh[] newArray(int size) {
            return new hinhanh[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdbydiadanh() {
        return idbydiadanh;
    }

    public void setIdbydiadanh(int idbydiadanh) {
        this.idbydiadanh = idbydiadanh;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(idbydiadanh);
        parcel.writeByteArray(hinhanh);
    }
}
