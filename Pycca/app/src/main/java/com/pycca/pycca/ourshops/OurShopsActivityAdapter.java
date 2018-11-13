package com.pycca.pycca.ourshops;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShops;

import java.util.ArrayList;

public class OurShopsActivityAdapter extends RecyclerView.Adapter<OurShopsActivityAdapter.OurShopsViewHolder> {

    private Activity activity;
    private ArrayList<OurShops> ourShopsArrayList;
    private OnItemClickListener onItemClickListener;

    OurShopsActivityAdapter(Activity activity, ArrayList<OurShops> ourShopsArrayList, OnItemClickListener onItemClickListener) {
        this.activity = activity;
        this.ourShopsArrayList = ourShopsArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public OurShopsActivityAdapter.OurShopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_activity_our_shops, parent, false);
        return new OurShopsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OurShopsActivityAdapter.OurShopsViewHolder ourShopsViewHolder, int position) {
        final OurShops ourShops = ourShopsArrayList.get(position);
        ourShopsViewHolder.tv_name.setText(ourShops.getName());
    }

    @Override
    public int getItemCount() {
        return ourShopsArrayList.size();
    }

    public class OurShopsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_name;

        OurShopsViewHolder(View view) {
            super(view);
            tv_name   =  view.findViewById(R.id.tv_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(OurShopsViewHolder.this, getOurShops(getAdapterPosition()));
        }

    }

    private OurShops getOurShops(int position) {
        if (ourShopsArrayList != null) {
            if (ourShopsArrayList.get(position) != null) {
                return ourShopsArrayList.get(position);
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
        void onClick(OurShopsViewHolder ourShopsViewHolder, OurShops ourShops);
    }

}
