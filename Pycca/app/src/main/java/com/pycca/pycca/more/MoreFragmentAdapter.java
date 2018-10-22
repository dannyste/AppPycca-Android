package com.pycca.pycca.more;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.More;

import java.util.ArrayList;

public class MoreFragmentAdapter extends RecyclerView.Adapter<MoreFragmentAdapter.MoreViewHolder> {

    private Activity activity;
    private ArrayList<More> MoreArrayList;
    private OnItemClickListener onItemClickListener;

    MoreFragmentAdapter(Activity activity, ArrayList<More> MoreArrayList, OnItemClickListener onItemClickListener) {
        this.activity = activity;
        this.MoreArrayList = MoreArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MoreFragmentAdapter.MoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fragment_more, parent, false);
        return new MoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreFragmentAdapter.MoreViewHolder MoreViewHolder, int position) {
        final More More = MoreArrayList.get(position);
        MoreViewHolder.ll_item.setBackgroundResource(More.getColor());
        MoreViewHolder.iv_image.setImageResource(More.getImage());
        MoreViewHolder.tv_name.setText(More.getName());
    }

    @Override
    public int getItemCount() {
        return MoreArrayList.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout ll_item;
        private ImageView iv_image;
        private TextView tv_name;

        MoreViewHolder(View view) {
            super(view);
            ll_item   =  view.findViewById(R.id.ll_item);
            iv_image  =  view.findViewById(R.id.iv_image);
            tv_name   =  view.findViewById(R.id.tv_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(MoreViewHolder.this, getAdapterPosition(), iv_image);
        }

    }

    public interface OnItemClickListener {
        void onClick(MoreViewHolder moreViewHolder, int position, ImageView imageView);
    }
}
