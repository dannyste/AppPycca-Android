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
        view.goToAccountStatusActivity();
    }

    @Override
    public void secondItemClicked() {
        view.goToQuotaIncreaseActivity();
    }

    @Override
    public void thirdItemClicked() {
        view.goToQuotaCalculatorActivity();
    }

    @Override
    public void fourthItemClicked() {
        view.goToVirtualCardActivity();
    }

    @Override
    public void fifthItemClicked() {
        view.goToCardBlockingActivity();
    }

}
