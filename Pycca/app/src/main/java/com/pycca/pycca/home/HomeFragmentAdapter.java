package com.pycca.pycca.home;

import android.app.Activity;
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
import com.pycca.pycca.pojo.Promotion;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.PromotionViewHolder> {
    private ArrayList<Division> divisionArrayList;
    private Activity activity;

    public HomeFragmentAdapter(ArrayList<Division> divisionArrayList, Activity activity) {
        this.divisionArrayList = divisionArrayList;
        this.activity = activity;
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fragment_home, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PromotionViewHolder holder, final int position) {
        final Division division = divisionArrayList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        holder.avloading.setVisibility(View.GONE);
        holder.ivImage.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .load(division.getImageLink())
                .apply(requestOptions)
                .into(holder.ivImage);

        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(holder.ivImage, "CLICK EN IMAGEN", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return divisionArrayList.size();
    }

    static class PromotionViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        AVLoadingIndicatorView avloading;

        PromotionViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            avloading = itemView.findViewById(R.id.avliv_loading);
        }
    }
}
