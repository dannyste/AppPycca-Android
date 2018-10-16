package com.pycca.pycca.multilogin;

public interface MultiLoginActivityMVP {

    interface View {

        void loginEmail();

        void loginGoogle();

        void loginFacebook();

        void loginInstagram();

        void registerNow();

        void termsUse();

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
