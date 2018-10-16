package com.pycca.pycca.restApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pycca.pycca.restApi.model.BaseResponse;

import okhttp3.ResponseBody;
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

    /*public Gson buildGsonDeserializerResponse(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BaseResponse.class, new MediaDeserializer());
        return gsonBuilder.create();
    }*/

}
