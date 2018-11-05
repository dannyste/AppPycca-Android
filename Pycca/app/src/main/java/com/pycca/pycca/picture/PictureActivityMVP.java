package com.pycca.pycca.picture;

import android.content.Context;

import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;

public interface PictureActivityMVP {

    interface View {

        void setData(ImageResource imageResource);

        Context getAppContext();

        void showMessage(int errorCode);

    }

    interface Presenter {

        void setView(View view);

        void receiveData(PictureActivity activity);

    }

    interface Model {

    }

    /*interface TaskListener {

        void onSuccess(ArrayList<ImageResource> list, boolean isHeader);
    }*/

}
