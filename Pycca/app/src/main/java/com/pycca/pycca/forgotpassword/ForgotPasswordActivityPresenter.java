package com.pycca.pycca.forgotpassword;

import com.pycca.pycca.util.Util;

public class ForgotPasswordActivityPresenter implements ForgotPasswordActivityMVP.Presenter, ForgotPasswordActivityMVP.TaskListener{

    private ForgotPasswordActivityMVP.View view;
    private ForgotPasswordActivityMVP.Model model;

    ForgotPasswordActivityPresenter(ForgotPasswordActivityMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(ForgotPasswordActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void resetPasswordClicked() {
        if (validateForm()) {
            view.hideRootView();
            view.showLoadingAnimation();
            String email = view.getEmail();
            model.firebaseSendPasswordResetEmail(email,ForgotPasswordActivityPresenter.this);
        }
    }

    @Override
    public void finishedDoneAnimation() {
        view.goToMultiLoginActivity();
    }

    @Override
    public void finishedFailureAnimation() {
        view.hideFailureAnimation();
        view.showRootView();
    }

    @Override
    public void onSuccess() {
        view.hideLoadingAnimation();
        view.showDoneAnimation();
    }

    @Override
    public void onError(int error) {
        view.hideLoadingAnimation();
        view.showFailureAnimation();
        view.showErrorMessage(error);
    }

    private boolean validateForm() {
        String email = view.getEmail();
        if (email.isEmpty()) {
            view.showEmailRequired();
            return false;
        }
        else if (!Util.checkValidEmail(email)) {
            view.showInvalidEmail();
            return false;
        }
        return true;
    }

}
