package com.pycca.pycca.ourshopdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShopDetail;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

public class OurShopDetailActivity extends AppCompatActivity implements OnMapReadyCallback, OurShopDetailActivityMVP.View {

    @Inject
    public OurShopDetailActivityMVP.Presenter presenter;

    private GoogleMap googleMap;

    private RecyclerView rv_our_shops_details;

    private ArrayList<OurShopDetail> ourShopDetailArrayList;
    private OurShopDetailActivityAdapter ourShopDetailActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_shop_detail);
        ((App) getApplication()).getApplicationComponent().inject(OurShopDetailActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(OurShopDetailActivity.this);
        rv_our_shops_details = findViewById(R.id.rv_our_shops_details);
        Bundle bundle = getIntent().getExtras();
        ourShopDetailArrayList = bundle.getParcelableArrayList("ourShopDetailArrayList");
        initRecyclerView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        initMarkerGoogleMap();
    }

    public void initMarkerGoogleMap() {
        addMarkerGoogleMap(ourShopDetailArrayList.get(0));
    }

    public void initRecyclerView() {
        ourShopDetailActivityAdapter = new OurShopDetailActivityAdapter(OurShopDetailActivity.this, ourShopDetailArrayList, new OurShopDetailActivityAdapter.OnItemClickListener() {
            @Override
            public void onClick(OurShopDetailActivityAdapter.OurShopDetailViewHolder ourShopDetailViewHolder, OurShopDetail ourShopDetail) {
                presenter.itemClicked(ourShopDetail);
            }
        });
        rv_our_shops_details.setAdapter(ourShopDetailActivityAdapter);
        rv_our_shops_details.addItemDecoration(new DividerItemDecoration(OurShopDetailActivity.this, DividerItemDecoration.VERTICAL));
        rv_our_shops_details.setItemAnimator(new DefaultItemAnimator());
        rv_our_shops_details.setHasFixedSize(false);
        rv_our_shops_details.setLayoutManager(new LinearLayoutManager(OurShopDetailActivity.this));
    }

    @Override
    public void clearMarkersGoogleMap() {
        googleMap.clear();
    }

    @Override
    public void addMarkerGoogleMap(OurShopDetail ourShopDetail) {
        LatLng latLng = new LatLng(ourShopDetail.getLatitude(), ourShopDetail.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(ourShopDetail.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_google_map));
        googleMap.addMarker(markerOptions).showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.GOOGLE_MAP_ZOOM));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(OurShopDetailActivity.this);
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
