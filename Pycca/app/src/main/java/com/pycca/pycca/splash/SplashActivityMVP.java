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

        void configureParameter(SplashActivity splashActivity);

    }

    interface Model {

        void setParameter(SplashActivity splashActivity, SplashActivityMVP.TaskListener taskListener);

        void userSubscribeToTopic(String topic);

        void userUnsubscribeFromTopic(String topic);

    }

    interface TaskListener {

        void onSuccess(User user);

        void onError(int error);

    }

}
