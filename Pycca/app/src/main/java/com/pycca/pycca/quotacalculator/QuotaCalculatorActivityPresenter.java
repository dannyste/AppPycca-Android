package com.pycca.pycca.quotacalculator;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotaCalculatorActivityPresenter implements QuotaCalculatorActivityMVP.Presenter {

    private QuotaCalculatorActivityMVP.View view;
    private QuotaCalculatorActivityMVP.Model model;

    QuotaCalculatorActivityPresenter(QuotaCalculatorActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(QuotaCalculatorActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void sendRequest() {
        if(isFormValid()){
            view.showLoadingAnimation();
            User user = model.getUser(view.getContext());
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
            Call<BaseResponse> quotaCalculatorCall = endpointsApi.getQuotas(view.getAmountToDiffer());
            quotaCalculatorCall.enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.isSuccessful()) {
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getStatus()) {
                            if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                view.hideLoadingAnimation();
                                view.showDoneAnimation();
                                view.setDataToFormQuotas(model.getQuotas(baseResponse),view.getAmountToDiffer());
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
    public void calculateAgain() {
        view.resetFormQuotas();
        view.resetFormAmountToDiffer();
        view.showViewFormAmountToDiffer();
    }

    @Override
    public void finishedDoneAnimation() {
        view.showViewFormQuotas();

    }

    @Override
    public void finishedErrorAnimation() {
        view.showViewFormAmountToDiffer();
    }

    @Override
    public boolean isFormValid() {
        if (view.getAmountToDiffer().isEmpty()){
            view.showErrorMessage(R.string.all_fields_required);
            return false;
        }
        return true;
    }

}
