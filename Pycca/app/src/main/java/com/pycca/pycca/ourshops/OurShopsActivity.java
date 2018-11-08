package com.pycca.pycca.ourshops;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.pycca.pycca.R;
import com.pycca.pycca.ourshopsdetails.OurShopsDetailsActivity;
import com.pycca.pycca.pojo.OurShops;
import com.pycca.pycca.pojo.OurShopsDetails;
import com.pycca.pycca.root.App;

import java.util.ArrayList;

import javax.inject.Inject;

public class OurShopsActivity extends AppCompatActivity implements OurShopsActivityMVP.View {

    private static final String TAG = OurShopsActivity.class.getName();

    @Inject
    public OurShopsActivityMVP.Presenter presenter;

    private RecyclerView rv_our_shops;

    private ArrayList<OurShops> ourShopsArrayList ;
    private OurShopsActivityAdapter ourShopsActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_shops);
        ((App) getApplication()).getApplicationComponent().inject(OurShopsActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rv_our_shops = findViewById(R.id.rv_club_pycca);
        initRecyclerView();
    }

    public void initRecyclerView() {
        ourShopsArrayList = new ArrayList<>();
        ourShopsActivityAdapter = new OurShopsActivityAdapter(OurShopsActivity.this, ourShopsArrayList, new OurShopsActivityAdapter.OnItemClickListener() {
            @Override
            public void onClick(OurShopsActivityAdapter.OurShopsViewHolder ourShopsViewHolder, OurShops ourShops) {
                presenter.itemClicked(ourShops.getOurShopsDetailsArrayList());
            }
        });
        rv_our_shops.setAdapter(ourShopsActivityAdapter);
        rv_our_shops.addItemDecoration(new DividerItemDecoration(OurShopsActivity.this, DividerItemDecoration.HORIZONTAL));
        rv_our_shops.setItemAnimator(new DefaultItemAnimator());
        rv_our_shops.setHasFixedSize(false);
        rv_our_shops.setLayoutManager(new LinearLayoutManager(OurShopsActivity.this));
    }

    @Override
    public void updateDataRecyclerView(ArrayList<OurShops> ourShopsArrayList) {
        this.ourShopsArrayList.clear();
        this.ourShopsArrayList.addAll(ourShopsArrayList);
        ourShopsActivityAdapter.notifyDataSetChanged();
    }

    @Override
    public void goToOurShopsDetailsActivity(ArrayList<OurShopsDetails> ourShopsDetails) {
        Intent ourShopsDetailsActivity = new Intent(OurShopsActivity.this, OurShopsDetailsActivity.class);
        //ourShopsDetailsActivity.putParcelableArrayListExtra("ourShopsDetails", ourShopsDetails);
        startActivity(ourShopsDetailsActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(OurShopsActivity.this);
        presenter.loadOurShopsArrayList();
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
