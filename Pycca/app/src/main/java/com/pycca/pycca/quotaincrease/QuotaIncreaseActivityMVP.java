package com.pycca.pycca.quotaincrease;

import android.content.Context;

import com.pycca.pycca.pojo.User;

public interface QuotaIncreaseActivityMVP {

    interface View {
        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showErrorAnimation();

        void showErrorMessage(int errorCode);

        void goToHostActivity();

        void showViewFormSended();

        void showViewForm();

        String getQuotaWanted();

        Context getContext();
    }

    interface Presenter {

        void setView(QuotaIncreaseActivityMVP.View view);

        void sendRequest();

        void finishedDoneAnimation();

        void finishedErrorAnimation();

        boolean isFormValid();

    }

    interface Model {
        User getUser(Context context);
    }

}