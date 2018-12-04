package com.pycca.pycca.cardblocking;

import com.pycca.pycca.pojo.Card;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

public interface CardBlockingActivityMVP {

    interface View {

        String getClubPyccaCardNumber();

        String getReason();

        void setReason(String reason);

        void showReasonRequired();

        void showRootView();

        void hideRootView();

        void showConfirmationMessage();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showFailureAnimation();

        void hideFailureAnimation();

        void showErrorMessage(int error);

        void showErrorAnimation();

        void hideErrorAnimation();

        void updateTextPrincipalCardRadioButton(ArrayList<Card> cardArrayList);

        void showAdditionalCardsTextView();

        void addAdditionalCardsRadioButton(ArrayList<Card> cardArrayList);

        void showAlertDialogReason();

        void showAlertDialogBlock();

    }

    interface Presenter {

        void setView(CardBlockingActivityMVP.View view);

        void loadCardsArrayList(CardBlockingActivity cardBlockingActivity);

        void reasonClicked();

        void reasonItemClicked(String reason);

        void blockClicked();

        void blockPositiveButtonClicked(CardBlockingActivity cardBlockingActivity);

        void finishedDoneAnimation();

        void finishedFailureAnimation();

        void errorTouchRetryClicked(CardBlockingActivity cardBlockingActivity);

    }

    interface Model {

        User getUser(CardBlockingActivity cardBlockingActivity);

        void setUser(CardBlockingActivity cardBlockingActivity, User user, TaskListener taskListener);

        ArrayList<Card> getCardArrayList(BaseResponse baseResponse);

    }

    interface TaskListener {

        void onSuccess(User user);

        void onError(int error);

    }

}
