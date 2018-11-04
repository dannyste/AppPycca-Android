package com.pycca.pycca.splash;

import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.SharedPreferencesManager;

public class SplashActivityModel implements SplashActivityMVP.Model {

    SplashActivityModel() {

    }

    @Override
    public User getUser(SplashActivity splashActivity) {
        return SharedPreferencesManager.getInstance(splashActivity).getUser();
    }

}
