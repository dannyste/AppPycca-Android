package com.pycca.pycca.coupon;

import android.content.Context;
import android.view.View;

import com.pycca.pycca.pojo.CouponImageResource;
import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;

public interface CouponFragmentMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<CouponImageResource> coupons);

        Context getAppContext();

        void showMessage(int errorCode);

        void goToPictureActivity(CouponImageResource coupon, android.view.View view);
    }

    interface Presenter {

        void setView(View view);

        void loadCoupons();

    }

    interface Model {

        void getContentImages(CouponFragmentMVP.TaskListener listener);

    }

    interface TaskListener {

        void onSuccess(ArrayList<CouponImageResource> list);
    }
}
