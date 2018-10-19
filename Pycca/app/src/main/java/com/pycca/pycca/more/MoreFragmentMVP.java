package com.pycca.pycca.more;

import com.pycca.pycca.pojo.More;

import java.util.ArrayList;

public interface MoreFragmentMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<More> moreArrayList);

    }

    interface Presenter {

        void setView(MoreFragmentMVP.View view);

        void loadMoreArrayList();

    }

    interface Model {

        ArrayList<More> getMoreArrayList();

    }

}
