package com.pycca.pycca.login;

import com.pycca.pycca.util.Constants;
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
        if (validate()) {
            view.showLoadingAnimation();
            model.firebaseAuthWithEmailAndPassword(view.getEmail(), view.getPassword(), LoginActivityPresenter.this);
        }
    }

    public boolean validate () {
        String email = view.getEmail();
        String password = view.getPassword();
        if (email.isEmpty()) {
            view.showEmailRequired();
            return false;
        }
        else if (!Util.checkValidEmail(email)) {
            view.showInvalidEmail();
            return false;
        }
        else if (password.isEmpty()) {
            view.showPasswordRequired();
            return false;
        }
        return true;
    }

    @Override
    public void finishedDoneAnimation() {
        view.goToHostActivity();
    }

    @Override
    public void forgotPasswordClicked() {
        view.goToForgotPasswordActivity();
    }

    @Override
    public void onSuccess() {
        Util.subscribeToTopicFCM(Constants.PUSH_TOPIC_NATIVO);
        view.showDoneAnimation();
    }

    @Override
    public void onError(int error) {
        view.hideLoadingAnimation();
        view.showErrorMessage(error);
    }

}
