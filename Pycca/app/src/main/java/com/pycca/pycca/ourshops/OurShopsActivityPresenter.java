package com.pycca.pycca.ourshops;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShops;
import com.pycca.pycca.pojo.OurShopsDetails;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurShopsActivityPresenter implements OurShopsActivityMVP.Presenter, OurShopsActivityMVP.TaskListener {

    private OurShopsActivityMVP.View view;
    private OurShopsActivityMVP.Model model;

    OurShopsActivityPresenter(OurShopsActivityMVP.Model model) {
        this.model  = model;
    }

    @Override
    public void setView(OurShopsActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadOurShopsArrayList() {
        view.showLoadingAnimation();
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<BaseResponse> getOurShopsCall = endpointsApi.getOurShops();
        getOurShopsCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getStatus()) {
                            if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                ArrayList<OurShops> ourShopsArrayList = model.getOurShopsArrayList(baseResponse);
                                view.hideLoadingAnimation();
                                view.updateDataRecyclerView(ourShopsArrayList);
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

    @Override
    public void itemClicked(ArrayList<OurShopsDetails> ourShopsDetailsArrayList) {
        view.goToOurShopsDetailsActivity(ourShopsDetailsArrayList);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(int error) {
        view.showErrorAnimation();
    }

}
