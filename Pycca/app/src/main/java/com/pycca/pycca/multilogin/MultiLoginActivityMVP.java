package com.pycca.pycca.multilogin;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.pycca.pycca.pojo.User;

public interface MultiLoginActivityMVP {

    interface View {

        void showRootView();

        void hideRootView();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showFailureAnimation();

        void hideFailureAnimation();

        void showErrorMessage(int error);

        void goToHostActivity();

        void goToLoginActivity();

        void goToSignUpActivity();

        void termsUse();

    }

    interface Presenter {

        void setView(MultiLoginActivityMVP.View view);

        void loginFacebookClicked();

        void loginGoogleClicked(MultiLoginActivity multiLoginActivity);

        void loginEmailClicked();

        void newHereRegisterNowClicked();

        void termsUseClicked();

        void configureFacebookSignIn(MultiLoginActivity multiLoginActivity, LoginButton loginButton);

        void configureGoogleSignIn(MultiLoginActivity multiLoginActivity);

        void onActivityResultFacebook(int requestCode, int resultCode, @Nullable Intent data);

        void onActivityResultGoogle(MultiLoginActivity multiLoginActivity, int requestCode, int resultCode, @Nullable Intent data);

        void finishedDoneAnimation();

        void finishedFailureAnimation();

    }

    interface Model {

        void firebaseAuthWithFacebook(MultiLoginActivity multiLoginActivity, AccessToken accessToken, MultiLoginActivityMVP.TaskListener taskListener);

        void firebaseAuthWithGoogle(MultiLoginActivity multiLoginActivity, GoogleSignInAccount googleSignInAccount, MultiLoginActivityMVP.TaskListener taskListener);

        void userSubscribeToTopic(String topic);

        void userUnsubscribeFromTopic(String topic);

    }

    interface TaskListener {

        void onSuccess(User user);

        void onError(int error);

    }

}
