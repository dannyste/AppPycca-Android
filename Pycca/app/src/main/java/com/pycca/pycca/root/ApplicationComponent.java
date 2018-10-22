package com.pycca.pycca.root;

import com.pycca.pycca.clubpycca.ClubPyccaFragment;
import com.pycca.pycca.clubpycca.ClubPyccaFragmentModule;
import com.pycca.pycca.coupon.CouponFragment;
import com.pycca.pycca.coupon.CouponFragmentModule;
import com.pycca.pycca.forgotpassword.ForgotPasswordActivity;
import com.pycca.pycca.forgotpassword.ForgotPasswordActivityModule;
import com.pycca.pycca.home.HomeFragment;
import com.pycca.pycca.home.HomeFragmentModule;
import com.pycca.pycca.login.LoginActivity;
import com.pycca.pycca.login.LoginActivityModule;
import com.pycca.pycca.more.MoreFragment;
import com.pycca.pycca.more.MoreFragmentModule;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.multilogin.MultiLoginActivityModule;
import com.pycca.pycca.promotion.PromotionFragment;
import com.pycca.pycca.promotion.PromotionFragmentModule;
import com.pycca.pycca.signup.SignUpActivity;
import com.pycca.pycca.signup.SignUpActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, MultiLoginActivityModule.class, SignUpActivityModule.class, LoginActivityModule.class,
        HomeFragmentModule.class, ClubPyccaFragmentModule.class, MoreFragmentModule.class, PromotionFragmentModule.class,
        CouponFragmentModule.class, ForgotPasswordActivityModule.class})
public interface ApplicationComponent {

    void inject(MultiLoginActivity multiLoginActivity);

    void inject(SignUpActivity signUpActivity);

    void inject(LoginActivity loginActivity);

    void inject(HomeFragment homeFragment);

    void inject(ClubPyccaFragment clubPyccaFragment);

    void inject(MoreFragment moreFragment);

    void inject(PromotionFragment promotionFragment);

    void inject(CouponFragment couponFragment);

    void inject(ForgotPasswordActivity forgotPasswordActivity);

}
