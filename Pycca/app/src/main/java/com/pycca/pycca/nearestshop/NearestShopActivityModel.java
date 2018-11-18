package com.pycca.pycca.nearestshop;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pycca.pycca.pojo.OurShopsDetails;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.restApi.model.OurShopsResponse;

import java.util.ArrayList;

public class NearestShopActivityModel implements NearestShopActivityMVP.Model {

    NearestShopActivityModel() {

    }

    @Override
    public ArrayList<OurShopsDetails> getOurShopsDetailsArrayList(BaseResponse baseResponse) {
        Gson gson = new Gson();
        TypeToken<ArrayList<OurShopsResponse>> typeToken = new TypeToken<ArrayList<OurShopsResponse>>() {};
        ArrayList<OurShopsResponse> ourShopsResponseArrayList = gson.fromJson(gson.toJson(baseResponse.getData().getResult()), typeToken.getType());
        ArrayList<OurShopsDetails> ourShopsDetailsArrayList = new ArrayList<>();
        for (OurShopsResponse ourShopsResponse: ourShopsResponseArrayList) {
            OurShopsDetails ourShopsDetails = new OurShopsDetails();
            ourShopsDetails.setName(ourShopsResponse.getDescripcion());
            ourShopsDetails.setAddress(ourShopsResponse.getDireccion());
            ourShopsDetails.setOpeningHours(ourShopsResponse.getHorario_atencion());
            ourShopsDetails.setLatitude(ourShopsResponse.getLatitud());
            ourShopsDetails.setLongitude(ourShopsResponse.getLongitud());
            ourShopsDetailsArrayList.add(ourShopsDetails);
        }
        return ourShopsDetailsArrayList;
    }

}
