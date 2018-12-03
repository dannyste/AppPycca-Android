package com.pycca.pycca.cardblocking;

import com.pycca.pycca.pojo.Card;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.SharedPreferencesManager;

import java.util.ArrayList;

public class CardBlockingActivityModel implements CardBlockingActivityMVP.Model {

    CardBlockingActivityModel() {

    }

    @Override
    public User getUser(CardBlockingActivity cardBlockingActivity) {
        return SharedPreferencesManager.getInstance(cardBlockingActivity).getUser();
    }

    @Override
    public ArrayList<Card> getCardArrayList(BaseResponse baseResponse) {
        return null;
    }

}
