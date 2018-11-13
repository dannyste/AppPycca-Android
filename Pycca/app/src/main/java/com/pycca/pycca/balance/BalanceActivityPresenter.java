package com.pycca.pycca.balance;

import com.pycca.pycca.signup.SignUpActivityMVP;
import com.pycca.pycca.util.Util;

public class BalanceActivityPresenter implements BalanceActivityMVP.Presenter, BalanceActivityMVP.TaskListener {

    private BalanceActivityMVP.View view;
    private BalanceActivityMVP.Model model;

    BalanceActivityPresenter(BalanceActivityMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(BalanceActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        view.showLoadingAnimation();
        model.getBalance(this);
    }

    @Override
    public void finishedDoneAnimation() {
        //view.goToLoginActivity();
    }

    @Override
    public void onSuccess() {
        view.showDoneAnimation();
        //view.setData();
    }

    @Override
    public void onError(int errorCode) {
        view.hideLoadingAnimation();
        view.showMessage(errorCode);
        view.finishActivity();
    }
}
