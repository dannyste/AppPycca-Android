package com.pycca.pycca.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class OurShop implements Parcelable {

    private String name;
    private ArrayList<OurShopDetail> ourShopDetailArrayList;

    public OurShop() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<OurShopDetail> getOurShopDetailArrayList() {
        return ourShopDetailArrayList;
    }

    public void setOurShopDetailArrayList(ArrayList<OurShopDetail> ourShopDetailArrayList) {
        this.ourShopDetailArrayList = ourShopDetailArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedList(ourShopDetailArrayList);
    }

    public static final Parcelable.Creator<OurShop> CREATOR = new Parcelable.Creator<OurShop>() {
        public OurShop createFromParcel(Parcel in) {
            return new OurShop(in);
        }
        public OurShop[] newArray(int size) {
            return new OurShop[size];
        }
    };

    private OurShop(Parcel in) {
        name = in.readString();
        in.readTypedList(ourShopDetailArrayList, OurShopDetail.CREATOR);
    }

}
