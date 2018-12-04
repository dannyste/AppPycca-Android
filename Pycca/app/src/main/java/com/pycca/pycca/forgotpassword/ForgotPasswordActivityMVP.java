package com.pycca.pycca.forgotpassword;

public interface ForgotPasswordActivityMVP {

    interface View {

        String getEmail();

        void showEmailRequired();

        void showInvalidEmail();

        void showRootView();

        void hideRootView();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showFailureAnimation();

        void hideFailureAnimation();

        void showErrorMessage(int error);

        void goToMultiLoginActivity();

    }

    interface Presenter {

        void setView(View view);

        void resetPasswordClicked();

        void finishedDoneAnimation();

        void finishedFailureAnimation();

    }

    interface Model {

        void firebaseSendPasswordResetEmail(String email, TaskListener taskListener);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int error);
    }

}
