package com.pycca.pycca.cardblocking;

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
                                view.updateDataRadioButton(cardArrayList);
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
    public void blockClicked() {
        view.hideRootView();
        view.showLoadingAnimation();

    }

    @Override
    public void finishedDoneAnimation() {

    }

    @Override
    public void finishedFailureAnimation() {

    }

    @Override
    public void errorTouchRetryClicked(CardBlockingActivity cardBlockingActivity) {
        view.hideErrorAnimation();
        loadCardsArrayList(cardBlockingActivity);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(int error) {
        view.hideRootView();
        view.hideLoadingAnimation();
        view.showErrorAnimation();
    }

}
