package com.pycca.pycca.splash;

import com.pycca.pycca.pojo.Parameter;
import com.pycca.pycca.pojo.User;

public interface SplashActivityMVP {

    interface View {

        void showPyccaAnimation();

        void checkPermission();

        void goToMultiLoginActivity();

        void goToHostActivity();

    }

    interface Presenter {

        void setView(SplashActivityMVP.View view);

        void startPyccaAnimation();

        void finishedPyccaAnimation();

        void getParameter(SplashActivity splashActivity);

        void getCurrentUser(SplashActivity splashActivity);

    }

    interface Model {

        void getParameter(SplashActivity splashActivity);

        User getUser(SplashActivity splashActivity);

    }

}
