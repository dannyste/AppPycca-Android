package com.pycca.pycca.login;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    public LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginClicked() {

    }

    @Override
    public void forgotPasswordClicked() {

    }

}
