package com.pycca.pycca.clubpyccapartner;

import com.pycca.pycca.R;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubPyccaPartnerActivityPresenter implements ClubPyccaPartnerActivityMVP.Presenter {

    private ClubPyccaPartnerActivityMVP.View view;
    private ClubPyccaPartnerActivityMVP.Model model;

    ClubPyccaPartnerActivityPresenter(ClubPyccaPartnerActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ClubPyccaPartnerActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void sendRequestClubPyccaPartner() {
        if(isFormValid()){
            view.showLoadingAnimation();

            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
            Call<BaseResponse> getBalanceCall = endpointsApi.clubPyccaPartner(view.getName(), view.getLastName(), view.getBornDate(), view.getIdentification(), view.getEmail(), view.getPhoneNumber(), view.getCellPhoneNumber(), view.getAddress());
            getBalanceCall.enqueue(new Callback<BaseResponse>() {
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
        if (view.getName().isEmpty() || view.getLastName().isEmpty()
                || view.getBornDate().isEmpty() || view.getEmail().isEmpty()
                || view.getIdentification().isEmpty() || view.getPhoneNumber().isEmpty()
                || view.getCellPhoneNumber().isEmpty() || view.getAddress().isEmpty()){
            view.showErrorMessage(R.string.all_fields_required);
            return false;
        }else if(!Util.checkValidEmail(view.getEmail())){
            view.showErrorMessage(R.string.invalid_email);
            return false;
        }
        return true;
    }

}
