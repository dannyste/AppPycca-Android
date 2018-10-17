package com.pycca.pycca.home;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pycca.pycca.pojo.Division;
import com.pycca.pycca.pojo.Promotion;
import com.pycca.pycca.restApi.RestApiConstants;
import com.pycca.pycca.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragmentModel implements HomeFragmentMVP.Model {

    @Override
    public ArrayList<Promotion> castPromotionList(Object objectList) {
        ArrayList<Promotion> promotionList = new ArrayList<>();
        try {
            JsonObject jsonObject = new Gson().toJsonTree(objectList).getAsJsonObject();
            JsonArray filesjson = jsonObject.getAsJsonArray("files");
            for (int i = 0; i < filesjson.size(); i++) {
                JsonObject jsonImageInfo = filesjson.get(i).getAsJsonObject();
                Promotion promotion = new Promotion(RestApiConstants.SERVER_ROOT_URL.concat(RestApiConstants.SERVER_URL_GET_IMAGE).concat(RestApiConstants.ACTION_PROMOTION).concat("/").concat(jsonImageInfo.get("name").getAsString()),
                        jsonImageInfo.get("date").getAsString());
                promotionList.add(promotion);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return promotionList;
    }

    @Override
    public ArrayList<Division> castDivisionList(Object objectList) {
        ArrayList<Division> divisionList = new ArrayList<>();
        try {
            JsonObject jsonObject = new Gson().toJsonTree(objectList).getAsJsonObject();
            JsonArray filesjson = jsonObject.getAsJsonArray("files");
            for (int i = 0; i < filesjson.size(); i++) {
                JsonObject jsonImageInfo = filesjson.get(i).getAsJsonObject();
                Division division = new Division(
                        RestApiConstants.SERVER_ROOT_URL.concat(RestApiConstants.SERVER_URL_GET_IMAGE).concat(RestApiConstants.ACTION_DIVISION).concat("/").concat(jsonImageInfo.get("name").getAsString()),
                        jsonImageInfo.get("date").getAsString(),
                        Util.getOrder(jsonImageInfo.get("name").getAsString()));
                divisionList.add(division);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Util.orderDivisionList(divisionList);
    }

}
