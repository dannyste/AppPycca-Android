package com.pycca.pycca.restApi;

import com.pycca.pycca.restApi.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndpointsApi {


    @GET(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_GET_CLIENT + RestApiConstants.ACTION_VALIDATE_CLIENT)
    Call<BaseResponse> getValidateClient(@Path("documentType") String documentType, @Path("documentNumber") String documentNumber, @Path("clubPyccaCardNumber") String clubPyccaCardNumber);

    @GET(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_GET_IMAGES_LIST + RestApiConstants.ACTION_PROMOTION)
    Call<BaseResponse> getPromotionsList();

    @GET(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_GET_IMAGES_LIST + RestApiConstants.ACTION_DIVISION)
    Call<BaseResponse> getDivisionsList();

}
