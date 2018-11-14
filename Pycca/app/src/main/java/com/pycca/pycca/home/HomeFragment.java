package com.pycca.pycca.home;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.DefaultSliderView;
import com.pycca.pycca.R;
import com.pycca.pycca.picture.PictureActivity;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeFragment extends Fragment implements HomeFragmentMVP.View, BaseSliderView.OnSliderClickListener  {

    private static final String TAG = HomeFragment.class.getName();

    @Inject
    public HomeFragmentMVP.Presenter presenter;

    private RecyclerView rvDivisions;
    private SliderLayout sl_promotions;
    private ArrayList<ImageResource> divisions;
    private ArrayList<ImageResource> headerList;
    private HomeFragmentAdapter homeFragmentAdapter;
    private View home_header;
    private LinearLayout ll_root_view;
    private BaseSliderView baseSliderView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        home_header = inflater.inflate(R.layout.home_header, container, false);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(HomeFragment.this);
        sl_promotions   = home_header.findViewById(R.id.sl_home_fragment);
        rvDivisions     = view.findViewById(R.id.rv_divisions);
        ll_root_view    = view.findViewById(R.id.ll_root_view);
        headerList = new ArrayList<>();
        initRecyclerView();
        return view;
    }



    @Override
    public void setDataToBanner(ArrayList<ImageResource> imageResources){
        this.headerList.clear();
        this.headerList.addAll(imageResources);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        sl_promotions.removeAllSliders();
        for (int i = 0; i < headerList.size(); i++) {
            addPageToSlider(headerList.get(i).getPath(), requestOptions, i);
        }
        sl_promotions.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sl_promotions.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sl_promotions.setCustomAnimation(new DescriptionAnimation());
        sl_promotions.setDuration(4000);
    }

    @Override
    public void updateDataRecyclerView(ArrayList<ImageResource> imageResources) {
        this.divisions.clear();
        this.divisions.addAll(imageResources);
        homeFragmentAdapter.notifyDataSetChanged();
    }

    private void addPageToSlider(String imageURL, RequestOptions requestOptions, int i){
        DefaultSliderView sliderView = new DefaultSliderView(getAppContext());
        sliderView
                .image(imageURL)
                .setRequestOption(requestOptions)
                .setProgressBarVisible(true)
                .setOnSliderClickListener(this);
        sliderView.bundle(new Bundle());
        sliderView.getBundle().putInt("position", i);
        sl_promotions.addSlider(sliderView);
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void goToPictureActivity(ImageResource imageResource) {
        sl_promotions.startAutoCycle();
        Intent intent = new Intent(getActivity(), PictureActivity.class);
        intent.putExtra("object", imageResource);
        ActivityOptions activityOptions =
                ActivityOptions.makeSceneTransitionAnimation(getActivity(), baseSliderView.getView(), "img");
        startActivity(intent, activityOptions.toBundle());

    }

    @Override
    public void showMessage(int errorCode) {
        Util.showMessage(ll_root_view, getResources().getString(errorCode));
    }

    private void initRecyclerView(){
        divisions = new ArrayList<>();
        homeFragmentAdapter = new HomeFragmentAdapter(getActivity(), divisions);

        rvDivisions.setAdapter(homeFragmentAdapter);
        rvDivisions.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager llm = new LinearLayoutManager(getAppContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvDivisions.setLayoutManager(llm);

        homeFragmentAdapter.setParallaxHeader(home_header, rvDivisions);
    }

    @Override
    public void onResume() {
        super.onResume();
        sl_promotions.startAutoCycle();
        presenter.setView(HomeFragment.this);
        presenter.loadImages();
    }

    @Override
    public void onStop() {
        super.onStop();
        divisions.clear();
        homeFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        int i = baseSliderView.getBundle().getInt("position");
        this.baseSliderView = baseSliderView;
        presenter.clickOnBanner(headerList.get(i));
    }
}
