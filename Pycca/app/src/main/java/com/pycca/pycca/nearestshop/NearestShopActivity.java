package com.pycca.pycca.nearestshop;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShopDetail;
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
    private LocationCallback locationCallback;

    private LatLng centerLatLng;

    private static final int RC_PERMISSION = 1000;
    private static final int RC_CHECK_SETTINGS = 2000;

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
            ActivityCompat.requestPermissions(NearestShopActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, RC_PERMISSION);
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
        SettingsClient settingsClient = LocationServices.getSettingsClient(NearestShopActivity.this);
        Task<LocationSettingsResponse> locationSettingsResponseTask = settingsClient.checkLocationSettings(locationSettingsRequest);
        locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse locationSettingsResponse = task.getResult(ApiException.class);
                    startLocationUpdates();
                }
                catch (ApiException apiException) {
                    switch (apiException.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) apiException;
                                resolvableApiException.startResolutionForResult(NearestShopActivity.this, RC_CHECK_SETTINGS);
                            }
                            catch (IntentSender.SendIntentException sendIntentException) {
                                Util.showMessage(ll_root_view, getString(R.string.error_default));
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            Util.showMessage(ll_root_view, getString(R.string.error_default));
                            break;
                    }
                }
            }
        });
    }

    private void startLocationUpdates() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                onLocationChanged(locationResult.getLastLocation());
            }
        };
        getFusedLocationProviderClient(NearestShopActivity.this).requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        centerLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, Constants.GOOGLE_MAP_ZOOM_RADIUS));
        getFusedLocationProviderClient(NearestShopActivity.this).removeLocationUpdates(locationCallback);
        presenter.loadOurShopsDetailsArrayList();
    }

    @Override
    public void showMarkersGoogleMap(ArrayList<OurShopDetail> ourShopDetailArrayList) {
        for (OurShopDetail ourShopDetail : ourShopDetailArrayList) {
            LatLng latLng = new LatLng(ourShopDetail.getLatitude(), ourShopDetail.getLongitude());
            float distance = LatLngUtils.distanceBetween(latLng, centerLatLng);
            if (distance < Constants.GOOGLE_MAP_RADIUS) {
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(ourShopDetail.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_google_map));
                googleMap.addMarker(markerOptions);
            }
        }
    }

    @Override
    public void showErrorMessage(int error) {
        Util.showMessage(ll_root_view, getString(error));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(NearestShopActivity.this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RC_PERMISSION: {
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case RC_CHECK_SETTINGS:
                switch (resultCode) {
                    case RESULT_OK:
                        startLocationUpdates();
                        break;
                    case RESULT_CANCELED:
                        break;
                    default:
                        break;
                }
                break;
        }
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