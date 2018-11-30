package com.pycca.pycca.host;

import android.view.MenuItem;

import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;

public interface HostActivityMVP {

    interface View {

        void showFragment(MenuItem menuItem);

        void showAlertDialogClubPyccaPartner();

        void hideAlertDialogClubPyccaPartner();

        String getIdentificationCard();

        String getClubPyccaCardNumber();

        void showIdentificationCardRequired();

        void clubPyccaCardNumberRequired();

        void showRootView();

        void hideRootView();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showFailureAnimation();

        void hideFailureAnimation();

        void showErrorMessage(int error);

        void goToClubPyccaPartnerActivity();

    }

    interface Presenter {

        void setView(HostActivityMVP.View view);

        void navigationItemSelected(HostActivity profileActivity, MenuItem menuItem);

        void validateClicked(HostActivity hostActivity);

        void requestNowClicked();

        void finishedDoneAnimation(MenuItem menuItem);

        void finishedFailureAnimation();

    }

    interface Model {

        User getUser(HostActivity hostActivity);

        void setUser(HostActivity hostActivity, String identificationCard, String clubPyccaCardNumber, BaseResponse baseResponse, HostActivityMVP.TaskListener taskListener);

        void userSubscribeToTopic(String topic);

        void userUnsubscribeFromTopic(String topic);

    }

    interface TaskListener {

        void onSuccess(User user);

        void onError(int error);

    }

}
