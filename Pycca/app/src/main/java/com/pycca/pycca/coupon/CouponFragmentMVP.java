package com.pycca.pycca.coupon;

import android.content.Context;

import com.pycca.pycca.pojo.Coupon;

import java.util.ArrayList;

public interface CouponFragmentMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<Coupon> coupons);

        Context getAppContext();

    }

    interface Presenter {

        void setView(View view);

        void loadCoupons(final android.view.View view1);

    }

    interface Model {

        ArrayList<Coupon> castCouponList(Object objectList);

    }
}
