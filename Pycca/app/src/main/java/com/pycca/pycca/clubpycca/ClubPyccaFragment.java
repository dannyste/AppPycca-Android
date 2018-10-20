package com.pycca.pycca.clubpycca;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.ClubPycca;
import com.pycca.pycca.root.App;

import java.util.ArrayList;

import javax.inject.Inject;

public class ClubPyccaFragment extends Fragment implements ClubPyccaFragmentMVP.View {

    private static final String TAG = ClubPyccaFragment.class.getName();

    @Inject
    public ClubPyccaFragmentMVP.Presenter presenter;

    private RecyclerView rv_club_pycca;

    private ArrayList<ClubPycca> clubPyccaArrayList ;
    private ClubPyccaFragmentAdapter clubPyccaFragmentAdapter;

    public ClubPyccaFragment() {

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_pycca, container, false);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(ClubPyccaFragment.this);
        Toolbar toolbar = view.findViewById(R.id.action_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        rv_club_pycca = view.findViewById(R.id.rv_club_pycca);
        initRecyclerView();
        return view;
    }

    @Override
    public void updateDataRecyclerView(ArrayList<ClubPycca> clubPyccaArrayList) {
        this.clubPyccaArrayList.clear();
        this.clubPyccaArrayList.addAll(clubPyccaArrayList);
        clubPyccaFragmentAdapter.notifyDataSetChanged();
    }

    public void initRecyclerView() {
        clubPyccaArrayList = new ArrayList<>();
        clubPyccaFragmentAdapter = new ClubPyccaFragmentAdapter(getActivity(), clubPyccaArrayList);
        rv_club_pycca.setAdapter(clubPyccaFragmentAdapter);
        rv_club_pycca.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        rv_club_pycca.setItemAnimator(new DefaultItemAnimator());
        rv_club_pycca.setHasFixedSize(false);
        rv_club_pycca.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(ClubPyccaFragment.this);
        presenter.loadClubPyccaArrayList();
    }

}
