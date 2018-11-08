package com.pycca.pycca.ourshopsdetails;

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

}
