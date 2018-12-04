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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.downloader.utils.Utils;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.SharedPreferencesManager;
import com.pycca.pycca.util.Util;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountStatusActivityPresenter implements AccountStatusActivityMVP.Presenter {

    private AccountStatusActivityMVP.View view;
    private AccountStatusActivityMVP.Model model;

    AccountStatusActivityPresenter(AccountStatusActivityMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(AccountStatusActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadData(AccountStatusActivity activity) {
        view.showLoadingAnimation();
        final User user = model.getUser(activity);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<BaseResponse> getBalanceCall = endpointsApi.getBalance(user.getClubPyccaCardNumber());
        getBalanceCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    BaseResponse baseResponse = response.body();
                    if (baseResponse.getStatus()) {
                        if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                            view.setData(model.getBalance(baseResponse, user.getClubPyccaCardNumber()));
                            view.hideLoadingAnimation();
                        }
                        else {
                            onError(R.string.error_default);
                        }
                    }
                    else {
                        onError(R.string.error_default);
                    }
                }else {
                    onError(R.string.error_default);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onError(R.string.error_default);
            }
        });
    }

    @Override
    public void downdloadPDF() {

        // Setting timeout globally for the download network requests:
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build();
        PRDownloader.initialize(view.getActivity().getApplicationContext(), config);

        //final String dirPath = Util.getRootDirPath(view.getActivity().getApplicationContext());
        final String dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

        view.showDownloadAnimation();
        int downloadId = PRDownloader.download(Util.getUrlDownloadPDF("8813","2018-06-30"), dirPath, "EstadoDeCuenta.pdf")
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {

                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {
                        view.hideDownloadAnimation();
                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {

                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // Create an explicit intent for an Activity in your app
                        File file = new File( dirPath,"EstadoDeCuenta.pdf");
                        Uri pdfUri = FileProvider.getUriForFile(view.getActivity().getApplicationContext(), "com.pycca.pycca.fileprovider", file);
                        //File filePath = new File(Uri.parse(dirPath.concat("/").concat("EstadoDeCuenta.pdf")).getPath());

                        Intent i = new Intent();
                        i.setAction(android.content.Intent.ACTION_VIEW);
                        //i.setDataAndType(FileProvider.getUriForFile(view.getActivity().getApplicationContext(),"com.pycca.pycca.fileprovider",filePath), "application/pdf");
                        i.setDataAndType(pdfUri, "application/pdf");
                        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        PendingIntent pendingIntent = PendingIntent.getActivity(view.getActivity().getApplicationContext(), 0, i, 0);

                        String channelId = view.getActivity().getString(R.string.app_name);
                        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                        NotificationCompat.Builder notificationBuilder =
                                new NotificationCompat.Builder(view.getActivity().getApplicationContext(), channelId)
                                .setSmallIcon(R.drawable.ic_download)
                                .setContentTitle(view.getActivity().getString(R.string.app_name))
                                .setContentText("Estado de cuenta descargado")
                                .setSound(defaultSoundUri)
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true);

                        NotificationManager notificationManager = (NotificationManager) view.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                        // Since android Oreo notification channel is needed.
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel(channelId, view.getActivity().getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
                            notificationManager.createNotificationChannel(channel);
                        }

                        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

                        view.hideDownloadAnimation();
                    }

                    @Override
                    public void onError(Error error) {
                        view.hideDownloadAnimation();
                    }

                });
    }



    public void onError(int errorCode) {
        view.showErrorAnimation();
    }
}
