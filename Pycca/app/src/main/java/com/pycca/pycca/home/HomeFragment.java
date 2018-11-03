package com.pycca.pycca.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.DefaultSliderView;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.root.App;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeFragment extends Fragment implements HomeFragmentMVP.View {

    private static final String TAG = HomeFragment.class.getName();

    @Inject
    public HomeFragmentMVP.Presenter presenter;

    private RecyclerView rvDivisions;
    private SliderLayout sl_promotions;
    private ArrayList<ImageResource> divisions;
    private HomeFragmentAdapter homeFragmentAdapter;
    private View home_header;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        home_header = inflater.inflate(R.layout.home_header, container, false);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(HomeFragment.this);
        sl_promotions  = home_header.findViewById(R.id.sl_home_fragment);
        rvDivisions    = view.findViewById(R.id.rv_divisions);
        initRecyclerView();
        return view;
    }



    @Override
    public void setDataToBanner(ArrayList<ImageResource> imageResources){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        sl_promotions.removeAllSliders();
        for (int i = 0; i < imageResources.size(); i++) {
            addPageToSlider(imageResources.get(i).getPath(), requestOptions, i);
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
                .setProgressBarVisible(true);
        sl_promotions.addSlider(sliderView);
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
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
        presenter.setView(HomeFragment.this);
        presenter.loadImages();
    }

    @Override
    public void onStop() {
        super.onStop();
        divisions.clear();
        homeFragmentAdapter.notifyDataSetChanged();
    }

}
