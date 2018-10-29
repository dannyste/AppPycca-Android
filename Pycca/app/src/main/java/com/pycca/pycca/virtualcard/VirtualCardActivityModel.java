package com.pycca.pycca.virtualcard;

import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.SharedPreferencesManager;

public class VirtualCardActivityModel implements VirtualCardActivityMVP.Model {

    VirtualCardActivityModel() {

    }

    @Override
    public User getUser(VirtualCardActivity virtualCardActivity) {
        return SharedPreferencesManager.getInstance(virtualCardActivity).getUser();
    }

}
