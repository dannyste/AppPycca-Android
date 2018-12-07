package com.pycca.pycca.ourshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.nearestshop.NearestShopActivity;
import com.pycca.pycca.ourshopdetail.OurShopDetailActivity;
import com.pycca.pycca.pojo.OurShop;
import com.pycca.pycca.pojo.OurShopDetail;
import com.pycca.pycca.root.App;

import java.util.ArrayList;

import javax.inject.Inject;

public class OurShopActivity extends AppCompatActivity implements OurShopActivityMVP.View {

    private static final String TAG = OurShopActivity.class.getName();

    @Inject
    public OurShopActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_loading, ll_error;
    private RelativeLayout rl_go_nearest_shop;
    private RecyclerView rv_our_shop;
    private LottieAnimationView lav_loading, lav_error;
    private TextView tv_error_touch_retry;

    private ArrayList<OurShop> ourShopArrayList;
    private OurShopActivityAdapter ourShopActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_shop);
        ((App) getApplication()).getApplicationComponent().inject(OurShopActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ll_root_view         = findViewById(R.id.ll_root_view);
        rl_go_nearest_shop   = findViewById(R.id.rl_go_nearest_shop);
        rv_our_shop          = findViewById(R.id.rv_our_shop);
        ll_loading           = findViewById(R.id.ll_loading);
        lav_loading          = findViewById(R.id.lav_loading);
        ll_error             = findViewById(R.id.ll_error);
        lav_error            = findViewById(R.id.lav_error);
        tv_error_touch_retry = findViewById(R.id.tv_error_touch_retry);
        rl_go_nearest_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goNearestShopClicked();
            }
        });
        tv_error_touch_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.errorTouchRetryClicked();
            }
        });
        initRecyclerView();
    }

    public void initRecyclerView() {
        ourShopArrayList = new ArrayList<>();
        ourShopActivityAdapter = new OurShopActivityAdapter(OurShopActivity.this, ourShopArrayList, new OurShopActivityAdapter.OnItemClickListener() {
            @Override
            public void onClick(OurShopActivityAdapter.OurShopViewHolder ourShopViewHolder, OurShop ourShop) {
                presenter.itemClicked(ourShop.getOurShopDetailArrayList());
            }
        });
        rv_our_shop.setAdapter(ourShopActivityAdapter);
        rv_our_shop.addItemDecoration(new DividerItemDecoration(OurShopActivity.this, DividerItemDecoration.VERTICAL));
        rv_our_shop.setItemAnimator(new DefaultItemAnimator());
        rv_our_shop.setHasFixedSize(false);
        rv_our_shop.setLayoutManager(new LinearLayoutManager(OurShopActivity.this));
    }

    @Override
    public void showRootView() {
        ll_root_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRootView() {
        ll_root_view.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingAnimation() {
        ll_loading.setVisibility(View.VISIBLE);
        lav_loading.playAnimation();
    }

    @Override
    public void hideLoadingAnimation() {
        ll_loading.setVisibility(View.GONE);
        lav_loading.pauseAnimation();
    }

    @Override
    public void showErrorAnimation() {
        ll_error.setVisibility(View.VISIBLE);
        lav_error.playAnimation();
    }

    @Override
    public void hideErrorAnimation() {
        ll_error.setVisibility(View.GONE);
        lav_error.pauseAnimation();
    }

    @Override
    public void updateDataRecyclerView(ArrayList<OurShop> ourShopArrayList) {
        this.ourShopArrayList.clear();
        this.ourShopArrayList.addAll(ourShopArrayList);
        ourShopActivityAdapter.notifyDataSetChanged();
    }

    @Override
    public void goToNearestShopActivity() {
        Intent nearestShopActivity = new Intent(OurShopActivity.this, NearestShopActivity.class);
        startActivity(nearestShopActivity);
    }

    @Override
    public void goToOurShopsDetailsActivity(ArrayList<OurShopDetail> ourShopDetailArrayList) {
        Intent ourShopsDetailsActivity = new Intent(OurShopActivity.this, OurShopDetailActivity.class);
        ourShopsDetailsActivity.putParcelableArrayListExtra("ourShopDetailArrayList", ourShopDetailArrayList);
        startActivity(ourShopsDetailsActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(OurShopActivity.this);
        presenter.loadOurShopsArrayList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.cancelServiceCall();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
