package com.pycca.pycca.login;

import com.pycca.pycca.util.Util;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter, LoginActivityMVP.TaskListener {

    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginClicked() {
        if(validate()){
            view.disableWidgets();
            view.showProgress();
            model.doLogin(view.getEmail(), view.getPassword(), this);
        }
    }

    @Override
    public void forgotPasswordClicked() {
        view.goToForgotPasswordActivity();
    }

    public boolean validate (){
        String email = view.getEmail();
        String password = view.getPassword();
        if(email.isEmpty()){
            view.showEmailRequired();
            return false;
        }else if (!Util.checkValidEmail(email)){
            view.showInvalidEmail();
            return false;
        }else if (password.isEmpty()){
            view.showPasswordRequired();
            return false;
        }
        return true;
    }

    @Override
    public void onSucess() {
        view.enableWidgets();
        view.hideProgress();
        view.goToHostActivity();
    }

    @Override
    public void onError(int errorCode) {
        view.enableWidgets();
        view.hideProgress();
        view.showErrorMessage(errorCode);

    }
}
