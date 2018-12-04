package com.pycca.pycca.quotacalculator;

import android.content.Context;

import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.restApi.model.QuotaCalculatorResponse;

import java.util.ArrayList;

public interface QuotaCalculatorActivityMVP {

    interface View {
        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showErrorAnimation();

        void showErrorMessage(int errorCode);

        void goToHostActivity();

        void showViewFormQuotas();

        void showViewFormAmountToDiffer();

        String getAmountToDiffer();

        void resetFormQuotas();

        void resetFormAmountToDiffer();

        void setDataToFormQuotas(ArrayList<QuotaCalculatorResponse> formQuotas, String amountToDiffer);

        Context getContext();

    }

    interface Presenter {

        void setView(QuotaCalculatorActivityMVP.View view);

        void sendRequest();

        void calculateAgain();

        void finishedDoneAnimation();

        void finishedErrorAnimation();

        boolean isFormValid();

    }

    interface Model {
        User getUser(Context context);

        ArrayList<QuotaCalculatorResponse> getQuotas(BaseResponse response);
    }

}
