package com.pycca.pycca.multilogin;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.Constants;

public class MultiLoginActivityPresenter implements MultiLoginActivityMVP.Presenter, MultiLoginActivityMVP.TaskListener {

    private MultiLoginActivityMVP.View view;
    private MultiLoginActivityMVP.Model model;

    private GoogleSignInClient googleSignInClient;

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    private static final int RC_LOGIN_GOOGLE = 9001;

    MultiLoginActivityPresenter(MultiLoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MultiLoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginFacebookClicked() {
        loginButton.performClick();
    }

    @Override
    public void loginGoogleClicked(MultiLoginActivity multiLoginActivity) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        multiLoginActivity.startActivityForResult(signInIntent, RC_LOGIN_GOOGLE);
    }

    @Override
    public void loginTwitterClicked() {
    }

    @Override
    public void loginEmailClicked() {
        view.goToLoginActivity();
    }

    @Override
    public void newHereRegisterNowClicked() {
        view.goToSignUpActivity();
    }

    @Override
    public void termsUseClicked() {
        view.termsUse();
    }

    @Override
    public void configureFacebookSignIn(final MultiLoginActivity multiLoginActivity, LoginButton loginButton) {
        callbackManager = CallbackManager.Factory.create();
        this.loginButton = loginButton;
        this.loginButton.setReadPermissions("email", "public_profile");
        this.loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                model.firebaseAuthWithFacebook(multiLoginActivity, loginResult.getAccessToken(), MultiLoginActivityPresenter.this);
            }

            @Override
            public void onCancel() {
                MultiLoginActivityPresenter.this.onError(R.string.error_default);
            }

            @Override
            public void onError(FacebookException error) {
                MultiLoginActivityPresenter.this.onError(R.string.error_default);
            }
        });
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
    public void onActivityResultFacebook(int requestCode, int resultCode, @Nullable Intent data) {
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            view.hideRootView();
            view.showLoadingAnimation();
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onActivityResultGoogle(MultiLoginActivity multiLoginActivity, int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RC_LOGIN_GOOGLE) {
            view.hideRootView();
            view.showLoadingAnimation();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
                model.firebaseAuthWithGoogle(multiLoginActivity, googleSignInAccount, MultiLoginActivityPresenter.this);
            }
            catch (ApiException apiException) {
                onError(R.string.error_default);
            }
        }
    }

    @Override
    public void finishedDoneAnimation() {
        view.goToHostActivity();
    }

    @Override
    public void finishedFailureAnimation() {
        view.hideFailureAnimation();
        view.showRootView();
    }

    @Override
    public void onSuccess(User user) {
        model.userUnsubscribeFromTopic(Constants.TOPIC_INVITED);
        model.userSubscribeToTopic(Constants.TOPIC_SOCIAL_NETWORK);
        if (user.isClubPyccaPartner()) {
            model.userSubscribeToTopic(Constants.TOPIC_CLUB_PYCCA_PARTNER);
            model.userSubscribeToTopic(Constants.TOPIC_SOCIAL_NETWORK_CLUB_PYCCA_PARTNER);
        }
        else {
            model.userSubscribeToTopic(Constants.TOPIC_NOT_CLUB_PYCCA_PARTNER);
            model.userSubscribeToTopic(Constants.TOPIC_SOCIAL_NETWORK_NOT_CLUB_PYCCA_PARTNER);
        }
        view.hideLoadingAnimation();
        view.showDoneAnimation();
    }

    @Override
    public void onError(int error) {
        view.hideLoadingAnimation();
        view.showFailureAnimation();
        view.showMessageError(error);
    }

}
