package com.pycca.pycca.accountstatus;

import com.pycca.pycca.pojo.AccountStatus;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;

public interface AccountStatusActivityMVP {

    interface View {

        void setData(AccountStatus accountStatus);

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

        AccountStatus getBalance(BaseResponse baseResponse, String clubPyccaCardNumber);
    }
}
