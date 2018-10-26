package com.pycca.pycca.login;

public interface LoginActivityMVP {

    interface View {

        String getEmail();

        String getPassword();

        void showInvalidEmail();

        void showEmailRequired();

        void showPasswordRequired();

        void goToHostActivity();

        void goToForgotPasswordActivity();

        void disableWidgets();

        void enableWidgets();

        void showProgress();

        void hideProgress();

        void showErrorMessage(int errorCode);
    }

    interface Presenter {

        void setView(LoginActivityMVP.View view);

        void loginClicked();

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
