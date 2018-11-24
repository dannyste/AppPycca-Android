package com.pycca.pycca.multilogin;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.pycca.pycca.pojo.User;

public interface MultiLoginActivityMVP {

    interface View {

        void loginTwitter();

        void loginEmail();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void goToHostActivity();

        void registerNow();

        void termsUse();

    }

    interface Presenter {

        void setView(MultiLoginActivityMVP.View view);

        void loginFacebookClicked();

        void loginGoogleClicked(MultiLoginActivity multiLoginActivity);

        void loginTwitterClicked();

        void loginEmailClicked();

        void configureFacebookSignIn(MultiLoginActivity multiLoginActivity, LoginButton loginButton);

        void configureGoogleSignIn(MultiLoginActivity multiLoginActivity);

        void onActivityResultFacebook(int requestCode, int resultCode, @Nullable Intent data);

        void onActivityResultGoogle(MultiLoginActivity multiLoginActivity, int requestCode, int resultCode, @Nullable Intent data);

        void finishedDoneAnimation();

        void newHereRegisterNowClicked();

        void termsUseClicked();

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
