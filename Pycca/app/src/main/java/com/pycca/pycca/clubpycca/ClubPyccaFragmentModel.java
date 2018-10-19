package com.pycca.pycca.clubpycca;

import com.pycca.pycca.pojo.ClubPycca;

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

}
