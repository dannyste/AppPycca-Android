package com.pycca.pycca.coupon;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pycca.pycca.pojo.Coupon;
import com.pycca.pycca.restApi.RestApiConstants;

import java.util.ArrayList;

public class CouponFragmentModel implements CouponFragmentMVP.Model {
    @Override
    public ArrayList<Coupon> castCouponList(Object objectList) {
        ArrayList<Coupon> couponList = new ArrayList<>();
        try {
            JsonObject jsonObject = new Gson().toJsonTree(objectList).getAsJsonObject();
            JsonArray filesjson = jsonObject.getAsJsonArray("files");
            for (int i = 0; i < filesjson.size(); i++) {
                JsonObject jsonImageInfo = filesjson.get(i).getAsJsonObject();
                Coupon coupon = new Coupon(RestApiConstants.SERVER_ROOT_URL.concat(RestApiConstants.SERVER_URL_GET_IMAGE).concat(RestApiConstants.ACTION_PROMOTION).concat("/").concat(jsonImageInfo.get("name").getAsString()),
                        jsonImageInfo.get("date").getAsString());
                couponList.add(coupon);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return couponList;
    }
}
