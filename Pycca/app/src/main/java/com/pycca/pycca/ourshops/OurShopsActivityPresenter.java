package com.pycca.pycca.ourshops;

import com.pycca.pycca.pojo.OurShopsDetails;

import java.util.ArrayList;

public class OurShopsActivityPresenter implements OurShopsActivityMVP.Presenter {

    private OurShopsActivityMVP.View view;
    private OurShopsActivityMVP.Model model;

    OurShopsActivityPresenter(OurShopsActivityMVP.Model model) {
        this.model  = model;
    }

    @Override
    public void setView(OurShopsActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadOurShopsArrayList() {

    }

    @Override
    public void itemClicked(ArrayList<OurShopsDetails> ourShopsDetails) {
        view.goToOurShopsDetailsActivity(ourShopsDetails);
    }

}
