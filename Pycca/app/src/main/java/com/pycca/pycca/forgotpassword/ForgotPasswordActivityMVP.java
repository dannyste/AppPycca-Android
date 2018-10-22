package com.pycca.pycca.forgotpassword;

public interface ForgotPasswordActivityMVP {

    interface View {

        String getEmail();

        void showInvalidEmail();

        void showEmailRequired();

        void showMessage(int code);

        void goToMultilogin();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

    }

    interface Presenter {

        void setView(View view);

        void resetPasswordClicked();

        void finishedDoneAnimation();

    }

    interface Model {

        void resetPasswordAuthentication(String email, TaskListener listener);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int errorCode);
    }

}
