package com.pycca.pycca.login;

public interface LoginActivityMVP {

    interface View {

        String getEmail();

        String getPassword();

        void showInvalidEmail();

        void showRequiredEmail();

        void showRequiredPassword();

        void showIncorrectCredentials();

    }

    interface Presenter {

        void setView(LoginActivityMVP.View view);

        void loginClicked();

        void forgotPasswordClicked();

    }

    interface Model {

        void setUser(String email, String password);

        User getUser();

    }

}
