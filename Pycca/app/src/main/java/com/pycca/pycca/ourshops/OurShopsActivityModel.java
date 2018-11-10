package com.pycca.pycca.ourshops;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pycca.pycca.pojo.OurShops;
import com.pycca.pycca.pojo.OurShopsDetails;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.restApi.model.OurShopsResponse;

import java.util.ArrayList;

public class OurShopsActivityModel implements OurShopsActivityMVP.Model {

    OurShopsActivityModel() {

    }

    @Override
    public ArrayList<OurShops> getOurShopsArrayList(BaseResponse baseResponse) {
        Gson gson = new Gson();
        TypeToken<ArrayList<OurShopsResponse>> typeToken = new TypeToken<ArrayList<OurShopsResponse>>() {};
        ArrayList<OurShopsResponse> ourShopsResponseArrayList = gson.fromJson(gson.toJson(baseResponse.getData().getResult()), typeToken.getType());
        ArrayList<OurShops> ourShopsArrayList = new ArrayList<>();
        for (OurShopsResponse ourShopsResponse: ourShopsResponseArrayList) {
            OurShops ourShops = new OurShops();
            ourShops.setName(ourShopsResponse.getCiudad());
            OurShopsDetails ourShopsDetails = new OurShopsDetails();
            ourShopsDetails.setName(ourShopsResponse.getDescripcion());
            ourShopsDetails.setAddress(ourShopsResponse.getDireccion());
            ourShopsDetails.setOpeningHours(ourShopsResponse.getHorario_atencion());
            ourShopsDetails.setLatitude(ourShopsResponse.getLatitud());
            ourShopsDetails.setLongitude(ourShopsResponse.getLongitud());
            int position = ourShopsArrayListContainsName(ourShopsArrayList, ourShopsResponse.getCiudad());
            if (position < 0) {
                ourShops.setOurShopsDetailsArrayList(new ArrayList<OurShopsDetails>());
                ourShops.getOurShopsDetailsArrayList().add(ourShopsDetails);
                ourShopsArrayList.add(ourShops);
            }
            else {
                ourShopsArrayList.get(position).getOurShopsDetailsArrayList().add(ourShopsDetails);
            }
        }
        return ourShopsArrayList;
    }

    private int ourShopsArrayListContainsName(ArrayList<OurShops> ourShopsArrayList, String name) {
        for (int i = 0; i < ourShopsArrayList.size(); i++) {
            if (ourShopsArrayList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

}
