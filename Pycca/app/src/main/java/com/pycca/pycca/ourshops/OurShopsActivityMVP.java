package com.pycca.pycca.ourshops;

import com.pycca.pycca.pojo.OurShops;
import com.pycca.pycca.pojo.OurShopsDetails;

import java.util.ArrayList;

public interface OurShopsActivityMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<OurShops> ourShopsArrayList);

        void goToOurShopsDetailsActivity(ArrayList<OurShopsDetails> ourShopsDetails);

    }

    interface Presenter {

        void setView(OurShopsActivityMVP.View view);

        void loadOurShopsArrayList();

        void itemClicked(ArrayList<OurShopsDetails> ourShopsDetails);

    }

    interface Model {

    }

}
