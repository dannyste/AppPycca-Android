package com.pycca.pycca.balance;

import com.pycca.pycca.R;
import com.pycca.pycca.host.HostActivityPresenter;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.signup.SignUpActivityMVP;
import com.pycca.pycca.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceActivityPresenter implements BalanceActivityMVP.Presenter {

    private BalanceActivityMVP.View view;
    private BalanceActivityMVP.Model model;

    BalanceActivityPresenter(BalanceActivityMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(BalanceActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadData(BalanceActivity activity) {
        view.showLoadingAnimation();
        User user = model.getUser(activity);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<BaseResponse> getBalanceCall = endpointsApi.getBalance("9218101008274040");
        getBalanceCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    BaseResponse baseResponse = response.body();
                    if (baseResponse.getStatus()) {
                        if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                            view.setData(model.getBalance(baseResponse, "9218101008274040"), Util.maskClubPyccaCardNumber("9218101008274040"));
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

    public void onError(int errorCode) {
        view.showErrorAnimation();
    }
}
