package com.pycca.pycca.ourshops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShops;
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(OurShopsActivity.this);
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
