package com.pycca.pycca.quotacalculator;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.restApi.model.QuotaCalculatorResponse;
import com.pycca.pycca.util.SharedPreferencesManager;

import java.util.ArrayList;

public class QuotaCalculatorActivityModel implements QuotaCalculatorActivityMVP.Model {

    QuotaCalculatorActivityModel() {

    }

    @Override
    public User getUser(Context context) {
        return SharedPreferencesManager.getInstance(context).getUser();
    }

    @Override
    public ArrayList<QuotaCalculatorResponse> getQuotas(BaseResponse response) {
        ArrayList<QuotaCalculatorResponse> list = new ArrayList<>();
        Gson gson = new Gson();
        TypeToken<ArrayList<QuotaCalculatorResponse>> typeToken = new TypeToken<ArrayList<QuotaCalculatorResponse>>() {};
        ArrayList<QuotaCalculatorResponse> quotasResponse = gson.fromJson(gson.toJson(response.getData().getResult()), typeToken.getType());
//        for (QuotaCalculatorResponse quotaCalculatorResponse: quotasResponse){
//
//        }
        return quotasResponse;
    }
}
