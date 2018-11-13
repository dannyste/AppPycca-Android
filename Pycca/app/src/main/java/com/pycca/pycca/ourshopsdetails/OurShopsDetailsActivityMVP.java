package com.pycca.pycca.ourshopsdetails;

import com.google.android.gms.maps.model.LatLng;
import com.pycca.pycca.pojo.OurShopsDetails;

public interface OurShopsDetailsActivityMVP {

    interface View {

        void clearMarkersGoogleMap();

        void addMarkerGoogleMap(OurShopsDetails ourShopsDetails);

    }

    interface Presenter {

        void setView(OurShopsDetailsActivityMVP.View view);

        void itemClicked(OurShopsDetails ourShopsDetails);

    }

    interface Model {


    }

}
