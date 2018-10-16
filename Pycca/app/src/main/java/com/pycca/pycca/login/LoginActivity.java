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
import com.pycca.pycca.forgotpassword.ForgotPasswordActivity;
import com.pycca.pycca.host.HostActivity;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;
import com.wang.avi.AVLoadingIndicatorView;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    private static final String TAG = LoginActivity.class.getName();

    @Inject
    public LoginActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view;
    private TextInputEditText tiet_email, tiet_password;
    private Button btn_login;
    private TextView tv_forgot_password;
    private AVLoadingIndicatorView avliv_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((App) getApplication()).getApplicationComponent().inject(LoginActivity.this);
        ll_root_view        = findViewById(R.id.ll_root_view);
        tiet_email          = findViewById(R.id.tiet_email);
        tiet_password       = findViewById(R.id.tiet_password);
        btn_login           = findViewById(R.id.btn_login);
        tv_forgot_password  = findViewById(R.id.tv_forgot_password);
        avliv_loading       = findViewById(R.id.avliv_loading);

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
        Util.showMessage(ll_root_view, getResources().getString(R.string.invalid_email));
    }

    @Override
    public void showEmailRequired() {
        Util.showMessage(ll_root_view, getResources().getString(R.string.email_required));
    }

    @Override
    public void showPasswordRequired() {
        Util.showMessage(ll_root_view, getResources().getString(R.string.password_required));
    }

    @Override
    public void goToHostActivity() {
        Intent hostActivity = new Intent(LoginActivity.this, HostActivity.class);
        startActivity(hostActivity);
        finish();
    }

    @Override
    public void goToForgotPasswordActivity() {
        Intent forgotPasswordActivity = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(forgotPasswordActivity);
        finish();
    }

    @Override
    public void disableWidgets() {
        setEnableWidgets(false);
    }

    @Override
    public void enableWidgets() {
        setEnableWidgets(true);
    }

    @Override
    public void showProgress() {
        avliv_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        avliv_loading.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(int errorCode) {
        Util.showMessage(ll_root_view, getResources().getString(errorCode));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(LoginActivity.this);
    }

    private void setEnableWidgets(boolean enable){
        tiet_email.setEnabled(enable);
        tiet_password.setEnabled(enable);
        btn_login.setEnabled(enable);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

}
