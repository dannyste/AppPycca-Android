package com.pycca.pycca.multilogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pycca.pycca.R;

import javax.inject.Inject;

public class MultiLoginActivity extends AppCompatActivity {

    private static final String TAG = MultiLoginActivity.class.getName();

    @Inject
    public MultiLoginActivityMVP.Presenter presenter;

    private Button btn_login_email, btn_login_google, btn_login_facebook, btn_login_instagram;
    private TextView tv_register_now, tv_terms_use;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_login);
        btn_login_email     = findViewById(R.id.btn_login_email);
        btn_login_google    = findViewById(R.id.btn_login_google);
        btn_login_facebook  = findViewById(R.id.btn_login_facebook);
        btn_login_instagram = findViewById(R.id.btn_login_instagram);
        tv_register_now     = findViewById(R.id.tv_register_now);
        tv_terms_use        = findViewById(R.id.tv_terms_use);
        btn_login_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_login_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
