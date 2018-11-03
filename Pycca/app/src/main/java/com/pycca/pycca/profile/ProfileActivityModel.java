package com.pycca.pycca.profile;

import com.google.firebase.auth.FirebaseAuth;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.SharedPreferencesManager;

public class ProfileActivityModel implements ProfileActivityMVP.Model {

    private FirebaseAuth firebaseAuth;

    ProfileActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
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

}
