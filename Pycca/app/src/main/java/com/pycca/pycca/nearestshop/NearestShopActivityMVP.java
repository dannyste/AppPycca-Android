package com.pycca.pycca.nearestshop;

import com.pycca.pycca.pojo.OurShopDetail;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

public interface NearestShopActivityMVP {

    interface View {

        void showMarkersGoogleMap(ArrayList<OurShopDetail> ourShopDetailArrayList);

        void showErrorMessage(int error);

    }

    interface Presenter {

        void setView(NearestShopActivityMVP.View view);

        void loadOurShopsDetailsArrayList();

    }

    interface Model {

        ArrayList<OurShopDetail> getOurShopsDetailsArrayList(BaseResponse baseResponse);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int error);

    }

}
