package com.pycca.pycca.nearestshop;

import com.pycca.pycca.pojo.OurShopsDetails;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

public interface NearestShopActivityMVP {

    interface View {

        void showMarkersGoogleMap(ArrayList<OurShopsDetails> ourShopsDetailsArrayList);

        void showErrorMessage(int error);

    }

    interface Presenter {

        void setView(NearestShopActivityMVP.View view);

        void loadOurShopsDetailsArrayList();

    }

    interface Model {

        ArrayList<OurShopsDetails> getOurShopsDetailsArrayList(BaseResponse baseResponse);

    }

    interface TaskListener {

        void onSuccess();

        void onError(int error);

    }

}
