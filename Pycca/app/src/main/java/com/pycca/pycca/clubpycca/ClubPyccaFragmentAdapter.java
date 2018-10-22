package com.pycca.pycca.clubpycca;

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
import com.pycca.pycca.pojo.ClubPycca;

import java.util.ArrayList;

public class ClubPyccaFragmentAdapter extends RecyclerView.Adapter<ClubPyccaFragmentAdapter.ClubPyccaViewHolder> {

    private Activity activity;
    private ArrayList<ClubPycca> clubPyccaArrayList;
    private OnItemClickListener onItemClickListener;

    ClubPyccaFragmentAdapter(Activity activity, ArrayList<ClubPycca> clubPyccaArrayList, OnItemClickListener onItemClickListener) {
        this.activity = activity;
        this.clubPyccaArrayList = clubPyccaArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ClubPyccaFragmentAdapter.ClubPyccaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fragment_club_pycca, parent, false);
        return new ClubPyccaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubPyccaFragmentAdapter.ClubPyccaViewHolder clubPyccaViewHolder, int position) {
        final ClubPycca clubPycca = clubPyccaArrayList.get(position);
        clubPyccaViewHolder.ll_item.setBackgroundResource(clubPycca.getColor());
        clubPyccaViewHolder.iv_image.setImageResource(clubPycca.getImage());
        clubPyccaViewHolder.tv_name.setText(clubPycca.getName());
    }

    @Override
    public int getItemCount() {
        return clubPyccaArrayList.size();
    }

    public class ClubPyccaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout ll_item;
        private ImageView iv_image;
        private TextView tv_name;

        ClubPyccaViewHolder(View view) {
            super(view);
            ll_item   =  view.findViewById(R.id.ll_item);
            iv_image  =  view.findViewById(R.id.iv_image);
            tv_name   =  view.findViewById(R.id.tv_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(ClubPyccaViewHolder.this, getAdapterPosition(), iv_image);
        }

    }

    public interface OnItemClickListener {
        void onClick(ClubPyccaViewHolder clubPyccaViewHolder, int position, ImageView imageView);
    }

}
