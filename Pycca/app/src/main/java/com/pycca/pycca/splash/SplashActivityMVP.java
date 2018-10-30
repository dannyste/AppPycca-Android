package com.pycca.pycca.splash;

import com.pycca.pycca.pojo.User;

public interface SplashActivityMVP {

    interface View {

        void showPyccaAnimation();

        void goToMultiLoginActivity();

        void goToHostActivity();

    }

    interface Presenter {

        void setView(SplashActivityMVP.View view);

        void startPyccaAnimation();

        void getCurrentUser(SplashActivity splashActivity);

    }

    interface Model {

        User getUser(SplashActivity splashActivity);

    }

}
