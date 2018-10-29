package com.pycca.pycca.promotion;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glide.slider.library.SliderLayout;
import com.pycca.pycca.R;
import com.pycca.pycca.home.HomeFragmentAdapter;
import com.pycca.pycca.home.HomeFragmentMVP;
import com.pycca.pycca.pojo.Division;
import com.pycca.pycca.pojo.Promotion;
import com.pycca.pycca.root.App;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.xml.transform.sax.TemplatesHandler;

public class PromotionFragment extends Fragment implements PromotionFragmentMVP.View{

    @Inject
    public PromotionFragmentMVP.Presenter presenter;

    private RecyclerView rvPromotions;
    private ArrayList<Promotion> promotions;
    private PromotionFragmentAdapter promotionFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotion, container, false);
        //(App) getActivity().getApplication()).getApplicationComponent().inject(PromotionFragment.this);
        rvPromotions    = view.findViewById(R.id.rv_promotion);
        initRecyclerView();
        return view;
    }

    @Override
    public void updateDataRecyclerView(ArrayList<Promotion> promotions) {
        this.promotions.clear();
        this.promotions.addAll(promotions);
        promotionFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getAppContext() {
        return getContext().getApplicationContext();
    }

    private void initRecyclerView(){
        promotions = new ArrayList<>();
        promotionFragmentAdapter = new PromotionFragmentAdapter(getActivity(), promotions);

        rvPromotions.setAdapter(promotionFragmentAdapter);
        rvPromotions.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager llm = new LinearLayoutManager(getAppContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPromotions.setLayoutManager(llm);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(PromotionFragment.this);
        presenter.loadPromotions(rvPromotions);
    }

    @Override
    public void onStop() {
        super.onStop();
        promotions.clear();
        promotionFragmentAdapter.notifyDataSetChanged();
    }
}
