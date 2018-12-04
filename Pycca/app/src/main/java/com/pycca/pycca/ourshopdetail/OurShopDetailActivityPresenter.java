package com.pycca.pycca.ourshopdetail;

import com.pycca.pycca.pojo.OurShopDetail;

public class OurShopDetailActivityPresenter implements OurShopDetailActivityMVP.Presenter {

    private OurShopDetailActivityMVP.View view;
    private OurShopDetailActivityMVP.Model model;

    OurShopDetailActivityPresenter(OurShopDetailActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(OurShopDetailActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void itemClicked(OurShopDetail ourShopDetail) {
        view.clearMarkersGoogleMap();
        view.addMarkerGoogleMap(ourShopDetail);
    }

}
