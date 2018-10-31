package com.pycca.pycca.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.Division;
import com.pycca.pycca.pojo.ImageResource;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter extends ParallaxRecyclerAdapter<ImageResource> {

    private Activity activity;
    private ArrayList<ImageResource> divisionArrayList;

    public HomeFragmentAdapter(Activity activity, ArrayList<ImageResource> divisionArrayList) {
        super(divisionArrayList);
        this.activity = activity;
        this.divisionArrayList = divisionArrayList;
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<ImageResource> parallaxRecyclerAdapter, int position) {
        final PromotionViewHolder promotionViewHolder = (PromotionViewHolder)viewHolder;
        final ImageResource division = divisionArrayList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        promotionViewHolder.avliv_loading.setVisibility(View.GONE);
        promotionViewHolder.iv_image.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .load(division.getPath())
                .apply(requestOptions)
                .into(promotionViewHolder.iv_image);
        promotionViewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<ImageResource> parallaxRecyclerAdapter, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_fragment_home, viewGroup, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<ImageResource> parallaxRecyclerAdapter) {
        return divisionArrayList.size();
    }

    static class PromotionViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_image;
        private AVLoadingIndicatorView avliv_loading;

        PromotionViewHolder(View view) {
            super(view);
            iv_image      = itemView.findViewById(R.id.iv_image);
            avliv_loading = itemView.findViewById(R.id.avliv_loading);
        }
    }
}
