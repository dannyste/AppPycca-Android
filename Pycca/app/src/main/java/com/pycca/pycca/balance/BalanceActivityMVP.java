package com.pycca.pycca.balance;

public interface BalanceActivityMVP {

    interface View {

        void setData(String cardNumber, String availableCredit, String usedCredit, String aprovedQuota, String dateForPay);

        void showMessage(int strCode);

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void finishActivity();

        BalanceActivity getActivity();
    }

    interface Presenter {

        void setView(BalanceActivityMVP.View view);

        void loadData();

        void finishedDoneAnimation();

    }

    interface Model {

        void getBalance(TaskListener listener);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int errorCode);
    }

}
