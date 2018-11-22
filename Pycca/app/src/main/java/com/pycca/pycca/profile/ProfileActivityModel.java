package com.pycca.pycca.profile;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.SharedPreferencesManager;

public class ProfileActivityModel implements ProfileActivityMVP.Model {

    private FirebaseAuth firebaseAuth;
    private FirebaseMessaging firebaseMessaging;

    ProfileActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseMessaging = FirebaseMessaging.getInstance();
    }

    @Override
    public User getUser(ProfileActivity profileActivity) {
        return SharedPreferencesManager.getInstance(profileActivity).getUser();
    }

    @Override
    public void userLogout(ProfileActivity profileActivity) {
        firebaseAuth.signOut();
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
