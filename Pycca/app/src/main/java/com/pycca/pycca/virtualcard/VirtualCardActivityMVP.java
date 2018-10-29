package com.pycca.pycca.virtualcard;

import com.pycca.pycca.pojo.User;

public interface VirtualCardActivityMVP {

    interface View {

        void setClubPyccaCardNumber(String clubPyccaCardNumber);

        void setName(String name);

        void setClientSince(String clientSince);

    }

    interface Presenter {

        void setView(VirtualCardActivityMVP.View view);

        void getCurrentUser(VirtualCardActivity virtualCardActivity);

    }

    interface Model {

        User getUser(VirtualCardActivity virtualCardActivity);

    }

}
