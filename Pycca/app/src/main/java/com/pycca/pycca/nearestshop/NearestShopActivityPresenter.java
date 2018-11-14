package com.pycca.pycca.nearestshop;

public class NearestShopActivityPresenter implements NearestShopActivityMVP.Presenter {

    private NearestShopActivityMVP.View view;
    private NearestShopActivityMVP.Model model;

    NearestShopActivityPresenter(NearestShopActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(NearestShopActivityMVP.View view) {
        this.view = view;
    }

}
