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

        void configureParameter(SplashActivity splashActivity);

        void getCurrentUser(SplashActivity splashActivity);

    }

    interface Model {

        void setParameter(SplashActivity splashActivity, SplashActivityMVP.TaskListener taskListener);

        User getUser(SplashActivity splashActivity);

        void userSubscribeToTopic(String topic);

        void userUnsubscribeFromTopic(String topic);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int error);

    }

}
