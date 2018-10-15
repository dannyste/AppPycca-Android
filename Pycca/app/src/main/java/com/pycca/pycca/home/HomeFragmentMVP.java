package com.pycca.pycca.home;

import android.content.Context;

import com.pycca.pycca.pojo.Division;
import com.pycca.pycca.pojo.Promotion;

import java.util.ArrayList;

public interface HomeFragmentMVP {

    interface View {

        void setDataToBanner(ArrayList<Promotion> promotions);

        void updateDataRecyclerView(ArrayList<Division> divisions);

        Context getAppContext();

    }

    interface Presenter {

        void setView(View view);

        void loadPromotions(final android.view.View view1);

        void loadDivisions(final android.view.View view1);

    }

    interface Model {

        ArrayList<Promotion> castPromotionList(Object objectList);

        ArrayList<Division> castDivisionList(Object objectList);

    }
}
