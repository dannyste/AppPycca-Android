package com.pycca.pycca.cardblocking;

import com.pycca.pycca.pojo.Card;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

public interface CardBlockingActivityMVP {

    interface View {

        void showRootView();

        void hideRootView();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showFailureAnimation();

        void hideFailureAnimation();

        void showErrorMessage(int error);

        void showErrorAnimation();

        void hideErrorAnimation();

        void updateTextPrincipalCardRadioButton(Card card);

        void showAdditionalCardsTextView();

        void addAdditionalCardsRadioButton(ArrayList<Card> cardArrayList);

        void showAlertDialogReason();

        void setReason(String reason);

        void showAlertDialogBlock();

    }

    interface Presenter {

        void setView(CardBlockingActivityMVP.View view);

        void loadCardsArrayList(CardBlockingActivity cardBlockingActivity);

        void reasonClicked();

        void reasonItemClicked(String reason);

        void blockClicked();

        void blockPositiveButtonClicked();

        void finishedDoneAnimation();

        void finishedFailureAnimation();

        void errorTouchRetryClicked(CardBlockingActivity cardBlockingActivity);

    }

    interface Model {

        User getUser(CardBlockingActivity cardBlockingActivity);

        ArrayList<Card> getCardArrayList(BaseResponse baseResponse);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int error);

    }

}
