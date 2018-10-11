package com.pycca.pycca.multilogin;

import android.content.Intent;

public class MultiLoginActivityPresenter implements MultiLoginActivityMVP.Presenter {

    private MultiLoginActivityMVP.View view;
    private MultiLoginActivityMVP.Model model;

    @Override
    public void setView(MultiLoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginEmailClicked() {

    }

    @Override
    public void loginGoogleClicked() {
        this.view.loginGoogle();
    }

    @Override
    public void loginFacebookClicked() {

    }

    @Override
    public void loginInstagramClicked() {

    }

    @Override
    public void registerNowClicked() {

    }

    @Override
    public void termsUseClicked() {

    }
}
