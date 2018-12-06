package com.pycca.pycca.accountstatus;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pycca.pycca.pojo.AccountStatus;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.AccountStatusResponse;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.SharedPreferencesManager;

public class AccountStatusActivityModel implements AccountStatusActivityMVP.Model {
    @Override
    public User getUser(AccountStatusActivity activity) {
        return SharedPreferencesManager.getInstance(activity).getUser();
    }

    @Override
    public AccountStatus getBalance(BaseResponse baseResponse, String clubPyccaCardNumber) {
        Gson gson = new Gson();
        TypeToken<AccountStatusResponse> typeToken = new TypeToken<AccountStatusResponse>() {};
        AccountStatusResponse accountStatusResponse = gson.fromJson(gson.toJson(baseResponse.getData().getResult()), typeToken.getType());

        AccountStatus accountStatus = new AccountStatus();
        accountStatus.setAprovedQuota(accountStatusResponse.getCupo());
        accountStatus.setAvailableCredit(accountStatusResponse.getDisponibleCuenta());
        accountStatus.setClubPyccaCardNumber(clubPyccaCardNumber);
        accountStatus.setUsedCredit(accountStatusResponse.getCupo() - accountStatusResponse.getDisponibleCuenta());
        accountStatus.setPayUntil(accountStatusResponse.getFechaTopePago().replaceAll("(\\r|\\n|\\t)",""));
        accountStatus.setQuotaToPay(accountStatusResponse.getMinimoPagar());
        accountStatus.setOverdue(accountStatusResponse.isVencido());
        accountStatus.setCutDate(accountStatusResponse.getFechaUltimoCorte());

        return accountStatus;
    }
}
