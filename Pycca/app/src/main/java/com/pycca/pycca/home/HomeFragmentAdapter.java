package com.pycca.pycca.home;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
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
import com.pycca.pycca.picture.PictureActivity;
import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;

public class HomeFragmentAdapter extends ParallaxRecyclerAdapter<ImageResource> {

    private Activity activity;
    private ArrayList<ImageResource> divisionArrayList;

    HomeFragmentAdapter(Activity activity, ArrayList<ImageResource> divisionArrayList) {
        super(divisionArrayList);
        this.activity = activity;
        this.divisionArrayList = divisionArrayList;
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<ImageResource> parallaxRecyclerAdapter, int position) {
        final PromotionViewHolder promotionViewHolder = (PromotionViewHolder)viewHolder;
        final ImageResource imageResource = divisionArrayList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.ic_broken_image);
        promotionViewHolder.iv_image.setVisibility(View.VISIBLE);
        Glide.with(activity)
                .load(imageResource.getPath())
                .apply(requestOptions)
                .into(promotionViewHolder.iv_image);
        promotionViewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PictureActivity.class);
                intent.putExtra("object", imageResource);
                ActivityOptions activityOptions =
                        ActivityOptions.makeSceneTransitionAnimation(
                                activity, promotionViewHolder.iv_image, "img");
                view.getContext().startActivity(intent, activityOptions.toBundle());
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

        PromotionViewHolder(View view) {
            super(view);
            iv_image      = itemView.findViewById(R.id.iv_image);
        }
    }
}
