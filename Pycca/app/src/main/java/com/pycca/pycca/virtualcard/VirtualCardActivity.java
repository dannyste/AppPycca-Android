package com.pycca.pycca.virtualcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.pycca.pycca.R;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class VirtualCardActivity extends AppCompatActivity implements VirtualCardActivityMVP.View {

    private static final String TAG = VirtualCardActivity.class.getName();

    @Inject
    public VirtualCardActivityMVP.Presenter presenter;

    private TextView tv_club_pycca_card_number, tv_name, tv_client_since;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_card);
        ((App) getApplication()).getApplicationComponent().inject(VirtualCardActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tv_club_pycca_card_number = findViewById(R.id.tv_club_pycca_card_number);
        tv_name                   = findViewById(R.id.tv_name);
        tv_client_since           = findViewById(R.id.tv_client_since);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(VirtualCardActivity.this);
        presenter.getCurrentUser(VirtualCardActivity.this);
    }

    @Override
    public void setClubPyccaCardNumber(String clubPyccaCardNumber) {
        tv_club_pycca_card_number.setText(Util.clipCardNumber(clubPyccaCardNumber));
    }

    @Override
    public void setName(String name) {
        tv_name.setText(name);
    }

    @Override
    public void setClientSince(String clientSince) {
        tv_client_since.setText(clientSince);
    }
}
