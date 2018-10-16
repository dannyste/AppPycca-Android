package com.pycca.pycca.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pycca.pycca.R;
import com.pycca.pycca.home.HomeActivity;
import com.pycca.pycca.root.App;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    private static final String TAG = LoginActivity.class.getName();

    @Inject
    public LoginActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view;
    private TextInputEditText tiet_email, tiet_password;
    private Button btn_login;
    private TextView tv_forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((App) getApplication()).getApplicationComponent().inject(LoginActivity.this);
        ll_root_view      = findViewById(R.id.ll_root_view);
        tiet_email        = findViewById(R.id.tiet_email);
        tiet_password     = findViewById(R.id.tiet_password);
        btn_login         = findViewById(R.id.btn_login);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginClicked();
            }
        });
        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.forgotPasswordClicked();
            }
        });
    }

    @Override
    public String getEmail() {
        return this.tiet_email.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return this.tiet_password.getText().toString().trim();
    }

    @Override
    public void showInvalidEmail() {
        Snackbar.make(ll_root_view, R.string.invalid_email, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showEmailRequired() {
        Snackbar.make(ll_root_view, R.string.email_required, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showPasswordRequired() {
        Snackbar.make(ll_root_view, R.string.password_required, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void goToHomeActivity() {
        Intent homeActivity = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(LoginActivity.this);
    }

}
