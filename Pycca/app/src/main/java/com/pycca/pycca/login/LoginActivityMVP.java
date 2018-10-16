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

    }

    interface Presenter {

        void setView(LoginActivityMVP.View view);

        void loginClicked();

        void forgotPasswordClicked();

    }

    interface Model {

    }

}
