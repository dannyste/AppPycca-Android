package com.pycca.pycca.coupon;

import dagger.Module;
import dagger.Provides;

@Module
public class CouponFragmentModule {

    @Provides
    CouponFragmentMVP.Presenter provideCouponFragmenPresenter(CouponFragmentMVP.Model model){
        return new CouponFragmentPresenter(model);
    }

    @Provides
    CouponFragmentMVP.Model provideCouponFragmenModel(){
        return new CouponFragmentModel();
    }
}
