package com.pycca.pycca.profile;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;

public class ProfileActivityPresenter implements ProfileActivityMVP.Presenter {

    private ProfileActivityMVP.View view;
    private ProfileActivityMVP.Model model;

    ProfileActivityPresenter(ProfileActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ProfileActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void getCurrentUser(ProfileActivity profileActivity) {
        User user = model.getUser(profileActivity);
        view.setPhotoUrl(user.getPhotoUrl());
        view.setName(user.getName());
        view.setEmail(user.getEmail());
        view.setIdentificationCard(!user.getIdentificationCard().trim().isEmpty() ? user.getIdentificationCard() : profileActivity.getString(R.string.not_registered));
        view.setClubPyccaCardNumber(!user.getClubPyccaCardNumber().trim().isEmpty() ? user.getClubPyccaCardNumber() : profileActivity.getString(R.string.not_registered));
    }

    @Override
    public void logoutClicked(ProfileActivity profileActivity) {
        model.userLogout(profileActivity);
        view.goToMultiLoginActivity();
    }

}
