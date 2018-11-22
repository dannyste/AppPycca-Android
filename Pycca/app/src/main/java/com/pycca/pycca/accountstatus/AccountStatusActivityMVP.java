package com.pycca.pycca.accountstatus;

import com.pycca.pycca.balance.BalanceActivity;
import com.pycca.pycca.pojo.Balance;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;

public interface AccountStatusActivityMVP {

    interface View {

        void setData(Balance balance, String clubPyccaNumber);

        void showMessage(int strCode);

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showErrorAnimation();

        void finishActivity();

        AccountStatusActivity getActivity();
    }

    interface Presenter {

        void setView(AccountStatusActivityMVP.View view);

        void loadData(AccountStatusActivity activity);

        void downdloadPDF();

    }

    interface Model {

        User getUser(AccountStatusActivity activity);

        Balance getBalance(BaseResponse baseResponse, String clubPyccaCardNumber);
    }
}
