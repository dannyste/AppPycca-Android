package com.pycca.pycca.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class OurShops implements Parcelable {

    private String name;
    private ArrayList<OurShopsDetails> ourShopsDetailsArrayList;

    public OurShops() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<OurShopsDetails> getOurShopsDetailsArrayList() {
        return ourShopsDetailsArrayList;
    }

    public void setOurShopsDetailsArrayList(ArrayList<OurShopsDetails> ourShopsDetailsArrayList) {
        this.ourShopsDetailsArrayList = ourShopsDetailsArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedList(ourShopsDetailsArrayList);
    }

    public static final Parcelable.Creator<OurShops> CREATOR = new Parcelable.Creator<OurShops>() {
        public OurShops createFromParcel(Parcel in) {
            return new OurShops(in);
        }
        public OurShops[] newArray(int size) {
            return new OurShops[size];
        }
    };

    private OurShops(Parcel in) {
        name = in.readString();
        in.readTypedList(ourShopsDetailsArrayList, OurShopsDetails.CREATOR);
    }

}
