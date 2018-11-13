package com.pycca.pycca.more;

import com.pycca.pycca.pojo.More;

import java.util.ArrayList;

public class MoreFragmentPresenter implements MoreFragmentMVP.Presenter {

    private MoreFragmentMVP.View view;
    private MoreFragmentMVP.Model model;

    MoreFragmentPresenter(MoreFragmentMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MoreFragmentMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadMoreArrayList() {
        ArrayList<More> moreArrayList = model.getMoreArrayList();
        view.updateDataRecyclerView(moreArrayList);
    }

    @Override
    public void firstItemClicked() {
        view.goToClubPyccaPartner();
    }

    @Override
    public void secondItemClicked() {
        view.goToProfileActivity();
    }

    @Override
    public void thirdItemClicked() {

    }

    @Override
    public void fourthItemClicked() {

    }

    @Override
    public void fifthItemClicked() {

    }

    @Override
    public void sixthItemClicked() {
        view.goToOurShopsActivity();
    }

}
