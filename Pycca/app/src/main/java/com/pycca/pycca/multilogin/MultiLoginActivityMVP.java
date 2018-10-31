package com.pycca.pycca.multilogin;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;

public interface MultiLoginActivityMVP {

    interface View {

        void loginInstagram();

        void loginEmail();

        void registerNow();

        void termsUse();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void goToHostActivity();

    }

    interface Presenter {

        void setView(MultiLoginActivityMVP.View view);

        void loginFacebookClicked();

        void loginInstagramClicked();

        void loginGoogleClicked(MultiLoginActivity multiLoginActivity);

        void loginEmailClicked();

        void registerNowClicked();

        void termsUseClicked();

        void configureFacebookSignIn(MultiLoginActivity multiLoginActivity, LoginButton loginButton);

        void configureGoogleSignIn(MultiLoginActivity multiLoginActivity);

        void onActivityResultFacebook(int requestCode, int resultCode, @Nullable Intent data);

        void onActivityResultGoogle(MultiLoginActivity multiLoginActivity, int requestCode, int resultCode, @Nullable Intent data);

        void finishedDoneAnimation();

    }

    interface Model {

        void firebaseAuthWithFacebook(MultiLoginActivity multiLoginActivity, AccessToken accessToken, MultiLoginActivityMVP.TaskListener taskListener);

        void firebaseAuthWithGoogle(MultiLoginActivity multiLoginActivity, GoogleSignInAccount googleSignInAccount, MultiLoginActivityMVP.TaskListener taskListener);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int error);

    }

}
