package com.pycca.pycca.ourshopsdetails;

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
import com.pycca.pycca.pojo.OurShopsDetails;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Constants;

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
        mapFragment.getMapAsync(OurShopsDetailsActivity.this);
        rv_our_shops_details = findViewById(R.id.rv_our_shops_details);
        Bundle bundle = getIntent().getExtras();
        ourShopsDetailsArrayList = bundle.getParcelableArrayList("ourShopsDetailsArrayList");
        initRecyclerView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        initMarkerGoogleMap();
    }

    public void initMarkerGoogleMap() {
        addMarkerGoogleMap(ourShopsDetailsArrayList.get(0));
    }

    public void initRecyclerView() {
        ourShopsDetailsActivityAdapter = new OurShopsDetailsActivityAdapter(OurShopsDetailsActivity.this, ourShopsDetailsArrayList, new OurShopsDetailsActivityAdapter.OnItemClickListener() {
            @Override
            public void onClick(OurShopsDetailsActivityAdapter.OurShopsDetailsViewHolder ourShopsDetailsViewHolder, OurShopsDetails ourShopsDetails) {
                presenter.itemClicked(ourShopsDetails);
            }
        });
        rv_our_shops_details.setAdapter(ourShopsDetailsActivityAdapter);
        rv_our_shops_details.addItemDecoration(new DividerItemDecoration(OurShopsDetailsActivity.this, DividerItemDecoration.VERTICAL));
        rv_our_shops_details.setItemAnimator(new DefaultItemAnimator());
        rv_our_shops_details.setHasFixedSize(false);
        rv_our_shops_details.setLayoutManager(new LinearLayoutManager(OurShopsDetailsActivity.this));
    }

    @Override
    public void clearMarkersGoogleMap() {
        googleMap.clear();
    }

    @Override
    public void addMarkerGoogleMap(OurShopsDetails ourShopsDetails) {
        LatLng latLng = new LatLng(ourShopsDetails.getLatitude(), ourShopsDetails.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(ourShopsDetails.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_google_map));
        googleMap.addMarker(markerOptions).showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.GOOGLE_MAP_ZOOM));
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
