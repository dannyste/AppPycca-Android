package com.pycca.pycca.coupon;

import dagger.Module;
import dagger.Provides;

@Module
public class CouponFragmentModule {
    @Provides
    public CouponFragmentMVP.Presenter provideCouponFragmenPresenter(CouponFragmentMVP.Model model){
        return new CouponFragmentPresenter(model);
    }

    @Provides
    public CouponFragmentMVP.Model provideCouponFragmenModel(){
        return new CouponFragmentModel();
    }
}
