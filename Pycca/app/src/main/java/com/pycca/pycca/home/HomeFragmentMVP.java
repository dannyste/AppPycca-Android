package com.pycca.pycca.home;

import android.content.Context;

import com.pycca.pycca.pojo.Division;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.pojo.Promotion;

import java.util.ArrayList;

public interface HomeFragmentMVP {

    interface View {

        void setDataToBanner(ArrayList<ImageResource> imageResources);

        void updateDataRecyclerView(ArrayList<ImageResource> imageResources);

        Context getAppContext();

    }

    interface Presenter {

        void setView(View view);

        void loadImages();

    }

    interface Model {

        void getHeaderImages(HomeFragmentMVP.TaskListener listener);

        void getContentImages(HomeFragmentMVP.TaskListener listener);

    }

    interface TaskListener {

        void onSuccess(ArrayList<ImageResource> list, boolean isHeader);
    }

}
