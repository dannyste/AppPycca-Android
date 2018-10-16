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
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.Division;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.PromotionViewHolder> {

    private Activity activity;
    private ArrayList<Division> divisionArrayList;

    public HomeFragmentAdapter(Activity activity, ArrayList<Division> divisionArrayList) {
        this.activity = activity;
        this.divisionArrayList = divisionArrayList;
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fragment_home, parent, false);
        return new PromotionViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull final PromotionViewHolder promotionViewHolder, final int position) {
        final Division division = divisionArrayList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        promotionViewHolder.avliv_loading.setVisibility(View.GONE);
        promotionViewHolder.iv_image.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .load(division.getImageLink())
                .apply(requestOptions)
                .into(promotionViewHolder.iv_image);
        promotionViewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(promotionViewHolder.iv_image, "CLICK EN IMAGEN", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
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
