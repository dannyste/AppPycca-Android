package com.pycca.pycca.more;

import com.pycca.pycca.pojo.More;
import com.pycca.pycca.pojo.Parameter;

import java.util.ArrayList;

public interface MoreFragmentMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<More> moreArrayList);

        void showAlertDialogClubPyccaPartner();

        void goToProfileActivity();

        void showAlertDialogContactUs();

        void goToContactCall();

        void goToContactEmail();

        void goToOurShopsActivity();

    }

    interface Presenter {

        void setView(MoreFragmentMVP.View view);

        void loadMoreArrayList(MoreFragment moreFragment);

        void moreFirstItemClicked();

        void moreSecondItemClicked();

        void moreThirdItemClicked();

        void moreFourthItemClicked();

        void contactUsFirstItemClicked();

        void contactUsSecondItemClicked();

        String getPhoneNumber(MoreFragment moreFragment);

        String getEmail(MoreFragment moreFragment);

    }

    interface Model {

        ArrayList<More> getMoreArrayList(MoreFragment moreFragment);

        Parameter getParameter(MoreFragment moreFragment);

    }

}
