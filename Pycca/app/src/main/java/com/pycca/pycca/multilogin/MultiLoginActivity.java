package com.pycca.pycca.multilogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pycca.pycca.R;

import javax.inject.Inject;

public class MultiLoginActivity extends AppCompatActivity implements MultiLoginActivityMVP.View {

    private static final String TAG = MultiLoginActivity.class.getName();

    @Inject
    public MultiLoginActivityMVP.Presenter presenter;

    private Button btn_login_email, btn_login_google, btn_login_facebook, btn_login_instagram;
    private TextView tv_register_now, tv_terms_use;

    private FirebaseAuth firebaseAuth;

    private GoogleSignInClient googleSignInClient;

    private static final int LOGIN_GOOGLE = 9001;

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
        tv_register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_terms_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(MultiLoginActivity.this, googleSignInOptions);
    }

    @Override
    public void loginGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, LOGIN_GOOGLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
