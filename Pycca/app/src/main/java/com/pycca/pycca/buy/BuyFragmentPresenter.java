package com.pycca.pycca.buy;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.pycca.pycca.R;
import com.pycca.pycca.home.HomeFragmentMVP;
import com.pycca.pycca.pojo.DivisionImageResource;
import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;

public class BuyFragmentPresenter implements BuyFragmentMVP.Presenter, BuyFragmentMVP.TaskListener {

    @Nullable
    private BuyFragmentMVP.View view;
    private BuyFragmentMVP.Model model;

    public BuyFragmentPresenter(BuyFragmentMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(BuyFragmentMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadImages() {
        model.getHeaderImages(this);
        model.getContentImages(this);
    }

    @Override
    public void clickOnBanner(ImageResource slideImg) {
        if(slideImg.getUrl().trim() != null && !slideImg.getUrl().trim().isEmpty()){
            view.goToLink(slideImg.getUrl().trim());
        }else {
            view.showMessage(R.string.link_not_found);
        }
    }

    @Override
    public void onSuccessHeader(ArrayList<ImageResource> list) {
        view.setDataToBanner(list);
    }

    @Override
    public void onSuccessContent(ArrayList<DivisionImageResource> list) {
        view.updateDataRecyclerView(list);
    }
}
