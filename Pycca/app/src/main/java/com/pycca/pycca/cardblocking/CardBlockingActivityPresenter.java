package com.pycca.pycca.cardblocking;

public class CardBlockingActivityPresenter implements CardBlockingActivityMVP.Presenter {

    private CardBlockingActivityMVP.View view;
    private CardBlockingActivityMVP.Model model;

    CardBlockingActivityPresenter(CardBlockingActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(CardBlockingActivityMVP.View view) {
        this.view = view;
    }

}
