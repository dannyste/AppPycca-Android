package com.pycca.pycca.buy;

import android.content.Context;

import com.pycca.pycca.pojo.DivisionImageResource;
import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;

public interface BuyFragmentMVP {

    interface View {

        void setDataToBanner(ArrayList<ImageResource> imageResources);

        void updateDataRecyclerView(ArrayList<DivisionImageResource> imageResources);

        Context getAppContext();

        void showMessage(int errorCode);

        void goToLink(String url);

    }

    interface Presenter {

        void setView(View view);

        void loadImages();

        void clickOnBanner(ImageResource slideImg);
    }

    interface Model {

        void getHeaderImages(BuyFragmentMVP.TaskListener listener);

        void getContentImages(BuyFragmentMVP.TaskListener listener);

    }

    interface TaskListener {

        void onSuccessHeader(ArrayList<ImageResource> list);

        void onSuccessContent(ArrayList<DivisionImageResource> list);
    }

}
