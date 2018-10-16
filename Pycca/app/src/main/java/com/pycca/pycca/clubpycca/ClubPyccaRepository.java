package com.pycca.pycca.clubpycca;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.ClubPycca;

import java.util.ArrayList;

public class ClubPyccaRepository implements ClubPyccaFragmentRepository {

    private ArrayList<ClubPycca> clubPyccaArrayList;

    @Override
    public ArrayList<ClubPycca> getClubPyccaArrayList() {
        clubPyccaArrayList = new ArrayList<>();
        clubPyccaArrayList.add(new ClubPycca(R.color.colorRed, R.drawable.ic_get_balance, R.string.get_balance));
        clubPyccaArrayList.add(new ClubPycca(R.color.colorGray, R.drawable.ic_account_status, R.string.account_status));
        clubPyccaArrayList.add(new ClubPycca(R.color.colorPrimary, R.drawable.ic_quota_increase, R.string.quota_increase));
        clubPyccaArrayList.add(new ClubPycca(R.color.colorRed, R.drawable.ic_quota_calculator, R.string.quota_calculator));
        clubPyccaArrayList.add(new ClubPycca(R.color.colorGray, R.drawable.ic_virtual_card, R.string.virtual_card));
        clubPyccaArrayList.add(new ClubPycca(R.color.colorPrimary, R.drawable.ic_card_blocking, R.string.card_blocking));
        return clubPyccaArrayList;
    }

}
