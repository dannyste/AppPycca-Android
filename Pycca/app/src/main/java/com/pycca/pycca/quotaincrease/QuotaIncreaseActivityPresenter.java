package com.pycca.pycca.quotaincrease;

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

public class QuotaIncreaseActivityPresenter implements QuotaIncreaseActivityMVP.Presenter {

    private QuotaIncreaseActivityMVP.View view;
    private QuotaIncreaseActivityMVP.Model model;

    QuotaIncreaseActivityPresenter(QuotaIncreaseActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(QuotaIncreaseActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void sendRequest() {
        if(isFormValid()){
            view.showLoadingAnimation();
            User user = model.getUser(view.getContext());
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
            Call<BaseResponse> quotaIncreaseCall = endpointsApi.postQuotaIncrease("1", String.valueOf(user.getAccountNumber()), user.getIdentificationCard(), user.getEmail(), user.getName(), user.getName(), view.getQuotaWanted());
            quotaIncreaseCall.enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.isSuccessful()) {
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getStatus()) {
                            if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                view.hideLoadingAnimation();
                                view.showDoneAnimation();
                            }
                            else {
                                view.hideLoadingAnimation();
                                view.showErrorAnimation();
                                view.showErrorMessage(R.string.error_default);
                            }
                        }
                        else {
                            view.hideLoadingAnimation();
                            view.showErrorAnimation();
                            view.showErrorMessage(R.string.error_default);
                        }
                    }else {
                        view.hideLoadingAnimation();
                        view.showErrorAnimation();
                        view.showErrorMessage(R.string.error_default);
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    view.hideLoadingAnimation();
                    view.showErrorAnimation();
                    view.showErrorMessage(R.string.error_default);
                }
            });
        }
    }

    @Override
    public void finishedDoneAnimation() {
        view.showViewFormSended();
    }

    @Override
    public void finishedErrorAnimation() {
        view.showViewForm();
    }

    @Override
    public boolean isFormValid() {
        if (view.getQuotaWanted().isEmpty()){
            view.showErrorMessage(R.string.all_fields_required);
            return false;
        }
        return true;
    }

}
