package com.pycca.pycca.nearestshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.pycca.pycca.R;
import com.pycca.pycca.root.App;

import javax.inject.Inject;

public class NearestShopActivity extends AppCompatActivity implements OnMapReadyCallback, NearestShopActivityMVP.View {

    private static final String TAG = NearestShopActivity.class.getName();

    @Inject
    public NearestShopActivityMVP.Presenter presenter;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_shop);
        ((App) getApplication()).getApplicationComponent().inject(NearestShopActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(NearestShopActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(NearestShopActivity.this);
    }

}
