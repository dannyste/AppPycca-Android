package com.pycca.pycca.ourshops;

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

}
