package com.pycca.pycca.host;

import android.view.MenuItem;

import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;

public interface HostActivityMVP {

    interface View {

        void showFragment(MenuItem menuItem);

        void showAlertDialog();

        String getIdentificationCard();

        String getClubPyccaCardNumber();

        void showIdentificationCardRequired();

        void clubPyccaCardNumberRequired();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void hideAlertDialog();

        void goToClubPyccaPartnerActivity();

        void showErrorMessage(int error);

    }

    interface Presenter {

        void setView(HostActivityMVP.View view);

        void navigationItemSelected(HostActivity profileActivity, MenuItem menuItem);

        void validateClicked(HostActivity hostActivity);

        void finishedDoneAnimation(MenuItem menuItem);

        void requestNowClicked();

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
