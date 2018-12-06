package com.pycca.pycca.accountstatus;

import android.Manifest;
import android.app.DownloadManager;
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
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.SharedPreferencesManager;
import com.pycca.pycca.util.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
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
        Call<BaseResponse> getBalanceCall = endpointsApi.getAccountStatus(user.getClubPyccaCardNumber());
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

        view.showDownloadAnimation();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.setConnectionRestApiServer();
        Call<ResponseBody> call = endpoints.getPdfAccountStatus("8813","2018-06-30");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(Util.writeResponseBodyToDisk(response.body(), "EstadoDeCuentaPycca")){
                    view.showSuccesfullNotification();
                }else {
                    view.showErrorNotification();
                }
                view.getActivity().hideDownloadAnimation();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showErrorNotification();
                view.getActivity().hideDownloadAnimation();
            }
        });
    }

    public void onError(int errorCode) {
        view.showErrorAnimation();
    }
}
