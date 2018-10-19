package com.pycca.pycca.signup;

import com.pycca.pycca.util.Util;

public class SignUpActivityPresenter implements SignUpActivityMVP.Presenter, SignUpActivityMVP.TaskListener {

    private SignUpActivityMVP.View view;
    private SignUpActivityMVP.Model model;

    public SignUpActivityPresenter(SignUpActivityMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(SignUpActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void registerClicked() {
        if(validate()){
            view.showLoadingAnimation();
            model.saveUserAuthentication(view.getEmail(), view.getPassword(), view.getIdentification(), view.getCardNumber(), this, view.getActivity());
        }
    }

    @Override
    public void finishedDoneAnimation() {
        view.goToLoginActivity();
    }

    @Override
    public void onSucess() {
        view.showDoneAnimation();
    }

    @Override
    public void onError(int errorCode) {
        view.hideLoadingAnimation();
        view.showErrorMessage(errorCode);
    }

    public boolean validate (){
        String email = view.getEmail();
        String password = view.getPassword();
        String identification = view.getIdentification();
        String clubPyccaCardNumber = view.getCardNumber();
        if(email.isEmpty()){
            view.showEmailRequired();
            return false;
        }else if (!Util.checkValidEmail(email)){
            view.showInvalidEmail();
            return false;
        }else if (password.isEmpty()){
            view.showPasswordRequired();
            return false;
        }else if (view.isClubPyccaMember()){
            if (identification.isEmpty()){
                view.showIdentificationRequired();
                return false;
            }else if(clubPyccaCardNumber.isEmpty()){
                view.showCardNumberRequired();
                return false;
            }
        }
        return true;
    }
}
