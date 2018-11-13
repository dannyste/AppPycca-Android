package com.pycca.pycca.home;

import android.content.Context;

import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;

public interface HomeFragmentMVP {

    interface View {

        void setDataToBanner(ArrayList<ImageResource> imageResources);

        void updateDataRecyclerView(ArrayList<ImageResource> imageResources);

        Context getAppContext();

        void goToPictureActivity(ImageResource imageResource);

        void showMessage(int errorCode);

    }

    interface Presenter {

        void setView(View view);

        void loadImages();

        void clickOnBanner(ImageResource slideImg);

    }

    interface Model {

        void getHeaderImages(HomeFragmentMVP.TaskListener listener);

        void getContentImages(HomeFragmentMVP.TaskListener listener);

    }

    interface TaskListener {

        void onSuccess(ArrayList<ImageResource> list, boolean isHeader);
    }

}
