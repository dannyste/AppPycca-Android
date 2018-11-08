package com.pycca.pycca.clubpyccapartner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.pycca.pycca.R;
import com.pycca.pycca.root.App;

public class ClubPyccaPartnerActivity extends AppCompatActivity {

    private static final String TAG = ClubPyccaPartnerActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_pycca_partner);
        //((App) getApplication()).getApplicationComponent().inject(ClubPyccaPartnerActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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