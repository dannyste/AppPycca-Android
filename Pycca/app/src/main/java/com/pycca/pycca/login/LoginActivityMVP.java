package com.pycca.pycca.login;

import com.pycca.pycca.pojo.User;

public interface LoginActivityMVP {

    interface View {

        String getEmail();

        String getPassword();

        void showEmailRequired();

        void showInvalidEmail();

        void showPasswordRequired();

        void showRootView();

        void hideRootView();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showFailureAnimation();

        void hideFailureAnimation();

        void showErrorMessage(int error);

        void goToHostActivity();

        void goToForgotPasswordActivity();

    }

    interface Presenter {

        void setView(LoginActivityMVP.View view);

        void loginClicked(LoginActivity loginActivity);

        void forgotPasswordClicked();

        void finishedDoneAnimation();

        void finishedFailureAnimation();

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
