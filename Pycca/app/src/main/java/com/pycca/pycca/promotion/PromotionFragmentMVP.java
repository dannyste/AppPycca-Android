package com.pycca.pycca.promotion;

import android.content.Context;

import com.pycca.pycca.pojo.Promotion;

import java.util.ArrayList;

public interface PromotionFragmentMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<Promotion> promotions);

        Context getAppContext();

    }

    interface Presenter {

        void setView(View view);

        void loadPromotions(final android.view.View view1);

    }

    interface Model {

        ArrayList<Promotion> castPromotionList(Object objectList);

    }
}
