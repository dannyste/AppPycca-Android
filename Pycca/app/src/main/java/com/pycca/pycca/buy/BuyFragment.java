package com.pycca.pycca.buy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.glide.slider.library.Tricks.ViewPagerEx;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.DivisionImageResource;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import javax.inject.Inject;

public class BuyFragment extends Fragment implements BuyFragmentMVP.View, BaseSliderView.OnSliderClickListener {

    @Inject
    public BuyFragmentMVP.Presenter presenter;

    private RecyclerView rvDivisions;
    private SliderLayout sl_header;
    private ArrayList<DivisionImageResource> divisions;
    private ArrayList<ImageResource> listHeader;
    private BuyFragmentAdapter buyFragmentAdapter;
    private View buyHeader;
    private LinearLayout ll_root_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        buyHeader = inflater.inflate(R.layout.buy_header, container, false);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(BuyFragment.this);
        sl_header  = buyHeader.findViewById(R.id.sl_buy_fragment);
        rvDivisions    = view.findViewById(R.id.rv_buy);
        ll_root_view = view.findViewById(R.id.ll_root_view);
        listHeader = new ArrayList<>();
        initRecyclerView();
        return view;
    }

    @Override
    public void setDataToBanner(ArrayList<ImageResource> imageResources) {
        this.listHeader.clear();
        this.listHeader.addAll(imageResources);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        sl_header.removeAllSliders();
        for (int i = 0; i < listHeader.size(); i++) {
            addPageToSlider(listHeader.get(i).getPath(), requestOptions, i);
        }
        sl_header.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sl_header.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sl_header.setCustomAnimation(new DescriptionAnimation());
        sl_header.setDuration(4000);
    }

    @Override
    public void updateDataRecyclerView(ArrayList<DivisionImageResource> imageResources) {
        this.divisions.clear();
        this.divisions.addAll(imageResources);
        buyFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void showMessage(int errorCode) {
        Util.showMessage(ll_root_view, getResources().getString(errorCode));
    }

    @Override
    public void goToLink(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(BuyFragment.this);
        presenter.loadImages();
    }

    @Override
    public void onStop() {
        super.onStop();
        divisions.clear();
        buyFragmentAdapter.notifyDataSetChanged();
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
        sl_header.addSlider(sliderView);
    }

    private void initRecyclerView(){
        divisions = new ArrayList<>();
        buyFragmentAdapter = new BuyFragmentAdapter(this, divisions);

        rvDivisions.setAdapter(buyFragmentAdapter);
        rvDivisions.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager llm = new LinearLayoutManager(getAppContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvDivisions.setLayoutManager(llm);

        buyFragmentAdapter.setParallaxHeader(buyHeader, rvDivisions);
    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        int i = baseSliderView.getBundle().getInt("position");
        presenter.clickOnBanner(listHeader.get(i));
    }
}
