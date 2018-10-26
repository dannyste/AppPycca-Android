package com.pycca.pycca.signup;

import android.app.Activity;

import com.pycca.pycca.pojo.User;

public interface SignUpActivityMVP {

    interface View {

        String getEmail();

        String getPassword();

        String getIdentification();

        String getCardNumber();

        boolean isClubPyccaMember();

        void showInvalidEmail();

        void showEmailRequired();

        void showPasswordRequired();

        void showIdentificationRequired();

        void showCardNumberRequired();

        void goToLoginActivity();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showErrorMessage(int errorCode);

        SignUpActivity getActivity();
    }

    interface Presenter {

        void setView(SignUpActivityMVP.View view);

        void registerClicked();

        void finishedDoneAnimation();

    }

    interface Model {

        void saveUserAuthentication(String email, String password, String identificationCard, String clubPyccaCardNumber, TaskListener listener, SignUpActivity signUpActivity);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int errorCode);
    }

}
