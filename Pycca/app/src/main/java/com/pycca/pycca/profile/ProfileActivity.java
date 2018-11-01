package com.pycca.pycca.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pycca.pycca.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

}
