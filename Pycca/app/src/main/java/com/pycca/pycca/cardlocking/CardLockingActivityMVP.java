package com.pycca.pycca.cardlocking;

import com.pycca.pycca.pojo.Card;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

public interface CardLockingActivityMVP {

    interface View {

        String getClubPyccaCardNumber();

        String getReason();

        void setReason(String reason);

        void showReasonRequired();

        void showRootView();

        void hideRootView();

        void showConfirmationMessage();

        void hideConfirmationMessage();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void hideDoneAnimation();

        void showFailureAnimation();

        void hideFailureAnimation();

        void showErrorMessage(int error);

        void showErrorAnimation();

        void hideErrorAnimation();

        void updateTextPrincipalCardRadioButton(ArrayList<Card> cardArrayList);

        void showAdditionalCardsTextView();

        void addAdditionalCardsRadioButton(ArrayList<Card> cardArrayList);

        void showAlertDialogReason();

        void showAlertDialogLock();

    }

    interface Presenter {

        void setView(CardLockingActivityMVP.View view);

        void loadCardsArrayList(CardLockingActivity cardLockingActivity);

        void reasonClicked();

        void reasonItemClicked(String reason);

        void lockClicked();

        void lockPositiveButtonClicked(CardLockingActivity cardLockingActivity);

        void finishedDoneAnimation();

        void finishedFailureAnimation();

        void errorTouchRetryClicked(CardLockingActivity cardLockingActivity);

    }

    interface Model {

        User getUser(CardLockingActivity cardLockingActivity);

        void setUser(CardLockingActivity cardLockingActivity, User user, TaskListener taskListener);

        ArrayList<Card> getCardArrayList(BaseResponse baseResponse);

    }

    interface TaskListener {

        void onSuccess(User user);

        void onError(int error);

    }

}
