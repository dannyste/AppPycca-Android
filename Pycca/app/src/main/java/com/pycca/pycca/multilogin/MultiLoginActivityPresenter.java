package com.pycca.pycca.multilogin;

public class MultiLoginActivityPresenter implements MultiLoginActivityMVP.Presenter {

    private MultiLoginActivityMVP.View view;
    private MultiLoginActivityMVP.Model model;

    MultiLoginActivityPresenter(MultiLoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MultiLoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginEmailClicked() {
        this.view.loginEmail();
    }

    @Override
    public void loginGoogleClicked() {
        this.view.loginGoogle();
    }

    @Override
    public void loginFacebookClicked() {
        this.view.loginFacebook();
    }

    @Override
    public void loginInstagramClicked() {
        this.view.loginInstagram();
    }

    @Override
    public void registerNowClicked() {
        this.view.registerNow();
    }

    @Override
    public void termsUseClicked() {
        this.view.termsUse();
    }
}
