package com.pycca.pycca.home;

import android.support.annotation.Nullable;
import android.view.View;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentPresenter implements HomeFragmentMVP.Presenter, HomeFragmentMVP.TaskListener {

    @Nullable
    private HomeFragmentMVP.View view;
    private HomeFragmentMVP.Model model;

    public HomeFragmentPresenter (HomeFragmentMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(HomeFragmentMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadImages() {
        model.getHeaderImages(this);
        model.getContentImages(this);
    }

    @Override
    public void onSuccess(ArrayList<ImageResource> list, boolean isHeader) {
        if(isHeader){
            view.setDataToBanner(list);
        }else {
            view.updateDataRecyclerView(list);
        }

    }
}
