package com.pycca.pycca.virtualcard;

import com.pycca.pycca.pojo.User;

public class VirtualCardActivityPresenter implements VirtualCardActivityMVP.Presenter {

    private VirtualCardActivityMVP.View view;
    private VirtualCardActivityMVP.Model model;

    VirtualCardActivityPresenter(VirtualCardActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(VirtualCardActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void getCurrentUser(VirtualCardActivity virtualCardActivity) {
        User user = model.getUser(virtualCardActivity);
        view.setClubPyccaCardNumber(user.getClubPyccaCardNumber());
        view.setName(user.getNamesClubPyccaPartner() + " " + user.getSurnamesClubPyccaPartner());
        view.setClientSince(user.getClientSince());
    }

}
