package com.pycca.pycca.coupon;

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
import com.pycca.pycca.pojo.Coupon;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class CouponFragmentAdapter extends RecyclerView.Adapter<CouponFragmentAdapter.CouponViewHolder> {

    private Activity activity;
    private ArrayList<Coupon> couponArrayList;

    public CouponFragmentAdapter(Activity activity, ArrayList<Coupon> couponArrayList) {
        this.activity = activity;
        this.couponArrayList = couponArrayList;
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_fragment_coupon, viewGroup, false);
        return new CouponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder couponViewHolder, int position) {
        final Coupon coupon = couponArrayList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        couponViewHolder.avliv_loading.setVisibility(View.GONE);
        couponViewHolder.iv_image.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .load(coupon.getImageLink())
                .apply(requestOptions)
                .into(couponViewHolder.iv_image);
        couponViewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return couponArrayList.size();
    }

    static class CouponViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout ll_item;
        private ImageView iv_image;
        private AVLoadingIndicatorView avliv_loading;

        CouponViewHolder(View view) {
            super(view);
            ll_item       =  view.findViewById(R.id.ll_item);
            iv_image      =  view.findViewById(R.id.iv_image);
            avliv_loading = itemView.findViewById(R.id.avliv_loading);
        }

    }
}
