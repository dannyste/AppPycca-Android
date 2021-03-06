package com.pycca.pycca.more;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.More;
import com.pycca.pycca.pojo.Parameter;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.SharedPreferencesManager;

import java.util.ArrayList;

public class MoreFragmentModel implements MoreFragmentMVP.Model {

    private MoreFragmentRepository moreFragmentRepository;

    MoreFragmentModel(MoreFragmentRepository moreFragmentRepository) {
        this.moreFragmentRepository = moreFragmentRepository;
    }

    @Override
    public ArrayList<More> getMoreArrayList(MoreFragment moreFragment) {
        ArrayList<More> moreArrayList = moreFragmentRepository.getMoreArrayList();
        User user = SharedPreferencesManager.getInstance(moreFragment.getActivity()).getUser();
        if (user.isClubPyccaPartner()) {
            moreArrayList.remove(0);
            moreArrayList.get(0).setColor(R.color.colorOptionOne);
            moreArrayList.get(1).setColor(R.color.colorOptionTwo);
            moreArrayList.get(2).setColor(R.color.colorOptionThree);
        }
        return moreArrayList;
    }

    @Override
    public Parameter getParameter(MoreFragment moreFragment) {
        return SharedPreferencesManager.getInstance(moreFragment.getActivity()).getParameter();
    }

}
