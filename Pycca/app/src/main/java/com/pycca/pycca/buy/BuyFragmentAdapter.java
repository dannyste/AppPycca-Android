package com.pycca.pycca.buy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import com.pycca.pycca.pojo.DivisionImageResource;
import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;

public class BuyFragmentAdapter extends ParallaxRecyclerAdapter<DivisionImageResource> {

    private BuyFragment fragment;
    private ArrayList<DivisionImageResource> divisionArrayList;

    BuyFragmentAdapter(BuyFragment fragment, ArrayList<DivisionImageResource> divisionArrayList) {
        super(divisionArrayList);
        this.fragment = fragment;
        this.divisionArrayList = divisionArrayList;
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<DivisionImageResource> parallaxRecyclerAdapter, int position) {
        final PromotionViewHolder promotionViewHolder = (PromotionViewHolder)viewHolder;
        final DivisionImageResource division = divisionArrayList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        promotionViewHolder.iv_image.setVisibility(View.VISIBLE);
        Glide.with(fragment)
                .load(division.getPath())
                .apply(requestOptions)
                .into(promotionViewHolder.iv_image);
        promotionViewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(division.getUrl().trim() != null && !division.getUrl().trim().isEmpty()){
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(division.getUrl().trim()));
                    fragment.startActivity(i);
                }else {
                    fragment.showMessage(R.string.link_not_found);
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<DivisionImageResource> parallaxRecyclerAdapter, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_fragment_buy, viewGroup, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<DivisionImageResource> parallaxRecyclerAdapter) {
        return divisionArrayList.size();
    }

    static class PromotionViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_image;

        PromotionViewHolder(View view) {
            super(view);
            iv_image      = itemView.findViewById(R.id.iv_image);
        }
    }
}
