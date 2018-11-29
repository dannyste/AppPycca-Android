package com.pycca.pycca.signup;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivityPresenter implements SignUpActivityMVP.Presenter, SignUpActivityMVP.TaskListener {

    private SignUpActivityMVP.View view;
    private SignUpActivityMVP.Model model;

    SignUpActivityPresenter(SignUpActivityMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(SignUpActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void signUpClicked(final SignUpActivity signUpActivity) {
        if (validateForm()) {
            view.hideRootView();
            view.showLoadingAnimation();
            final String email = view.getEmail();
            final String password = view.getPassword();
            final boolean clubPyccaPartner = view.isClubPyccaPartner();
            final String identificationCard = view.getIdentificationCard();
            final String clubPyccaCardNumber = view.getClubPyccaCardNumber();
            if (clubPyccaPartner) {
                final RestApiAdapter restApiAdapter = new RestApiAdapter();
                EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
                Call<BaseResponse> getValidateClientCall = endpointsApi.getValidateClient("C", identificationCard, clubPyccaCardNumber);
                getValidateClientCall.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                BaseResponse baseResponse = response.body();
                                if (baseResponse.getStatus()) {
                                    if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                        model.firebaseCreateUserWithEmailAndPassword(signUpActivity, email, password, clubPyccaPartner, identificationCard, clubPyccaCardNumber, baseResponse, SignUpActivityPresenter.this);
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
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        onError(R.string.error_default);
                    }
                });
            }
            else {
                model.firebaseCreateUserWithEmailAndPassword(signUpActivity, email, password, clubPyccaPartner, identificationCard, clubPyccaCardNumber, null, SignUpActivityPresenter.this);
            }
        }
    }

    @Override
    public void finishedDoneAnimation() {
        view.goToHostActivity();
    }

    @Override
    public void finishedFailureAnimation() {
        view.hideFailureAnimation();
        view.showRootView();
    }

    @Override
    public void onSuccess(User user) {
        model.userUnsubscribeFromTopic(Constants.TOPIC_INVITED);
        model.userSubscribeToTopic(Constants.TOPIC_NATIVE);
        if (user.isClubPyccaPartner()) {
            model.userSubscribeToTopic(Constants.TOPIC_CLUB_PYCCA_PARTNER);
            model.userSubscribeToTopic(Constants.TOPIC_NATIVE_CLUB_PYCCA_PARTNER);
        }
        else {
            model.userSubscribeToTopic(Constants.TOPIC_NOT_CLUB_PYCCA_PARTNER);
            model.userSubscribeToTopic(Constants.TOPIC_NATIVE_NOT_CLUB_PYCCA_PARTNER);
        }
        view.hideLoadingAnimation();
        view.showDoneAnimation();
    }

    @Override
    public void onError(int error) {
        view.hideLoadingAnimation();
        view.showFailureAnimation();
        view.showErrorMessage(error);
    }

    private boolean validateForm() {
        String email = view.getEmail();
        String password = view.getPassword();
        boolean clubPyccaPartner = view.isClubPyccaPartner();
        String identificationCard = view.getIdentificationCard();
        String clubPyccaCardNumber = view.getClubPyccaCardNumber();
        if(email.isEmpty()) {
            view.showEmailRequired();
            return false;
        }
        else if (!Util.checkValidEmail(email)) {
            view.showInvalidEmail();
            return false;
        }
        else if (password.isEmpty()) {
            view.showPasswordRequired();
            return false;
        }
        else if (clubPyccaPartner) {
            if (identificationCard.isEmpty()) {
                view.showIdentificationCardRequired();
                return false;
            }
            else if (clubPyccaCardNumber.isEmpty()) {
                view.showClubPyccaCardNumberRequired();
                return false;
            }
        }
        return true;
    }
}
