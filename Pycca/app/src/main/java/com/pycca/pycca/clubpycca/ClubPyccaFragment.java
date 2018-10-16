package com.pycca.pycca.clubpycca;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pycca.pycca.R;
import com.pycca.pycca.home.HomeFragment;
import com.pycca.pycca.pojo.ClubPycca;

import javax.inject.Inject;

public class ClubPyccaFragment extends Fragment implements ClubPyccaFragmentMVP.View {

    private static final String TAG = HomeFragment.class.getName();

    @Inject
    public ClubPyccaFragmentMVP.Presenter presenter;

    public ClubPyccaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_pycca, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(ClubPyccaFragment.this);
    }
}
