package com.pycca.pycca.restApi;

import com.pycca.pycca.restApi.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndpointsApi {

    @GET(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_GET_VALIDATE_CLIENT)
    Call<BaseResponse> getValidateClient(@Path("documentType") String documentType, @Path("identificationCard") String identificationCard, @Path("clubPyccaCardNumber") String clubPyccaCardNumber);

}
