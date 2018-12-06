package com.pycca.pycca.cardlocking;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.Card;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardLockingActivityPresenter implements CardLockingActivityMVP.Presenter, CardLockingActivityMVP.TaskListener {

    private CardLockingActivityMVP.View view;
    private CardLockingActivityMVP.Model model;

    CardLockingActivityPresenter(CardLockingActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(CardLockingActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadCardsArrayList(CardLockingActivity cardLockingActivity) {
        view.hideRootView();
        view.showLoadingAnimation();
        User user = model.getUser(cardLockingActivity);
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
    public void lockClicked() {
        view.showAlertDialogLock();
    }

    @Override
    public void lockPositiveButtonClicked(final CardLockingActivity cardLockingActivity) {
        if (validateForm()) {
            view.hideRootView();
            view.showLoadingAnimation();
            final String clubPyccaCardNumber = view.getClubPyccaCardNumber();
            String reason = view.getReason();
            int reasonCode = reason.equalsIgnoreCase(cardLockingActivity.getString(R.string.lost)) ? 4 : 5;
            final User user = model.getUser(cardLockingActivity);
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
            Call<BaseResponse> getCardsCall = endpointsApi.postCardLocking(clubPyccaCardNumber, user.getAccountNumber(), reasonCode, "");
            getCardsCall.enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    try {
                        if (response.isSuccessful()) {
                            BaseResponse baseResponse = response.body();
                            if (baseResponse.getStatus()) {
                                if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                    if (user.getClubPyccaCardNumber().equalsIgnoreCase(clubPyccaCardNumber)) {
                                        user.setClubPyccaCardLocked(true);
                                        model.setUser(cardLockingActivity, user, CardLockingActivityPresenter.this);
                                    }
                                    else {
                                        onSuccess(user);
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
        view.showConfirmationMessage();
    }

    @Override
    public void finishedFailureAnimation() {
        view.hideFailureAnimation();
        view.showRootView();
    }

    @Override
    public void errorTouchRetryClicked(CardLockingActivity cardLockingActivity) {
        view.hideErrorAnimation();
        loadCardsArrayList(cardLockingActivity);
    }

    @Override
    public void onSuccess(User user) {
        view.hideLoadingAnimation();
        view.showDoneAnimation();
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
