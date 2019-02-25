package com.example.admin.demomyvietnam;

import android.os.Parcel;
import android.os.Parcelable;

public class thanhpho implements Parcelable{
    private int id;
    private String mTenthanhpho;
    private byte [] hinhanh;

    public thanhpho(int id, String mTenthanhpho, byte[] hinhanh) {
        this.id = id;
        this.mTenthanhpho = mTenthanhpho;
        this.hinhanh = hinhanh;
    }

    protected thanhpho(Parcel in) {
        id = in.readInt();
        mTenthanhpho = in.readString();
        hinhanh = in.createByteArray();
    }

    public static final Creator<thanhpho> CREATOR = new Creator<thanhpho>() {
        @Override
        public thanhpho createFromParcel(Parcel in) {
            return new thanhpho(in);
        }

        @Override
        public thanhpho[] newArray(int size) {
            return new thanhpho[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmTenthanhpho() {
        return mTenthanhpho;
    }

    public void setmTenthanhpho(String mTenthanhpho) {
        this.mTenthanhpho = mTenthanhpho;
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
        parcel.writeString(mTenthanhpho);
        parcel.writeByteArray(hinhanh);
    }
}
