package com.pycca.pycca.clubpycca;

import com.pycca.pycca.pojo.ClubPycca;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.SharedPreferencesManager;

import java.util.ArrayList;

public class ClubPyccaFragmentModel implements ClubPyccaFragmentMVP.Model {

    private ClubPyccaFragmentRepository clubPyccaFragmentRepository;

    ClubPyccaFragmentModel(ClubPyccaFragmentRepository clubPyccaFragmentRepository) {
        this.clubPyccaFragmentRepository = clubPyccaFragmentRepository;
    }

    @Override
    public ArrayList<ClubPycca> getClubPyccaArrayList() {
        return clubPyccaFragmentRepository.getClubPyccaArrayList();
    }

    @Override
    public User getUser(ClubPyccaFragment clubPyccaFragment) {
        return SharedPreferencesManager.getInstance(clubPyccaFragment.getActivity()).getUser();
    }

}
