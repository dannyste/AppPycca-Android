package com.pycca.pycca.multilogin;

public interface MultiLoginActivityMVP {

    interface View {

        void loginGoogle();

    }

    interface Presenter {

        void setView(MultiLoginActivityMVP.View view);

        void loginEmailClicked();

        void loginGoogleClicked();

        void loginFacebookClicked();

        void loginInstagramClicked();

        void registerNowClicked();

        void termsUseClicked();

    }

    interface Model {

    }

}
