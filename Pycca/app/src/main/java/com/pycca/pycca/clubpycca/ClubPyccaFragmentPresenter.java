package com.pycca.pycca.clubpycca;

import com.pycca.pycca.pojo.ClubPycca;
import com.pycca.pycca.pojo.User;

import java.util.ArrayList;

public class ClubPyccaFragmentPresenter implements ClubPyccaFragmentMVP.Presenter {

    private ClubPyccaFragmentMVP.View view;
    private ClubPyccaFragmentMVP.Model model;

    ClubPyccaFragmentPresenter(ClubPyccaFragmentMVP.Model model) {
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
    public void clubPyccaSecondItemClicked(ClubPyccaFragment clubPyccaFragment) {
        User user = model.getUser(clubPyccaFragment);
        if (user.isClubPyccaCardLocked()) {

        }
        else {
            view.goToQuotaIncreaseActivity();
        }
    }

    @Override
    public void clubPyccaThirdItemClicked() {
        view.goToQuotaCalculatorActivity();
    }

    @Override
    public void clubPyccaFourthItemClicked(ClubPyccaFragment clubPyccaFragment) {
        User user = model.getUser(clubPyccaFragment);
        if (user.isClubPyccaCardLocked()) {

        }
        else {
            view.goToVirtualCardActivity();
        }
    }

    @Override
    public void clubPyccaFifthItemClicked() {
        view.goToCardBlockingActivity();
    }

}
