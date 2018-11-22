package com.pycca.pycca.splash;

import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.Util;

public class SplashActivityPresenter implements SplashActivityMVP.Presenter, SplashActivityMVP.TaskListener {

    private SplashActivityMVP.View view;
    private SplashActivityMVP.Model model;

    SplashActivityPresenter(SplashActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(SplashActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void startPyccaAnimation() {
        view.showPyccaAnimation();
    }

    @Override
    public void finishedPyccaAnimation() {
        view.checkPermission();
    }

    @Override
    public void configureParameter(SplashActivity splashActivity) {
        model.setParameter(splashActivity, SplashActivityPresenter.this);
    }

    @Override
    public void onSuccess(User user) {
        if (user != null) {
            if (user.getRegistrationProvider().equalsIgnoreCase(Util.RegistrationProvider.FACEBOOK.toString()) || user.getRegistrationProvider().equalsIgnoreCase(Util.RegistrationProvider.INSTAGRAM.toString()) || user.getRegistrationProvider().equalsIgnoreCase(Util.RegistrationProvider.GOOGLE.toString())) {
                model.userSubscribeToTopic(Constants.TOPIC_SOCIAL_NETWORK);
                if (user.isClubPyccaPartner()) {
                    model.userSubscribeToTopic(Constants.TOPIC_CLUB_PYCCA_PARTNER);
                    model.userSubscribeToTopic(Constants.TOPIC_SOCIAL_NETWORK_CLUB_PYCCA_PARTNER);
                }
                else {
                    model.userSubscribeToTopic(Constants.TOPIC_NOT_CLUB_PYCCA_PARTNER);
                    model.userSubscribeToTopic(Constants.TOPIC_SOCIAL_NETWORK_NOT_CLUB_PYCCA_PARTNER);
                }
            }
            else {
                model.userSubscribeToTopic(Constants.TOPIC_NATIVE);
                if (user.isClubPyccaPartner()) {
                    model.userSubscribeToTopic(Constants.TOPIC_CLUB_PYCCA_PARTNER);
                    model.userSubscribeToTopic(Constants.TOPIC_NATIVE_CLUB_PYCCA_PARTNER);
                }
                else {
                    model.userSubscribeToTopic(Constants.TOPIC_NOT_CLUB_PYCCA_PARTNER);
                    model.userSubscribeToTopic(Constants.TOPIC_NATIVE_NOT_CLUB_PYCCA_PARTNER);
                }
            }
            view.goToHostActivity();
        }
        else {
            model.userSubscribeToTopic(Constants.TOPIC_INVITED);
            view.goToMultiLoginActivity();
        }
    }

    @Override
    public void onError(int error) {

    }

}
