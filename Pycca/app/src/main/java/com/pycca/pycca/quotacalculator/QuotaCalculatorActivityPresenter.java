package com.pycca.pycca.quotacalculator;

public class QuotaCalculatorActivityPresenter implements QuotaCalculatorActivityMVP.Presenter {

    private QuotaCalculatorActivityMVP.View view;
    private QuotaCalculatorActivityMVP.Model model;

    QuotaCalculatorActivityPresenter(QuotaCalculatorActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(QuotaCalculatorActivityMVP.View view) {
        this.view = view;
    }

}
