package com.pycca.pycca.multilogin;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.pycca.pycca.R;

public class MultiLoginActivityPresenter implements MultiLoginActivityMVP.Presenter, MultiLoginActivityMVP.TaskListener {

    private MultiLoginActivityMVP.View view;
    private MultiLoginActivityMVP.Model model;

    private GoogleSignInClient googleSignInClient;

    private static final int RC_LOGIN_GOOGLE = 9001;
    private static final int RC_LOGIN_FACEBOOK = 9002;

    MultiLoginActivityPresenter(MultiLoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MultiLoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void currentFirebaseUser() {
        FirebaseUser firebaseUser = model.getCurrentFirebaseUser();
        if (firebaseUser != null) {
            view.goToHostActivity();
        }
    }

    @Override
    public void loginEmailClicked() {
        this.view.loginEmail();
    }

    @Override
    public void loginGoogleClicked(MultiLoginActivity multiLoginActivity) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        multiLoginActivity.startActivityForResult(signInIntent, RC_LOGIN_GOOGLE);
    }

    @Override
    public void loginFacebookClicked() {
        this.view.loginFacebook();
    }

    @Override
    public void loginInstagramClicked() {
        this.view.loginInstagram();
    }

    @Override
    public void registerNowClicked() {
        this.view.registerNow();
    }

    @Override
    public void termsUseClicked() {
        this.view.termsUse();
    }

    @Override
    public void configureGoogleSignIn(MultiLoginActivity multiLoginActivity) {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(multiLoginActivity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(multiLoginActivity, googleSignInOptions);
    }

    @Override
    public void onActivityResultGoogle(MultiLoginActivity multiLoginActivity, int requestCode, int resultCode, @Nullable Intent data) {
        view.showLoadingAnimation();
        if (requestCode == RC_LOGIN_GOOGLE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
                model.firebaseAuthWithGoogle(multiLoginActivity, googleSignInAccount, MultiLoginActivityPresenter.this);
            }
            catch (ApiException apiException) {
                view.hideLoadingAnimation();
            }
        }
    }

    @Override
    public void onActivityResultFacebook(int requestCode, int resultCode, @Nullable Intent data) {

    }

    @Override
    public void finishedDoneAnimation() {
        view.goToHostActivity();
    }

    @Override
    public void onSuccess() {
        view.showDoneAnimation();
    }

    @Override
    public void onError() {
        view.hideLoadingAnimation();
    }
}
