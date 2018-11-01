package com.pycca.pycca.promotion;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.Promotion;

import java.util.ArrayList;

public class PromotionFragmentAdapter extends RecyclerView.Adapter<PromotionFragmentAdapter.PromotionViewHolder> {

    private Activity activity;
    private ArrayList<Promotion> promotionArrayList;

    public PromotionFragmentAdapter(Activity activity, ArrayList<Promotion> promotionArrayList) {
        this.activity = activity;
        this.promotionArrayList = promotionArrayList;
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_fragment_promotion, viewGroup, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionViewHolder promotionViewHolder, int position) {
        final Promotion promotion = promotionArrayList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        promotionViewHolder.iv_image.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .load(promotion.getImageLink())
                .apply(requestOptions)
                .into(promotionViewHolder.iv_image);
        promotionViewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return promotionArrayList.size();
    }

    static class PromotionViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout ll_item;
        private ImageView iv_image;

        PromotionViewHolder(View view) {
            super(view);
            ll_item       =  view.findViewById(R.id.ll_item);
            iv_image      =  view.findViewById(R.id.iv_image);
        }

    }
}
