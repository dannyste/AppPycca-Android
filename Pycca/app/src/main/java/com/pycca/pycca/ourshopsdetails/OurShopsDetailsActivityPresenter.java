package com.pycca.pycca.ourshopsdetails;

import com.pycca.pycca.pojo.OurShopsDetails;

public class OurShopsDetailsActivityPresenter implements OurShopsDetailsActivityMVP.Presenter {

    private OurShopsDetailsActivityMVP.View view;
    private OurShopsDetailsActivityMVP.Model model;

    OurShopsDetailsActivityPresenter(OurShopsDetailsActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(OurShopsDetailsActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void itemClicked(OurShopsDetails ourShopsDetails) {
        view.clearMarkersGoogleMap();
        view.addMarkerGoogleMap(ourShopsDetails);
    }

}
