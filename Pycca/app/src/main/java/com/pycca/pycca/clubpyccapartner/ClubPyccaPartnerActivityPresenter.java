package com.pycca.pycca.clubpyccapartner;

public class ClubPyccaPartnerActivityPresenter implements ClubPyccaPartnerActivityMVP.Presenter {

    private ClubPyccaPartnerActivityMVP.View view;
    private ClubPyccaPartnerActivityMVP.Model model;

    ClubPyccaPartnerActivityPresenter(ClubPyccaPartnerActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ClubPyccaPartnerActivityMVP.View view) {
        this.view = view;
    }

}
