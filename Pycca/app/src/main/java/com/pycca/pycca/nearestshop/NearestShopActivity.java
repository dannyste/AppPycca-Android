package com.pycca.pycca.nearestshop;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.androidmapsextensions.utils.LatLngUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
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
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class NearestShopActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, NearestShopActivityMVP.View {

    private static final String TAG = NearestShopActivity.class.getName();

    @Inject
    public NearestShopActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view;

    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;

    private LatLng centerLatLng;

    private static final int RC_PERMISSION_REQUEST = 1000;

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
        ll_root_view = findViewById(R.id.ll_root_view);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (ContextCompat.checkSelfPermission(NearestShopActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            this.googleMap.setMyLocationEnabled(true);
        }
        else {
            ActivityCompat.requestPermissions(NearestShopActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, RC_PERMISSION_REQUEST);
        }
    }

    private synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(NearestShopActivity.this)
                .addConnectionCallbacks(NearestShopActivity.this)
                .addOnConnectionFailedListener(NearestShopActivity.this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        createLocationRequest();
        buildLocationSettingsRequest();
        checkLocationSettings();
        startLocationUpdates();
    }

    private void createLocationRequest() {
        locationRequest = new LocationRequest()
                .setInterval(Constants.LOCATION_REQUEST_INTERVAL)
                .setFastestInterval(Constants.LOCATION_REQUEST_FASTEST_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest).setAlwaysShow(true);
        locationSettingsRequest = builder.build();
    }

    private void checkLocationSettings() {
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);
    }

    private void startLocationUpdates() {
        getFusedLocationProviderClient(NearestShopActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.GOOGLE_MAP_ZOOM));
        getFusedLocationProviderClient(this).removeLocationUpdates(new LocationCallback());
        presenter.loadOurShopsDetailsArrayList();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RC_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (googleApiClient == null) {
                        buildGoogleApiClient();
                    }
                    googleMap.setMyLocationEnabled(true);
                }
                else {
                    Util.showMessage(ll_root_view, getString(R.string.permission_necessary));
                }
            }
        }
    }

    @Override
    public void showMarkersGoogleMap(ArrayList<OurShopsDetails> ourShopsDetailsArrayList) {
        for (OurShopsDetails ourShopsDetails: ourShopsDetailsArrayList) {
            LatLng latLng = new LatLng(ourShopsDetails.getLatitude(), ourShopsDetails.getLongitude());
            float distance = LatLngUtils.distanceBetween(latLng, centerLatLng);
            if (distance < 2000) {
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(ourShopsDetails.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_y));
                googleMap.addMarker(markerOptions);
            }
        }
    }

    @Override
    public void showErrorMessage(int error) {
        Util.showMessage(ll_root_view, getString(error));
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

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(NearestShopActivity.this);
    }

}
