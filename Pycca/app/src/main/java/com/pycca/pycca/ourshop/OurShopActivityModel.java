package com.pycca.pycca.ourshop;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pycca.pycca.pojo.OurShop;
import com.pycca.pycca.pojo.OurShopDetail;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.restApi.model.OurShopResponse;

import java.util.ArrayList;

public class OurShopActivityModel implements OurShopActivityMVP.Model {

    OurShopActivityModel() {

    }

    @Override
    public ArrayList<OurShop> getOurShopsArrayList(BaseResponse baseResponse) {
        Gson gson = new Gson();
        TypeToken<ArrayList<OurShopResponse>> typeToken = new TypeToken<ArrayList<OurShopResponse>>() {};
        ArrayList<OurShopResponse> ourShopResponseArrayList = gson.fromJson(gson.toJson(baseResponse.getData().getResult()), typeToken.getType());
        ArrayList<OurShop> ourShopArrayList = new ArrayList<>();
        for (OurShopResponse ourShopResponse : ourShopResponseArrayList) {
            OurShop ourShop = new OurShop();
            ourShop.setName(ourShopResponse.getCiudad());
            OurShopDetail ourShopDetail = new OurShopDetail();
            ourShopDetail.setName(ourShopResponse.getDescripcion());
            ourShopDetail.setAddress(ourShopResponse.getDireccion());
            ourShopDetail.setOpeningHours(ourShopResponse.getHorario_atencion());
            ourShopDetail.setLatitude(ourShopResponse.getLatitud());
            ourShopDetail.setLongitude(ourShopResponse.getLongitud());
            int position = ourShopsArrayListContainsName(ourShopArrayList, ourShopResponse.getCiudad());
            if (position < 0) {
                ourShop.setOurShopDetailArrayList(new ArrayList<OurShopDetail>());
                ourShop.getOurShopDetailArrayList().add(ourShopDetail);
                ourShopArrayList.add(ourShop);
            }
            else {
                ourShopArrayList.get(position).getOurShopDetailArrayList().add(ourShopDetail);
            }
        }
        return ourShopArrayList;
    }

    private int ourShopsArrayListContainsName(ArrayList<OurShop> ourShopArrayList, String name) {
        for (int i = 0; i < ourShopArrayList.size(); i++) {
            if (ourShopArrayList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

}
