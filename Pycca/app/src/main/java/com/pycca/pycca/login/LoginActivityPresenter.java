package com.pycca.pycca.login;

import com.pycca.pycca.pojo.User;
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
    public void loginClicked(LoginActivity loginActivity) {
        if (validateForm()) {
            view.showLoadingAnimation();
            model.firebaseAuthWithEmailAndPassword(loginActivity, view.getEmail(), view.getPassword(), LoginActivityPresenter.this);
        }
    }

    public boolean validateForm() {
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
    public void onSuccess(User user) {
        model.userUnsubscribeFromTopic(Constants.TOPIC_INVITED);
        model.userSubscribeToTopic(Constants.TOPIC_NATIVE);
        if (user.isClubPyccaPartner()) {
            model.userSubscribeToTopic(Constants.TOPIC_CLUB_PYCCA_PARTNER);
            model.userSubscribeToTopic(Constants.TOPIC_NATIVE_CLUB_PYCCA_PARTNER);
        }
        else {
            model.userSubscribeToTopic(Constants.TOPIC_NOT_CLUB_PYCCA_PARTNER);
            model.userSubscribeToTopic(Constants.TOPIC_NATIVE_NOT_CLUB_PYCCA_PARTNER);
        }
        view.showDoneAnimation();
    }

    @Override
    public void onError(int error) {
        view.hideLoadingAnimation();
        view.showErrorMessage(error);
    }

}
