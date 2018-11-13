package com.pycca.pycca.quotaincrease;

public class QuotaIncreaseActivityPresenter implements QuotaIncreaseActivityMVP.Presenter {

    private QuotaIncreaseActivityMVP.View view;
    private QuotaIncreaseActivityMVP.Model model;

    QuotaIncreaseActivityPresenter(QuotaIncreaseActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(QuotaIncreaseActivityMVP.View view) {
        this.view = view;
    }

}
