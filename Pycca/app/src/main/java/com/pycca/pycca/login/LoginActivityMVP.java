package com.pycca.pycca.login;

public interface LoginActivityMVP {

    interface View {

        String getEmail();

        String getPassword();

        void showEmailRequired();

        void showInvalidEmail();

        void showPasswordRequired();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void goToHostActivity();

        void goToForgotPasswordActivity();

        void showErrorMessage(int error);

    }

    interface Presenter {

        void setView(LoginActivityMVP.View view);

        void loginClicked();

        void finishedDoneAnimation();

        void forgotPasswordClicked();

    }

    interface Model {

        void firebaseAuthWithEmailAndPassword(String email, String password, TaskListener listener);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int errorCode);
    }

}
