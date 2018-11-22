package com.pycca.pycca.ourshops;

import com.pycca.pycca.pojo.OurShops;
import com.pycca.pycca.pojo.OurShopsDetails;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

public interface OurShopsActivityMVP {

    interface View {

        void showRootView();

        void hideRootView();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showErrorAnimation();

        void hideErrorAnimation();

        void updateDataRecyclerView(ArrayList<OurShops> ourShopsArrayList);

        void goToOurShopsDetailsActivity(ArrayList<OurShopsDetails> ourShopsDetailsArrayList);

    }

    interface Presenter {

        void setView(OurShopsActivityMVP.View view);

        void errorTouchRetryClicked();

        void loadOurShopsArrayList();

        void itemClicked(ArrayList<OurShopsDetails> ourShopsDetailsArrayList);

    }

    interface Model {

        ArrayList<OurShops> getOurShopsArrayList(BaseResponse baseResponse);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int error);

    }

}
