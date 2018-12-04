package com.pycca.pycca.ourshopdetail;

import com.pycca.pycca.pojo.OurShopDetail;

public interface OurShopDetailActivityMVP {

    interface View {

        void clearMarkersGoogleMap();

        void addMarkerGoogleMap(OurShopDetail ourShopDetail);

    }

    interface Presenter {

        void setView(OurShopDetailActivityMVP.View view);

        void itemClicked(OurShopDetail ourShopDetail);

    }

    interface Model {


    }

}
