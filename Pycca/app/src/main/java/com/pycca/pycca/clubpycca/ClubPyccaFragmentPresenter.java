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
    public void firstItemClicked() {
        view.goToBalanceActivity();
    }

    @Override
    public void secondItemClicked() {
        view.goToAccountStatusActivity();
    }

    @Override
    public void thirdItemClicked() {
        view.goToQuotaIncreaseActivity();
    }

    @Override
    public void fourthItemClicked() {
        view.goToQuotaCalculatorActivity();
    }

    @Override
    public void fifthItemClicked() {
        view.goToVirtualCardActivity();
    }

    @Override
    public void sixthItemClicked() {
        view.goToCardBlockingActivity();
    }

}
