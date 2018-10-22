package com.pycca.pycca.forgotpassword;

import com.pycca.pycca.R;
import com.pycca.pycca.util.Util;

public class ForgotPasswordActivityPresenter implements ForgotPasswordActivityMVP.Presenter, ForgotPasswordActivityMVP.TaskListener{

    private ForgotPasswordActivityMVP.View view;
    private ForgotPasswordActivityMVP.Model model;

    public ForgotPasswordActivityPresenter(ForgotPasswordActivityMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(ForgotPasswordActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void resetPasswordClicked() {
        if(validate()){
            view.showLoadingAnimation();
            model.resetPasswordAuthentication(view.getEmail(),this);
        }
    }

    @Override
    public void finishedDoneAnimation() {
        view.goToMultilogin();
    }

    public boolean validate (){
        String email = view.getEmail();
        if(email.isEmpty()){
            view.showEmailRequired();
            return false;
        }else if (!Util.checkValidEmail(email)){
            view.showInvalidEmail();
            return false;
        }
        return true;
    }

    @Override
    public void onSuccess() {
        view.showMessage(R.string.reset_password_email_sended);
        view.showDoneAnimation();
    }

    @Override
    public void onError(int errorCode) {
        view.hideLoadingAnimation();
        view.showMessage(errorCode);
    }
}
