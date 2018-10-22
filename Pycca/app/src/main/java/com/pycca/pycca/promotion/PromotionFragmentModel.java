package com.pycca.pycca.promotion;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pycca.pycca.pojo.Promotion;
import com.pycca.pycca.restApi.RestApiConstants;

import java.util.ArrayList;

public class PromotionFragmentModel implements PromotionFragmentMVP.Model {
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
}
