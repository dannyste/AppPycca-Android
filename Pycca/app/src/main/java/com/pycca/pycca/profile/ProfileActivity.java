package com.pycca.pycca.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pycca.pycca.R;
import com.pycca.pycca.host.HostActivity;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.root.App;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements ProfileActivityMVP.View {

    private static final String TAG = ProfileActivity.class.getName();

    @Inject
    public ProfileActivityMVP.Presenter presenter;

    private CircleImageView civ_photo;
    private TextView tv_name, tv_email, tv_identification_card, tv_club_pycca_card_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ((App) getApplication()).getApplicationComponent().inject(ProfileActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        civ_photo                 = findViewById(R.id.civ_photo);
        tv_name                   = findViewById(R.id.tv_name);
        tv_email                  = findViewById(R.id.tv_email);
        tv_identification_card    = findViewById(R.id.tv_identification_card);
        tv_club_pycca_card_number = findViewById(R.id.tv_club_pycca_card_number);
    }

    @Override
    public void goToMultiLoginActivity() {
        Intent multiLoginActivity = new Intent(ProfileActivity.this, MultiLoginActivity.class);
        startActivity(multiLoginActivity);
        if (HostActivity.activity != null) {
            HostActivity.activity.finish();
        }
        finish();
    }

    @Override
    public void setPhotoUrl(String photoUrl) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerInside().diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.drawable.photo).error(R.drawable.photo);
        Glide.with(ProfileActivity.this).load(photoUrl).apply(requestOptions).into(civ_photo);
    }

    @Override
    public void setName(String name) {
        tv_name.setText(name);
    }

    @Override
    public void setEmail(String email) {
        tv_email.setText(email);
    }

    @Override
    public void setIdentificationCard(String identificationCard) {
        tv_identification_card.setText(identificationCard);
    }

    @Override
    public void setClubPyccaCardNumber(String clubPyccaCardNumber) {
        tv_club_pycca_card_number.setText(clubPyccaCardNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mi_logout) {
            presenter.logoutClicked(ProfileActivity.this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(ProfileActivity.this);
        presenter.getCurrentUser(ProfileActivity.this);
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
