package com.pycca.pycca.more;

import com.pycca.pycca.pojo.More;

import java.util.ArrayList;

public class MoreFragmentModel implements MoreFragmentMVP.Model {

    private MoreFragmentRepository moreFragmentRepository;

    MoreFragmentModel(MoreFragmentRepository moreFragmentRepository) {
        this.moreFragmentRepository = moreFragmentRepository;
    }

    @Override
    public ArrayList<More> getMoreArrayList() {
        return moreFragmentRepository.getMoreArrayList();
    }

}
