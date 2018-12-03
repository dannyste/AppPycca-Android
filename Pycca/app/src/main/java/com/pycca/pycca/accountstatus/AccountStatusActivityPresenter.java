package com.pycca.pycca.accountstatus;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.SharedPreferencesManager;
import com.pycca.pycca.util.Util;

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

    }

    public void onError(int errorCode) {
        view.showErrorAnimation();
    }
}
