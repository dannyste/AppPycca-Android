package com.pycca.pycca.nearestshop;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pycca.pycca.pojo.OurShopDetail;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.restApi.model.OurShopResponse;

import java.util.ArrayList;

public class NearestShopActivityModel implements NearestShopActivityMVP.Model {

    NearestShopActivityModel() {

    }

    @Override
    public ArrayList<OurShopDetail> getOurShopsDetailsArrayList(BaseResponse baseResponse) {
        Gson gson = new Gson();
        TypeToken<ArrayList<OurShopResponse>> typeToken = new TypeToken<ArrayList<OurShopResponse>>() {};
        ArrayList<OurShopResponse> ourShopResponseArrayList = gson.fromJson(gson.toJson(baseResponse.getData().getResult()), typeToken.getType());
        ArrayList<OurShopDetail> ourShopDetailArrayList = new ArrayList<>();
        for (OurShopResponse ourShopResponse : ourShopResponseArrayList) {
            OurShopDetail ourShopDetail = new OurShopDetail();
            ourShopDetail.setName(ourShopResponse.getDescripcion());
            ourShopDetail.setAddress(ourShopResponse.getDireccion());
            ourShopDetail.setOpeningHours(ourShopResponse.getHorario_atencion());
            ourShopDetail.setLatitude(ourShopResponse.getLatitud());
            ourShopDetail.setLongitude(ourShopResponse.getLongitud());
            ourShopDetailArrayList.add(ourShopDetail);
        }
        return ourShopDetailArrayList;
    }

}
