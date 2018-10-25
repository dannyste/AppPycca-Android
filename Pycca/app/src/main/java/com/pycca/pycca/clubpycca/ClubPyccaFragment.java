package com.pycca.pycca.clubpycca;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.ClubPycca;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.virtualcard.VirtualCardActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class ClubPyccaFragment extends Fragment implements ClubPyccaFragmentMVP.View {

    private static final String TAG = ClubPyccaFragment.class.getName();

    @Inject
    public ClubPyccaFragmentMVP.Presenter presenter;

    private RecyclerView rv_club_pycca;

    private ArrayList<ClubPycca> clubPyccaArrayList ;
    private ClubPyccaFragmentAdapter clubPyccaFragmentAdapter;

    public ClubPyccaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_pycca, container, false);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(ClubPyccaFragment.this);
        rv_club_pycca = view.findViewById(R.id.rv_club_pycca);
        initRecyclerView();
        return view;
    }

    @Override
    public void updateDataRecyclerView(ArrayList<ClubPycca> clubPyccaArrayList) {
        this.clubPyccaArrayList.clear();
        this.clubPyccaArrayList.addAll(clubPyccaArrayList);
        clubPyccaFragmentAdapter.notifyDataSetChanged();
    }

    public void initRecyclerView() {
        clubPyccaArrayList = new ArrayList<>();
        clubPyccaFragmentAdapter = new ClubPyccaFragmentAdapter(getActivity(), clubPyccaArrayList, new ClubPyccaFragmentAdapter.OnItemClickListener() {
            @Override
            public void onClick(ClubPyccaFragmentAdapter.ClubPyccaViewHolder clubPyccaViewHolder, final int position, ImageView imageView) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
                objectAnimator.setDuration(Constants.ANIMATION_DURATION);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimator);
                animatorSet.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (position) {
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                Intent virtualCardActivity = new Intent(getActivity(), VirtualCardActivity.class);
                                startActivity(virtualCardActivity);
                                break;
                            case 5:
                                break;
                        }
                    }
                }, Constants.ANIMATION_DURATION);
            }
        });
        rv_club_pycca.setAdapter(clubPyccaFragmentAdapter);
        rv_club_pycca.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        rv_club_pycca.setItemAnimator(new DefaultItemAnimator());
        rv_club_pycca.setHasFixedSize(false);
        rv_club_pycca.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(ClubPyccaFragment.this);
        presenter.loadClubPyccaArrayList();
    }

}
