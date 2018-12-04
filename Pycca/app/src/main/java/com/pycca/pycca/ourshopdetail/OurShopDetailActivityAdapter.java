package com.pycca.pycca.ourshopdetail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShopDetail;

import java.util.ArrayList;

public class OurShopDetailActivityAdapter extends RecyclerView.Adapter<OurShopDetailActivityAdapter.OurShopDetailViewHolder> {

    private Activity activity;
    private ArrayList<OurShopDetail> ourShopDetailArrayList;
    private OnItemClickListener onItemClickListener;

    OurShopDetailActivityAdapter(Activity activity, ArrayList<OurShopDetail> ourShopDetailArrayList, OnItemClickListener onItemClickListener) {
        this.activity = activity;
        this.ourShopDetailArrayList = ourShopDetailArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public OurShopDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_activity_our_shop_detail, parent, false);
        return new OurShopDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OurShopDetailViewHolder ourShopDetailViewHolder, int position) {
        final OurShopDetail ourShopDetail = ourShopDetailArrayList.get(position);
        ourShopDetailViewHolder.tv_name.setText(ourShopDetail.getName());
        ourShopDetailViewHolder.tv_address.setText(ourShopDetail.getAddress());
        ourShopDetailViewHolder.tv_opening_hours.setText(ourShopDetail.getOpeningHours());
        ourShopDetailViewHolder.iv_how_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("google.navigation:q=" + ourShopDetail.getLatitude() + "," + ourShopDetail.getLongitude() + "&mode=d");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ourShopDetailArrayList.size();
    }

    public class OurShopDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_name;
        private TextView tv_address;
        private TextView tv_opening_hours;
        private ImageView iv_how_get;

        OurShopDetailViewHolder(View view) {
            super(view);
            tv_name          =  view.findViewById(R.id.tv_name);
            tv_address       =  view.findViewById(R.id.tv_address);
            tv_opening_hours =  view.findViewById(R.id.tv_opening_hours);
            iv_how_get       =  view.findViewById(R.id.iv_how_get);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(OurShopDetailViewHolder.this, getOurShopDetail(getAdapterPosition()));
        }

    }

    private OurShopDetail getOurShopDetail(int position) {
        if (ourShopDetailArrayList != null) {
            if (ourShopDetailArrayList.get(position) != null) {
                return ourShopDetailArrayList.get(position);
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public interface OnItemClickListener {
        void onClick(OurShopDetailViewHolder ourShopDetailViewHolder, OurShopDetail ourShopDetail);
    }

}
