package com.pycca.pycca.pojo;

import java.util.ArrayList;

public class OurShops {

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
}
