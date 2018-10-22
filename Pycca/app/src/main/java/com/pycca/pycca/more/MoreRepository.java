package com.pycca.pycca.more;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.More;

import java.util.ArrayList;

public class MoreRepository implements MoreFragmentRepository {

    private ArrayList<More> moreArrayList;

    @Override
    public ArrayList<More> getMoreArrayList() {
        moreArrayList = new ArrayList<>();
        moreArrayList.add(new More(R.color.colorOptionOne, R.drawable.ic_profile, R.string.profile));
        moreArrayList.add(new More(R.color.colorOptionTwo, R.drawable.ic_purchases, R.string.purchases));
        moreArrayList.add(new More(R.color.colorOptionThree, R.drawable.ic_logout, R.string.logout));
        return moreArrayList;
    }

}
