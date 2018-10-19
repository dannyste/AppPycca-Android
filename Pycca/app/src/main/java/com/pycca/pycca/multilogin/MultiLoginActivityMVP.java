package com.pycca.pycca.multilogin;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;

public interface MultiLoginActivityMVP {

    interface View {

        void loginEmail();

        void loginInstagram();

        void registerNow();

        void termsUse();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void goToHostActivity();

    }

    interface Presenter {

        void setView(MultiLoginActivityMVP.View view);

        void currentFirebaseUser();

        void loginEmailClicked();

        void loginGoogleClicked(MultiLoginActivity multiLoginActivity);

        void loginFacebookClicked();

        void loginInstagramClicked();

        void registerNowClicked();

        void termsUseClicked();

        void configureGoogleSignIn(MultiLoginActivity multiLoginActivity);

        void configureFacebookSignIn(MultiLoginActivity multiLoginActivity, LoginButton loginButton);

        void onActivityResultGoogle(MultiLoginActivity multiLoginActivity, int requestCode, int resultCode, @Nullable Intent data);

        void onActivityResultFacebook(int requestCode, int resultCode, @Nullable Intent data);

        void finishedDoneAnimation();

    }

    interface Model {

        FirebaseUser getCurrentFirebaseUser();

        void firebaseAuthWithGoogle(MultiLoginActivity multiLoginActivity, GoogleSignInAccount googleSignInAccount, MultiLoginActivityMVP.TaskListener taskListener);

        void firebaseAuthWithFacebook(MultiLoginActivity multiLoginActivity, AccessToken accessToken, MultiLoginActivityMVP.TaskListener taskListener);

    }

    interface TaskListener {

        void onSuccess();

        void onError();
    }

}
