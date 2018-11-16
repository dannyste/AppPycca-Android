package com.pycca.pycca.balance;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pycca.pycca.pojo.Balance;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BalanceResponse;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.SharedPreferencesManager;

import java.util.ArrayList;

public class BalanceActivityModel implements BalanceActivityMVP.Model {
    @Override
    public User getUser(BalanceActivity activity) {
        return SharedPreferencesManager.getInstance(activity).getUser();
    }

    @Override
    public Balance getBalance(BaseResponse baseResponse, String clubPyccaCardNumber) {
        Gson gson = new Gson();
        TypeToken<BalanceResponse> typeToken = new TypeToken<BalanceResponse>() {};
        BalanceResponse balanceResponse = gson.fromJson(gson.toJson(baseResponse.getData().getResult()), typeToken.getType());

        Balance balance = new Balance();
        balance.setAprovedQuota(balanceResponse.getCupo());
        balance.setAvailableCredit(balanceResponse.getDisponibleCuenta());
        balance.setClubPyccaCardNumber(clubPyccaCardNumber);
        balance.setUsedCredit(balanceResponse.getCupo() - balanceResponse.getDisponibleCuenta());
        balance.setPayUntil(balanceResponse.getFechaTopePago().replaceAll("(\\r|\\n|\\t)",""));

        return balance;
    }
}
