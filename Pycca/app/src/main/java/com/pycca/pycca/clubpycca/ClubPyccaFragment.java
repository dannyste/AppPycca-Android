package com.pycca.pycca.clubpycca;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.pycca.pycca.R;
import com.pycca.pycca.accountstatus.AccountStatusActivity;
import com.pycca.pycca.cardlocking.CardLockingActivity;
import com.pycca.pycca.host.HostActivity;
import com.pycca.pycca.pojo.ClubPycca;
import com.pycca.pycca.quotacalculator.QuotaCalculatorActivity;
import com.pycca.pycca.quotaincrease.QuotaIncreaseActivity;
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

    private boolean animationRunning = true;

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

    public void initRecyclerView() {
        clubPyccaArrayList = new ArrayList<>();
        clubPyccaFragmentAdapter = new ClubPyccaFragmentAdapter(getActivity(), clubPyccaArrayList, new ClubPyccaFragmentAdapter.OnItemClickListener() {
            @Override
            public void onClick(ClubPyccaFragmentAdapter.ClubPyccaViewHolder clubPyccaViewHolder, final int position, ImageView imageView) {
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
                                    presenter.clubPyccaFirstItemClicked();
                                    break;
                                case 1:
                                    presenter.clubPyccaSecondItemClicked(ClubPyccaFragment.this);
                                    break;
                                case 2:
                                    presenter.clubPyccaThirdItemClicked();
                                    break;
                                case 3:
                                    presenter.clubPyccaFourthItemClicked(ClubPyccaFragment.this);
                                    break;
                                case 4:
                                    presenter.clubPyccaFifthItemClicked();
                                    break;
                            }
                            animationRunning = true;
                        }
                    }, Constants.ANIMATION_DURATION);
                }
            }
        });
        rv_club_pycca.setAdapter(clubPyccaFragmentAdapter);
        rv_club_pycca.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        rv_club_pycca.setItemAnimator(new DefaultItemAnimator());
        rv_club_pycca.setHasFixedSize(false);
        rv_club_pycca.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void updateDataRecyclerView(ArrayList<ClubPycca> clubPyccaArrayList) {
        this.clubPyccaArrayList.clear();
        this.clubPyccaArrayList.addAll(clubPyccaArrayList);
        clubPyccaFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAlertDialogClubPyccaPartner() {
        ((HostActivity) getActivity()).showAlertDialogClubPyccaPartner();
    }

    @Override
    public void goToAccountStatusActivity() {
        Intent accountStatusActivity = new Intent(getActivity(), AccountStatusActivity.class);
        startActivity(accountStatusActivity);
    }

    @Override
    public void goToQuotaIncreaseActivity() {
        Intent quotaIncreaseActivity = new Intent(getActivity(), QuotaIncreaseActivity.class);
        startActivity(quotaIncreaseActivity);
    }

    @Override
    public void goToQuotaCalculatorActivity() {
        Intent quotaCalculatorActivity = new Intent(getActivity(), QuotaCalculatorActivity.class);
        startActivity(quotaCalculatorActivity);
    }

    @Override
    public void goToVirtualCardActivity() {
        Intent virtualCardActivity = new Intent(getActivity(), VirtualCardActivity.class);
        startActivity(virtualCardActivity);
    }

    @Override
    public void goToCardBlockingActivity() {
        Intent cardBlockingActivity = new Intent(getActivity(), CardLockingActivity.class);
        startActivity(cardBlockingActivity);
    }

    @Override
    public void showAlertDialogClubPyccaCardLocked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.club_pycca_card_locked_enter_another)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                })
                .setNegativeButton(R.string.not, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(getResources().getColor(R.color.colorPrimary));
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(ClubPyccaFragment.this);
        presenter.loadClubPyccaArrayList();
    }

}
