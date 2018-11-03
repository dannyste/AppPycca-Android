package com.pycca.pycca.more;

import com.pycca.pycca.pojo.More;

import java.util.ArrayList;

public interface MoreFragmentMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<More> moreArrayList);

        void goToProfileActivity();

    }

    interface Presenter {

        void setView(MoreFragmentMVP.View view);

        void loadMoreArrayList();

        void firstItemClicked();

        void secondItemClicked();

        void thirdItemClicked();

        void fourthItemClicked();

        void fifthItemClicked();

        void sixthItemClicked();

    }

    interface Model {

        ArrayList<More> getMoreArrayList();

    }

}
