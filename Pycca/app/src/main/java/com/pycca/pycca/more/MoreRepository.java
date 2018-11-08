package com.pycca.pycca.more;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.More;

import java.util.ArrayList;

public class MoreRepository implements MoreFragmentRepository {

    private ArrayList<More> moreArrayList;

    @Override
    public ArrayList<More> getMoreArrayList() {
        moreArrayList = new ArrayList<>();
        moreArrayList.add(new More(R.color.colorOptionOne, R.drawable.ic_club_pycca, R.string.club_pycca_partner));
        moreArrayList.add(new More(R.color.colorOptionTwo, R.drawable.ic_profile, R.string.profile));
        moreArrayList.add(new More(R.color.colorOptionThree, R.drawable.ic_contact_call, R.string.contact_call));
        moreArrayList.add(new More(R.color.colorOptionFour, R.drawable.ic_contact_email, R.string.contact_email));
        moreArrayList.add(new More(R.color.colorOptionFive, R.drawable.ic_nearest_shop, R.string.nearest_shop));
        moreArrayList.add(new More(R.color.colorOptionSix, R.drawable.ic_our_shop, R.string.our_shop));
        return moreArrayList;
    }

}
