package com.pycca.pycca.signup;

import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;

public interface SignUpActivityMVP {

    interface View {

        String getEmail();

        String getPassword();

        boolean isClubPyccaPartner();

        String getIdentificationCard();

        String getClubPyccaCardNumber();

        void showEmailRequired();

        void showInvalidEmail();

        void showPasswordRequired();

        void showIdentificationCardRequired();

        void showClubPyccaCardNumberRequired();

        void showRootView();

        void hideRootView();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showFailureAnimation();

        void hideFailureAnimation();

        void showErrorMessage(int error);

        void goToHostActivity();

    }

    interface Presenter {

        void setView(SignUpActivityMVP.View view);

        void signUpClicked(SignUpActivity signUpActivity);

        void finishedDoneAnimation();

        void finishedFailureAnimation();

    }

    interface Model {

        void firebaseCreateUserWithEmailAndPassword(final SignUpActivity signUpActivity, String email, String password, boolean clubPyccaPartner, String identificationCard, String clubPyccaCardNumber, BaseResponse baseResponse, final SignUpActivityMVP.TaskListener taskListener);

        void userSubscribeToTopic(String topic);

        void userUnsubscribeFromTopic(String topic);

    }

    interface TaskListener {

        void onSuccess(User user);

        void onError(int errorCode);

    }

}
