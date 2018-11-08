package com.pycca.pycca.ourshopsdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShopsDetails;
import com.pycca.pycca.root.App;

import java.util.ArrayList;

import javax.inject.Inject;

public class OurShopsDetailsActivity extends AppCompatActivity implements OnMapReadyCallback, OurShopsDetailsActivityMVP.View {

    @Inject
    public OurShopsDetailsActivityMVP.Presenter presenter;

    private GoogleMap googleMap;

    private RecyclerView rv_our_shops_details;

    private ArrayList<OurShopsDetails> ourShopsDetailsArrayList ;
    private OurShopsDetailsActivityAdapter ourShopsDetailsActivityAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_shops_details);
        ((App) getApplication()).getApplicationComponent().inject(OurShopsDetailsActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(OurShopsDetailsActivity.this);
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
