package com.pycca.pycca.more;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
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

import com.google.firebase.auth.FirebaseAuth;
import com.pycca.pycca.R;
import com.pycca.pycca.clubpyccapartner.ClubPyccaPartnerActivity;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.ourshop.OurShopActivity;
import com.pycca.pycca.pojo.More;
import com.pycca.pycca.profile.ProfileActivity;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

public class MoreFragment extends Fragment implements MoreFragmentMVP.View {

    private static final String TAG = MoreFragment.class.getName();

    @Inject
    public MoreFragmentMVP.Presenter presenter;

    private RecyclerView rv_more;

    private ArrayList<More> moreArrayList ;
    private MoreFragmentAdapter moreFragmentAdapter;

    private boolean animationRunning = true;

    public MoreFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(MoreFragment.this);
        rv_more = view.findViewById(R.id.rv_more);
        initRecyclerView();
        return view;
    }

    public void initRecyclerView() {
        moreArrayList = new ArrayList<>();
        moreFragmentAdapter = new MoreFragmentAdapter(getActivity(), moreArrayList, new MoreFragmentAdapter.OnItemClickListener() {
            @Override
            public void onClick(MoreFragmentAdapter.MoreViewHolder moreViewHolder, final int position, ImageView imageView) {
                if (animationRunning) {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
                    objectAnimator.setDuration(Constants.ANIMATION_DURATION);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(objectAnimator);
                    animatorSet.start();
                    animationRunning = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            switch (position) {
                                case 0:
                                    presenter.firstItemClicked();
                                    break;
                                case 1:
                                    presenter.secondItemClicked();
                                    break;
                                case 2:
                                    presenter.thirdItemClicked();
                                    break;
                                case 3:
                                    presenter.fourthItemClicked();
                                    break;
                                case 4:
                                    presenter.fifthItemClicked();
                                    break;
                                case 5:
                                    presenter.sixthItemClicked();
                                    break;
                            }
                            animationRunning = true;
                        }
                    }, Constants.ANIMATION_DURATION);
                }
            }
        });
        rv_more.setAdapter(moreFragmentAdapter);
        rv_more.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        rv_more.setItemAnimator(new DefaultItemAnimator());
        rv_more.setHasFixedSize(false);
        rv_more.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void updateDataRecyclerView(ArrayList<More> moreArrayList) {
        this.moreArrayList.clear();
        this.moreArrayList.addAll(moreArrayList);
        moreFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void goToClubPyccaPartner() {
        Intent clubPyccaPartnerActivity = new Intent(getActivity(), ClubPyccaPartnerActivity.class);
        startActivity(clubPyccaPartnerActivity);
    }

    @Override
    public void goToProfileActivity() {
        Intent profileActivity = new Intent(getActivity(), ProfileActivity.class);
        startActivity(profileActivity);
    }

    @Override
    public void goToOurShopActivity() {
        Intent ourShopActivity = new Intent(getActivity(), OurShopActivity.class);
        startActivity(ourShopActivity);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(MoreFragment.this);
        presenter.loadMoreArrayList();
    }

}
