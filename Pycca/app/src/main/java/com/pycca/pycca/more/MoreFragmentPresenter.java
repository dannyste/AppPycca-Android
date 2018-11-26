package com.pycca.pycca.more;

import com.pycca.pycca.pojo.More;
import com.pycca.pycca.pojo.Parameter;

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
    public void loadMoreArrayList(MoreFragment moreFragment) {
        ArrayList<More> moreArrayList = model.getMoreArrayList(moreFragment);
        view.updateDataRecyclerView(moreArrayList);
    }

    @Override
    public void firstItemClicked() {
        view.showAlertDialogClubPyccaPartner();
    }

    @Override
    public void secondItemClicked() {
        view.goToProfileActivity();
    }

    @Override
    public void thirdItemClicked() {
        view.showAlertDialogContactUs();
    }

    @Override
    public void fourthItemClicked() {
        view.goToOurShopsActivity();
    }

    @Override
    public String getPhoneNumber(MoreFragment moreFragment) {
        Parameter parameter = model.getParameter(moreFragment);
        return parameter.getPhoneNumber();
    }

    @Override
    public String getEmail(MoreFragment moreFragment) {
        Parameter parameter = model.getParameter(moreFragment);
        return parameter.getEmail();
    }

}
