package com.pycca.pycca.quotacalculator;

public interface QuotaCalculatorActivityMVP {

    interface View {

    }

    interface Presenter {

        void setView(QuotaCalculatorActivityMVP.View view);

    }

    interface Model {

    }

}
