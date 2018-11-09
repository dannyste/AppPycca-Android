package com.pycca.pycca.ourshops;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pycca.pycca.pojo.OurShops;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

public class OurShopsActivityModel implements OurShopsActivityMVP.Model {

    OurShopsActivityModel() {

    }

    @Override
    public ArrayList<OurShops> getOurShopsArrayList(BaseResponse baseResponse) {
        Gson gson = new Gson();
        TypeToken<ArrayList<OurShops>> typeToken = new TypeToken<ArrayList<OurShops>>() {};
        return gson.fromJson(gson.toJson(baseResponse.getData().getResult()), typeToken.getType());
    }

}
