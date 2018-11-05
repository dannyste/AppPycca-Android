package com.pycca.pycca.splash;

import com.pycca.pycca.pojo.User;

public class SplashActivityPresenter implements SplashActivityMVP.Presenter {

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
    public void getCurrentUser(SplashActivity splashActivity) {
        User user = model.getUser(splashActivity);
        if (user != null) {
            view.goToHostActivity();
        }
        else {
            view.goToMultiLoginActivity();
        }
    }

}
