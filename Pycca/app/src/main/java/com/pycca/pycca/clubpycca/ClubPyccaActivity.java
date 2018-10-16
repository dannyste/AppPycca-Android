package com.pycca.pycca.clubpycca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.pycca.pycca.R;

public class ClubPyccaActivity extends AppCompatActivity {

    private static final String TAG = ClubPyccaActivity.class.getName();

    private RecyclerView rv_club_pycca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_pycca);
        rv_club_pycca = findViewById(R.id.rl_club_pycca);
    }

}
