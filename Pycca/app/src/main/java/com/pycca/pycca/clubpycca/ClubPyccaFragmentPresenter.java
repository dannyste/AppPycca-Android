package com.pycca.pycca.clubpycca;

import com.pycca.pycca.pojo.ClubPycca;

import java.util.ArrayList;

public class ClubPyccaFragmentPresenter implements ClubPyccaFragmentMVP.Presenter {

    private ClubPyccaFragmentMVP.View view;
    private ClubPyccaFragmentMVP.Model model;

    public ClubPyccaFragmentPresenter(ClubPyccaFragmentMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ClubPyccaFragmentMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadClubPyccaArrayList() {
        ArrayList<ClubPycca> clubPyccaArrayList = model.getClubPyccaArrayList();
        view.updateDataRecyclerView(clubPyccaArrayList);
    }

    @Override
    public void clubPyccaFirstItemClicked() {
        view.goToAccountStatusActivity();
    }

    @Override
    public void clubPyccaSecondItemClicked() {
        view.goToQuotaIncreaseActivity();
    }

    @Override
    public void clubPyccaThirdItemClicked() {
        view.goToQuotaCalculatorActivity();
    }

    @Override
    public void clubPyccaFourthItemClicked() {
        view.goToVirtualCardActivity();
    }

    @Override
    public void clubPyccaFifthItemClicked() {
        view.goToCardBlockingActivity();
    }

}
