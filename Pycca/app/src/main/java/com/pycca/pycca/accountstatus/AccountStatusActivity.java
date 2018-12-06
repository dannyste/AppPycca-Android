package com.pycca.pycca.accountstatus;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.AccountStatus;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import java.io.File;

import javax.inject.Inject;

public class AccountStatusActivity extends AppCompatActivity implements AccountStatusActivityMVP.View {

    @Inject
    public AccountStatusActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_loading, ll_error, ll_downloading_pdf, ll_overdue_balance;
    private TextView tv_available_credit, tv_used_quota, tv_aproved_quota, tv_pay_until, tv_overdue_balance;
    private LottieAnimationView lav_loading, lav_error, lav_downloading_pdf;
    private Button bt_download_pdf;
    private static final int RC_PERMISSION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_status);
        ((App) getApplication()).getApplicationComponent().inject(AccountStatusActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_available_credit = findViewById(R.id.tv_available_credit);
        tv_used_quota = findViewById(R.id.tv_used_quota);
        tv_aproved_quota = findViewById(R.id.tv_aproved_quota);
        tv_pay_until = findViewById(R.id.tv_pay_until);
        ll_root_view = findViewById(R.id.ll_root_view);
        ll_loading   = findViewById(R.id.ll_loading);
        ll_overdue_balance   = findViewById(R.id.ll_overdue_balance);
        lav_loading  = findViewById(R.id.lav_loading);
        ll_error     = findViewById(R.id.ll_error);
        lav_error    = findViewById(R.id.lav_error);
        bt_download_pdf    = findViewById(R.id.bt_download_pdf);
        ll_downloading_pdf     = findViewById(R.id.ll_downloading_pdf);
        lav_downloading_pdf    = findViewById(R.id.lav_downloading_pdf);
        tv_overdue_balance    = findViewById(R.id.tv_overdue_balance);

        bt_download_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(AccountStatusActivity.this);
        presenter.loadData(this);
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

    @Override
    public void setData(AccountStatus accountStatus) {
        tv_available_credit.setText("$".concat(String.valueOf(accountStatus.getAvailableCredit())));
        tv_used_quota.setText("$".concat(String.valueOf(accountStatus.getUsedCredit())));
        tv_aproved_quota.setText("$".concat(String.valueOf(accountStatus.getAprovedQuota())));
        tv_pay_until.setText(Util.formatStringPayUntil(accountStatus.getPayUntil(),String.valueOf(accountStatus.getQuotaToPay())));
        ll_overdue_balance.setVisibility(View.INVISIBLE);
        if(accountStatus.isOverdue()){
            ll_overdue_balance.setVisibility(View.VISIBLE);
            tv_overdue_balance.setText(getResources().getString(R.string.overdue_balance).concat(" $").concat(String.valueOf(accountStatus.getQuotaToPay())));
        }
    }

    @Override
    public void showMessage(int strCode) {
        Util.showMessage(ll_root_view, getResources().getString(strCode));
    }

    @Override
    public void showLoadingAnimation() {
        ll_root_view.setVisibility(View.GONE);
        ll_loading.setVisibility(View.VISIBLE);
        lav_loading.playAnimation();
    }

    @Override
    public void hideLoadingAnimation() {
        lav_loading.pauseAnimation();
        ll_loading.setVisibility(View.GONE);
        ll_root_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorAnimation() {
        lav_loading.pauseAnimation();
        ll_loading.setVisibility(View.GONE);
        ll_error.setVisibility(View.VISIBLE);
        lav_error.playAnimation();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public AccountStatusActivity getActivity() {
        return this;
    }

    @Override
    public void showDownloadAnimation() {
        ll_root_view.setVisibility(View.GONE);
        ll_downloading_pdf.setVisibility(View.VISIBLE);
        lav_downloading_pdf.playAnimation();
    }

    @Override
    public void hideDownloadAnimation() {
        lav_downloading_pdf.pauseAnimation();
        ll_downloading_pdf.setVisibility(View.GONE);
        ll_root_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccesfullNotification() {
        File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(),"EstadoDeCuentaPycca.pdf");
        Uri pdfUri = FileProvider.getUriForFile(getApplicationContext(),"com.pycca.pycca.fileprovider", file);

        Intent actionView = new Intent(Intent.ACTION_VIEW);
        actionView.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        actionView.setDataAndType(pdfUri, "application/pdf");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, actionView, 0);


        String channelId = getString(R.string.app_name);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.drawable.ic_download)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(getActivity().getResources().getString(R.string.download_succesfull))
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    @Override
    public void showErrorNotification() {
        String channelId = getString(R.string.app_name);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.drawable.ic_download)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(getResources().getString(R.string.download_error))
                        .setSound(defaultSoundUri)
                        .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RC_PERMISSION: {
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    presenter.downdloadPDF();
                }
                else {
                    Util.showMessage(ll_root_view, getString(R.string.permissions_necessary));
                }
            }
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSION);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSION);
            }
        }
        else {
            presenter.downdloadPDF();
        }
    }
}
