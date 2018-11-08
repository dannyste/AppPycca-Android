package com.pycca.pycca.ourshops;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShop;

import java.util.ArrayList;

public class OurShopsActivityAdapter extends RecyclerView.Adapter<OurShopsActivityAdapter.OurShopViewHolder> {

    private Activity activity;
    private ArrayList<OurShop> ourShopArrayList;
    private OnItemClickListener onItemClickListener;

    OurShopsActivityAdapter(Activity activity, ArrayList<OurShop> ourShopArrayList, OnItemClickListener onItemClickListener) {
        this.activity = activity;
        this.ourShopArrayList = ourShopArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public OurShopsActivityAdapter.OurShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_activity_our_shops, parent, false);
        return new OurShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OurShopsActivityAdapter.OurShopViewHolder ourShopViewHolder, int position) {
        final OurShop ourShop = ourShopArrayList.get(position);
        ourShopViewHolder.tv_name.setText(ourShop.getName());
    }

    @Override
    public int getItemCount() {
        return ourShopArrayList.size();
    }

    public class OurShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_name;

        OurShopViewHolder(View view) {
            super(view);
            tv_name   =  view.findViewById(R.id.tv_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(OurShopViewHolder.this, getAdapterPosition());
        }

    }

    public interface OnItemClickListener {
        void onClick(OurShopViewHolder ourShopViewHolder, int position);
    }

}
