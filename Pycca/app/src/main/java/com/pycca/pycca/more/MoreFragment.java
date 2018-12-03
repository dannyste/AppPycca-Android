package com.pycca.pycca.more;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pycca.pycca.R;
import com.pycca.pycca.host.HostActivity;
import com.pycca.pycca.ourshop.OurShopActivity;
import com.pycca.pycca.pojo.More;
import com.pycca.pycca.profile.ProfileActivity;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import javax.inject.Inject;

public class MoreFragment extends Fragment implements MoreFragmentMVP.View {

    private static final String TAG = MoreFragment.class.getName();

    @Inject
    public MoreFragmentMVP.Presenter presenter;

    private LinearLayout ll_root_view;
    private RecyclerView rv_more;

    private ArrayList<More> moreArrayList ;
    private MoreFragmentAdapter moreFragmentAdapter;

    private boolean animationRunning = true;

    private static final int RC_PERMISSION = 1000;

    public MoreFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(MoreFragment.this);
        ll_root_view = view.findViewById(R.id.ll_root_view);
        rv_more      = view.findViewById(R.id.rv_more);
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
                            int newPosition = moreArrayList.size() < 4 ? position + 1 : position;
                            switch (newPosition) {
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
    public void showAlertDialogClubPyccaPartner() {
        ((HostActivity) getActivity()).showAlertDialogClubPyccaPartner();
    }

    @Override
    public void goToProfileActivity() {
        Intent profileActivity = new Intent(getActivity(), ProfileActivity.class);
        startActivity(profileActivity);
    }

    @Override
    public void showAlertDialogContactUs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.contact_us)
                .setItems(R.array.contact_us_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                        switch (position) {
                            case 0:
                                presenter.alertDialogFirstItemClicked();
                                break;
                            case 1:
                                presenter.alertDialogSecondItemClicked();
                                break;
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @Override
    public void goToContactCall() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + presenter.getPhoneNumber(MoreFragment.this)));
            startActivity(intent);
        }
        else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, RC_PERMISSION);
        }
    }

    @Override
    public void goToContactEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { presenter.getEmail(MoreFragment.this) });
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void goToOurShopsActivity() {
        Intent ourShopsActivity = new Intent(getActivity(), OurShopActivity.class);
        startActivity(ourShopsActivity);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(MoreFragment.this);
        presenter.loadMoreArrayList(MoreFragment.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RC_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goToContactCall();
                }
                else {
                    Util.showMessage(ll_root_view, getString(R.string.permission_necessary));
                }
            }
        }
    }

}
