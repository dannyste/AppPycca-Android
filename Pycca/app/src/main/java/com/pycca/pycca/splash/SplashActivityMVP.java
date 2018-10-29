package com.pycca.pycca.splash;

import com.google.firebase.auth.FirebaseUser;

public interface SplashActivityMVP {

    interface View {

    }

    interface Presenter {

        void setView(SplashActivityMVP.View view);

    }

    interface Model {

        FirebaseUser getCurrentFirebaseUser();

    }

    interface TaskListener {

        void onSuccess();

        void onError(int error);

    }

}
