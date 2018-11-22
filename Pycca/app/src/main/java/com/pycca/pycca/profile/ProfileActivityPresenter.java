package com.pycca.pycca.profile;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.Constants;

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
        model.userSubscribeToTopic(Constants.TOPIC_INVITED);
        model.userUnsubscribeFromTopic(Constants.TOPIC_SOCIAL_NETWORK);
        model.userUnsubscribeFromTopic(Constants.TOPIC_NATIVE);
        model.userUnsubscribeFromTopic(Constants.TOPIC_CLUB_PYCCA_PARTNER);
        model.userUnsubscribeFromTopic(Constants.TOPIC_NOT_CLUB_PYCCA_PARTNER);
        model.userUnsubscribeFromTopic(Constants.TOPIC_SOCIAL_NETWORK_CLUB_PYCCA_PARTNER);
        model.userUnsubscribeFromTopic(Constants.TOPIC_SOCIAL_NETWORK_NOT_CLUB_PYCCA_PARTNER);
        model.userUnsubscribeFromTopic(Constants.TOPIC_NATIVE_CLUB_PYCCA_PARTNER);
        model.userUnsubscribeFromTopic(Constants.TOPIC_NATIVE_NOT_CLUB_PYCCA_PARTNER);
        view.goToMultiLoginActivity();
    }

}
