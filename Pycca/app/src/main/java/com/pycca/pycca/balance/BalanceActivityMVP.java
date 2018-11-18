package com.pycca.pycca.balance;

import com.pycca.pycca.pojo.Balance;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;

public interface BalanceActivityMVP {

    interface View {

        void setData(Balance balance, String clubPyccaNumber);

        void showMessage(int strCode);

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showErrorAnimation();

        void finishActivity();

        BalanceActivity getActivity();
    }

    interface Presenter {

        void setView(BalanceActivityMVP.View view);

        void loadData(BalanceActivity activity);

    }

    interface Model {

        User getUser(BalanceActivity activity);

        Balance getBalance(BaseResponse baseResponse, String clubPyccaCardNumber);
    }
}
