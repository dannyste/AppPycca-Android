package com.pycca.pycca.login;

import com.pycca.pycca.pojo.User;

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

        void loginClicked(LoginActivity loginActivity);

        void finishedDoneAnimation();

        void forgotPasswordClicked();

    }

    interface Model {

        void firebaseAuthWithEmailAndPassword(LoginActivity loginActivity, String email, String password, TaskListener taskListener);

        void userSubscribeToTopic(String topic);

        void userUnsubscribeFromTopic(String topic);

    }

    interface TaskListener {

        void onSuccess(User user);

        void onError(int errorCode);
    }

}
