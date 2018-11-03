package com.pycca.pycca.coupon;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import com.pycca.pycca.pojo.CouponImageResource;
import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;

public class CouponFragmentAdapter extends RecyclerView.Adapter<CouponFragmentAdapter.CouponViewHolder> {

    private CouponFragment fragment;
    private ArrayList<CouponImageResource> couponArrayList;

    public CouponFragmentAdapter(CouponFragment fragment, ArrayList<CouponImageResource> couponArrayList) {
        this.fragment = fragment;
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
        final CouponImageResource coupon = couponArrayList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        couponViewHolder.iv_image.setVisibility(View.VISIBLE);
        Glide.with(fragment)
                .load(coupon.getPath())
                .apply(requestOptions)
                .into(couponViewHolder.iv_image);
        couponViewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(coupon.getUrl().trim() != null && !coupon.getUrl().trim().isEmpty()){
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(coupon.getUrl().trim()));
                    fragment.startActivity(i);
                }else {
                    fragment.showMessage(R.string.link_not_found);
                }
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

        CouponViewHolder(View view) {
            super(view);
            ll_item       =  view.findViewById(R.id.ll_item);
            iv_image      =  view.findViewById(R.id.iv_image);
        }

    }
}
