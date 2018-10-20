package com.pycca.pycca.restApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public EndpointsApi setConnectionRestApiServer(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiConstants.SERVER_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndpointsApi.class);
    }

}
