package com.pycca.pycca.host;

import android.view.MenuItem;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HostActivityPresenter implements HostActivityMVP.Presenter, HostActivityMVP.TaskListener {

    private HostActivityMVP.View view;
    private HostActivityMVP.Model model;

    HostActivityPresenter(HostActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(HostActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void navigationItemSelected(HostActivity hostActivity, MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.mi_club_pycca) {
            User user = model.getUser(hostActivity);
            if (!user.isClubPyccaPartner()) {
                view.showAlertDialog();
            }
            else {
                view.showFragment(menuItem);
            }
        }
        else {
            view.showFragment(menuItem);
        }
    }

    @Override
    public void validateClicked(final HostActivity hostActivity) {
        if (validateFields()) {
            view.showLoadingAnimation();
            String documentType = "C";
            final String identificationCard = view.getIdentificationCard();
            final String clubPyccaCardNumber = view.getClubPyccaCardNumber();
            final RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
            Call<BaseResponse> getValidateClientCall = endpointsApi.getValidateClient(documentType, identificationCard, clubPyccaCardNumber);
            getValidateClientCall.enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.isSuccessful()) {
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getStatus()) {
                            if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                model.setUser(hostActivity, identificationCard, clubPyccaCardNumber, baseResponse, HostActivityPresenter.this);
                            } else {
                                onError(R.string.error_default);
                            }
                        } else {
                            onError(R.string.error_default);
                        }
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    onError(R.string.error_default);
                }
            });
        }
    }

    public boolean validateFields() {
        String identificationCard = view.getIdentificationCard();
        String clubPyccaCardNumber = view.getClubPyccaCardNumber();
        if (identificationCard.isEmpty()) {
            view.showIdentificationCardRequired();
            return false;
        }
        else if (clubPyccaCardNumber.isEmpty()) {
            view.clubPyccaCardNumberRequired();
            return false;
        }
        return true;
    }

    @Override
    public void requestNowClicked() {
        view.goToClubPyccaPartnerActivity();
    }

    @Override
    public void finishedDoneAnimation(MenuItem menuItem) {
        view.hideAlertDialog();
        view.showFragment(menuItem);
    }

    @Override
    public void onSuccess() {
        view.showDoneAnimation();
    }

    @Override
    public void onError(int error) {
        view.hideLoadingAnimation();
        view.showErrorMessage(error);
    }

}
