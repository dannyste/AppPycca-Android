package com.pycca.pycca.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
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
import com.pycca.pycca.pojo.Division;
import com.pycca.pycca.pojo.Promotion;
import com.pycca.pycca.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends Fragment implements HomeFragmentMVP.View {
    @Inject
    HomeFragmentMVP.Presenter presenter;

    private RecyclerView rvDivisions;
    private SliderLayout sl_promotions;
    private ArrayList<Division> divisions;
    private HomeFragmentAdapter homeFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v          = inflater.inflate(R.layout.fragment_home, container, false);
        sl_promotions   = v.findViewById(R.id.sl_home_fragment);
        rvDivisions     = v.findViewById(R.id.rv_divisions);

        ((App) getActivity().getApplication()).getApplicationComponent().inject(this);

        initRecyclerView();
        return v;
    }


    @Override
    public void setDataToBanner(ArrayList<Promotion> promotions) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        sl_promotions.removeAllSliders();
        for (int i = 0; i < promotions.size(); i++) {
            addPageToSlider(promotions.get(i).getImageLink(), requestOptions, i);
        }
        sl_promotions.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sl_promotions.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sl_promotions.setCustomAnimation(new DescriptionAnimation());
        sl_promotions.setDuration(4000);
    }

    @Override
    public void updateDataRecyclerView(ArrayList<Division> divisions) {
        this.divisions.clear();
        this.divisions.addAll(divisions);
        homeFragmentAdapter.notifyDataSetChanged();
    }

    private void addPageToSlider(String imageURL, RequestOptions requestOptions, int i){
        DefaultSliderView sliderView = new DefaultSliderView(getAppContext());
        sliderView
                .image(imageURL)
                .setRequestOption(requestOptions)
                .setProgressBarVisible(true);
                //.setOnSliderClickListener(MainActivity.this);
        //sliderView.bundle(new Bundle());
        //sliderView.getBundle().putInt("position", i);
        sl_promotions.addSlider(sliderView);

    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadPromotions(sl_promotions);
        presenter.loadDivisions(rvDivisions);
    }

    @Override
    public void onStop() {
        super.onStop();
        divisions.clear();
        homeFragmentAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        divisions = new ArrayList<>();
        homeFragmentAdapter = new HomeFragmentAdapter(divisions, getActivity());

        rvDivisions.setAdapter(homeFragmentAdapter);
        rvDivisions.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager llm = new LinearLayoutManager(getAppContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvDivisions.setLayoutManager(llm);
    }
}
