package com.pycca.pycca.picture;

import android.support.annotation.Nullable;

import com.pycca.pycca.R;
import com.pycca.pycca.home.HomeFragmentMVP;
import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;

public class PictureActivityPresenter implements PictureActivityMVP.Presenter {

    @Nullable
    private PictureActivityMVP.View view;
    //private HomeFragmentMVP.Model model;

    @Override
    public void setView(PictureActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void receiveData(PictureActivity activity) {
        ImageResource imageResource = (ImageResource) activity.getIntent().getSerializableExtra("object");
        if(imageResource != null){
            view.setData(imageResource);
        }else{
            view.showMessage(R.string.error_default);
        }
    }
}
