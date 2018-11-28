package com.pycca.pycca.profile;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.SharedPreferencesManager;

public class ProfileActivityModel implements ProfileActivityMVP.Model {

    private FirebaseAuth firebaseAuth;
    private FirebaseMessaging firebaseMessaging;
    private LoginManager loginManager;

    ProfileActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseMessaging = FirebaseMessaging.getInstance();
        loginManager = LoginManager.getInstance();
    }

    @Override
    public User getUser(ProfileActivity profileActivity) {
        return SharedPreferencesManager.getInstance(profileActivity).getUser();
    }

    @Override
    public void userLogout(ProfileActivity profileActivity) {
        firebaseAuth.signOut();
        loginManager.logOut();
        LoginManager.getInstance().logOut();
        SharedPreferencesManager.getInstance(profileActivity).setUser(null);
    }

    @Override
    public void userSubscribeToTopic(String topic) {
        firebaseMessaging.subscribeToTopic(topic);
    }

    @Override
    public void userUnsubscribeFromTopic(String topic) {
        firebaseMessaging.unsubscribeFromTopic(topic);
    }

}
