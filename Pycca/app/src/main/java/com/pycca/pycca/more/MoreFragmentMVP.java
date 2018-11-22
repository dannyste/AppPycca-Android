package com.pycca.pycca.more;

import com.pycca.pycca.pojo.More;
import com.pycca.pycca.pojo.Parameter;

import java.util.ArrayList;

public interface MoreFragmentMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<More> moreArrayList);

        void goToClubPyccaPartner();

        void goToProfileActivity();

        void goToContactCall();

        void goToContactEmail();

        void goToNearestShopActivity();

        void goToOurShopsActivity();

    }

    interface Presenter {

        void setView(MoreFragmentMVP.View view);

        void loadMoreArrayList(MoreFragment moreFragment);

        void firstItemClicked();

        void secondItemClicked();

        void thirdItemClicked();

        void fourthItemClicked();

        void fifthItemClicked();

        void sixthItemClicked();

        String getPhoneNumber(MoreFragment moreFragment);

        String getEmail(MoreFragment moreFragment);

    }

    interface Model {

        ArrayList<More> getMoreArrayList(MoreFragment moreFragment);

        Parameter getParameter(MoreFragment moreFragment);

    }

}
