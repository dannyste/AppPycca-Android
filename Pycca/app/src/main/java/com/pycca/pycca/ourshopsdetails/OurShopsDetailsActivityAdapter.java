package com.pycca.pycca.ourshopsdetails;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShopsDetails;

import java.util.ArrayList;

public class OurShopsDetailsActivityAdapter extends RecyclerView.Adapter<OurShopsDetailsActivityAdapter.OurShopsDetailsViewHolder> {

    private Activity activity;
    private ArrayList<OurShopsDetails> ourShopsDetailsArrayList;
    private OnItemClickListener onItemClickListener;

    OurShopsDetailsActivityAdapter(Activity activity, ArrayList<OurShopsDetails> ourShopsDetailsArrayList, OnItemClickListener onItemClickListener) {
        this.activity = activity;
        this.ourShopsDetailsArrayList = ourShopsDetailsArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public OurShopsDetailsActivityAdapter.OurShopsDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_activity_our_shops_details, parent, false);
        return new OurShopsDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OurShopsDetailsActivityAdapter.OurShopsDetailsViewHolder ourShopsDetailsViewHolder, int position) {
        final OurShopsDetails ourShopsDetails = ourShopsDetailsArrayList.get(position);
        ourShopsDetailsViewHolder.tv_name.setText(ourShopsDetails.getName());
        ourShopsDetailsViewHolder.tv_address.setText(ourShopsDetails.getAddress());
        ourShopsDetailsViewHolder.tv_opening_hours.setText(ourShopsDetails.getOpeningHours());
    }

    @Override
    public int getItemCount() {
        return ourShopsDetailsArrayList.size();
    }

    public class OurShopsDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_name;
        private TextView tv_address;
        private TextView tv_opening_hours;
        private ImageView iv_how_get;

        OurShopsDetailsViewHolder(View view) {
            super(view);
            tv_name          =  view.findViewById(R.id.tv_name);
            tv_address       =  view.findViewById(R.id.tv_address);
            tv_opening_hours =  view.findViewById(R.id.tv_opening_hours);
            iv_how_get       =  view.findViewById(R.id.iv_how_get);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(OurShopsDetailsViewHolder.this, getOurShopsDetails(getAdapterPosition()));
        }

    }

    private OurShopsDetails getOurShopsDetails(int position) {
        if (ourShopsDetailsArrayList != null) {
            if (ourShopsDetailsArrayList.get(position) != null) {
                return ourShopsDetailsArrayList.get(position);
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
        void onClick(OurShopsDetailsViewHolder ourShopsDetailsViewHolder, OurShopsDetails ourShopsDetails);
    }

}
