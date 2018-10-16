package com.pycca.pycca.restApi;

import com.pycca.pycca.restApi.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointsApi {

    @GET(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_GET_IMAGES_LIST + RestApiConstants.ACTION_PROMOTION)
    Call<BaseResponse> getPromotionsList();

    @GET(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_GET_IMAGES_LIST + RestApiConstants.ACTION_DIVISION)
    Call<BaseResponse> getDivisionsList();

}
