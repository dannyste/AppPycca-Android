package com.pycca.pycca.more;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.More;
import com.pycca.pycca.root.App;

import java.util.ArrayList;

import javax.inject.Inject;

public class MoreFragment extends Fragment implements MoreFragmentMVP.View {

    private static final String TAG = MoreFragment.class.getName();

    @Inject
    public MoreFragmentMVP.Presenter presenter;

    private RecyclerView rv_more;

    private ArrayList<More> moreArrayList ;
    private MoreFragmentAdapter moreFragmentAdapter;

    public MoreFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        //getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(MoreFragment.this);
        rv_more = view.findViewById(R.id.rv_more);
        initRecyclerView();
        return view;
    }

    @Override
    public void updateDataRecyclerView(ArrayList<More> moreArrayList) {
        this.moreArrayList.clear();
        this.moreArrayList.addAll(moreArrayList);
        moreFragmentAdapter.notifyDataSetChanged();
    }

    public void initRecyclerView() {
        moreArrayList = new ArrayList<>();
        moreFragmentAdapter = new MoreFragmentAdapter(getActivity(), moreArrayList);
        rv_more.setAdapter(moreFragmentAdapter);
        rv_more.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        rv_more.setItemAnimator(new DefaultItemAnimator());
        rv_more.setHasFixedSize(false);
        rv_more.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(MoreFragment.this);
        presenter.loadMoreArrayList();
    }

}
