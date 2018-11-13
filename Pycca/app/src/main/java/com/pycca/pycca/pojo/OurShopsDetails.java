package com.pycca.pycca.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class OurShopsDetails implements Parcelable {

    private String name;
    private String address;
    private String openingHours;
    private double latitude;
    private double longitude;

    public OurShopsDetails() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(openingHours);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    public static final Parcelable.Creator<OurShopsDetails> CREATOR = new Parcelable.Creator<OurShopsDetails>() {
        public OurShopsDetails createFromParcel(Parcel in) {
            return new OurShopsDetails(in);
        }
        public OurShopsDetails[] newArray(int size) {
            return new OurShopsDetails[size];
        }
    };

    private OurShopsDetails(Parcel in) {
        name = in.readString();
        address = in.readString();
        openingHours = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

}
