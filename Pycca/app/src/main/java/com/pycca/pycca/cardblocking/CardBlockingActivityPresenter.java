package com.pycca.pycca.cardblocking;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.Card;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardBlockingActivityPresenter implements CardBlockingActivityMVP.Presenter, CardBlockingActivityMVP.TaskListener {

    private CardBlockingActivityMVP.View view;
    private CardBlockingActivityMVP.Model model;

    CardBlockingActivityPresenter(CardBlockingActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(CardBlockingActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadCardsArrayList(CardBlockingActivity cardBlockingActivity) {
        view.hideRootView();
        view.showLoadingAnimation();
        User user = model.getUser(cardBlockingActivity);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<BaseResponse> getCardsCall = endpointsApi.getCards(user.getAccountNumber(), user.getClubPyccaCardNumber());
        getCardsCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getStatus()) {
                            if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                ArrayList<Card> cardArrayList = model.getCardArrayList(baseResponse);
                                view.hideLoadingAnimation();
                                view.showRootView();
                                view.updateTextPrincipalCardRadioButton(cardArrayList);
                                if (cardArrayList.size() > 1) {
                                    view.showAdditionalCardsTextView();
                                    view.addAdditionalCardsRadioButton(cardArrayList);
                                }
                            }
                            else {
                                onError(R.string.error_default);
                            }
                        }
                        else {
                            onError(R.string.error_default);
                        }
                    }
                }
                catch (Exception exception) {
                    onError(R.string.error_default);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable throwable) {
                onError(R.string.error_default);
            }
        });
    }

    @Override
    public void reasonClicked() {
        view.showAlertDialogReason();
    }

    @Override
    public void reasonItemClicked(String reason) {
        view.setReason(reason);
    }

    @Override
    public void blockClicked() {
        view.showAlertDialogBlock();
    }

    @Override
    public void blockPositiveButtonClicked(final CardBlockingActivity cardBlockingActivity) {
        if (validateForm()) {
            view.hideRootView();
            view.showLoadingAnimation();
            final String clubPyccaCardNumber = view.getClubPyccaCardNumber();
            String reason = view.getReason();
            int reasonCode = reason.equalsIgnoreCase(cardBlockingActivity.getString(R.string.lost)) ? 4 : 5;
            final User user = model.getUser(cardBlockingActivity);
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
            Call<BaseResponse> getCardsCall = endpointsApi.postCardBlocking(clubPyccaCardNumber, user.getAccountNumber(), reasonCode, "");
            getCardsCall.enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    try {
                        if (response.isSuccessful()) {
                            BaseResponse baseResponse = response.body();
                            if (baseResponse.getStatus()) {
                                if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                    if (user.getClubPyccaCardNumber().equalsIgnoreCase(clubPyccaCardNumber)) {
                                        user.setClubPyccaPartner(false);
                                        user.setClubPyccaCardNumber("");
                                        user.setNamesClubPyccaPartner("");
                                        user.setSurnamesClubPyccaPartner("");
                                        user.setAccountNumber(0);
                                        user.setClientSince("");
                                        user.setModificationDate(new Date());
                                        model.setUser(cardBlockingActivity, user, CardBlockingActivityPresenter.this);
                                    }
                                    else {
                                        view.showConfirmationMessage();
                                    }
                                }
                                else {
                                    onError(R.string.error_default);
                                }
                            }
                            else {
                                onError(R.string.error_default);
                            }
                        }
                    } catch (Exception exception) {
                        onError(R.string.error_default);
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable throwable) {
                    onError(R.string.error_default);
                }
            });
        }
    }

    @Override
    public void finishedDoneAnimation() {

    }

    @Override
    public void finishedFailureAnimation() {
        view.hideFailureAnimation();
        view.showRootView();
    }

    @Override
    public void errorTouchRetryClicked(CardBlockingActivity cardBlockingActivity) {
        view.hideErrorAnimation();
        loadCardsArrayList(cardBlockingActivity);
    }

    @Override
    public void onSuccess(User user) {

    }

    @Override
    public void onError(int error) {
        view.hideRootView();
        view.hideLoadingAnimation();
        view.showErrorAnimation();
    }

    private boolean validateForm() {
        String reason = view.getReason();
        if (reason.isEmpty()) {
            view.showReasonRequired();
            return false;
        }
        return true;
    }

}
