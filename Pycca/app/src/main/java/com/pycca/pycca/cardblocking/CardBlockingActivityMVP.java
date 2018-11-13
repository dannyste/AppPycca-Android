package com.pycca.pycca.cardblocking;

public interface CardBlockingActivityMVP {

    interface View {

    }

    interface Presenter {

        void setView(CardBlockingActivityMVP.View view);

    }

    interface Model {

    }

}
