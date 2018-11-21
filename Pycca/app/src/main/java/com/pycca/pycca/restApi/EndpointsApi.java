package com.pycca.pycca.restApi;

import com.pycca.pycca.restApi.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EndpointsApi {

    @GET(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_GET_VALIDATE_CLIENT)
    Call<BaseResponse> getValidateClient(@Path("documentType") String documentType, @Path("identificationCard") String identificationCard, @Path("clubPyccaCardNumber") String clubPyccaCardNumber);

    @GET(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_GET_OUR_SHOPS)
    Call<BaseResponse> getOurShops();

    @GET(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_GET_BALANCE)
    Call<BaseResponse> getBalance(@Path("clubPyccaCardNumber") String clubPyccaCardNumber);

    @FormUrlEncoded
    @POST(RestApiConstants.SERVER_ROOT_URL + RestApiConstants.SERVER_URL_POST_CLUB_PYCCA_PARTNER)
    Call<BaseResponse> clubPyccaPartner(@Field("name") String name, @Field("last_name") String last_name, @Field("born_date") String born_date,
                                        @Field("identification") String identification, @Field("email") String email, @Field("phone") String phone,
                                        @Field("cell_phone") String cell_phone, @Field("address") String address);
}