package com.pycca.pycca.ourshop;

import com.pycca.pycca.pojo.OurShop;
import com.pycca.pycca.pojo.OurShopDetail;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

public interface OurShopActivityMVP {

    interface View {

        void showRootView();

        void hideRootView();

        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showErrorAnimation();

        void hideErrorAnimation();

        void updateDataRecyclerView(ArrayList<OurShop> ourShopArrayList);

        void goToNearestShopActivity();

        void goToOurShopsDetailsActivity(ArrayList<OurShopDetail> ourShopDetailArrayList);

    }

    interface Presenter {

        void setView(OurShopActivityMVP.View view);

        void loadOurShopsArrayList();

        void goNearestShopClicked();

        void itemClicked(ArrayList<OurShopDetail> ourShopDetailArrayList);

        void errorTouchRetryClicked();

        void cancelServiceCall();

    }

    interface Model {

        ArrayList<OurShop> getOurShopsArrayList(BaseResponse baseResponse);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int error);

    }

}
