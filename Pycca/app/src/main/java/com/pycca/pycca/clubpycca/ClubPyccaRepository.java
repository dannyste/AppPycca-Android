package com.pycca.pycca.clubpycca;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.ClubPycca;

import java.util.ArrayList;

public class ClubPyccaRepository implements ClubPyccaFragmentRepository {

    private ArrayList<ClubPycca> clubPyccaArrayList;

    @Override
    public ArrayList<ClubPycca> getClubPyccaArrayList() {
        clubPyccaArrayList = new ArrayList<>();
        clubPyccaArrayList.add(new ClubPycca(R.color.colorOptionTwo, R.drawable.ic_account_status, R.string.account_status));
        clubPyccaArrayList.add(new ClubPycca(R.color.colorOptionThree, R.drawable.ic_quota_increase, R.string.quota_increase));
        clubPyccaArrayList.add(new ClubPycca(R.color.colorOptionFour, R.drawable.ic_quota_calculator, R.string.quota_calculator));
        clubPyccaArrayList.add(new ClubPycca(R.color.colorOptionFive, R.drawable.ic_virtual_card, R.string.virtual_card));
        clubPyccaArrayList.add(new ClubPycca(R.color.colorOptionSix, R.drawable.ic_card_locking, R.string.card_locking));
        return clubPyccaArrayList;
    }

}
