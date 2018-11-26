package com.pycca.pycca.quotaincrease;

import android.content.Context;

import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.SharedPreferencesManager;

public class QuotaIncreaseActivityModel implements QuotaIncreaseActivityMVP.Model {

    QuotaIncreaseActivityModel() {

    }

    @Override
    public User getUser(Context context) {
        return SharedPreferencesManager.getInstance(context).getUser();
    }
}
