package com.pycca.pycca.root;

import android.app.Application;



import com.pycca.pycca.accountstatus.AccountStatusActivity;
import com.pycca.pycca.accountstatus.AccountStatusActivityModule;
import com.pycca.pycca.buy.BuyFragmentModule;
import com.pycca.pycca.cardlocking.CardLockingActivityModule;
import com.pycca.pycca.clubpycca.ClubPyccaFragmentModule;
import com.pycca.pycca.clubpyccapartner.ClubPyccaPartnerActivityModule;
import com.pycca.pycca.coupon.CouponFragmentModule;
import com.pycca.pycca.forgotpassword.ForgotPasswordActivityModule;
import com.pycca.pycca.home.HomeFragmentModule;
import com.pycca.pycca.host.HostActivityModule;
import com.pycca.pycca.login.LoginActivityModule;
import com.pycca.pycca.more.MoreFragmentModule;
import com.pycca.pycca.multilogin.MultiLoginActivityModule;
import com.pycca.pycca.nearestshop.NearestShopActivityModule;
import com.pycca.pycca.ourshop.OurShopActivityModule;
import com.pycca.pycca.ourshopdetail.OurShopDetailActivityModule;
import com.pycca.pycca.picture.PictureActivityModule;
import com.pycca.pycca.profile.ProfileActivityModule;
import com.pycca.pycca.quotacalculator.QuotaCalculatorActivityModule;
import com.pycca.pycca.quotaincrease.QuotaIncreaseActivityModule;
import com.pycca.pycca.signup.SignUpActivityModule;
import com.pycca.pycca.splash.SplashActivityModule;
import com.pycca.pycca.virtualcard.VirtualCardActivityModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .splashActivityModule(new SplashActivityModule())
                .multiLoginActivityModule(new MultiLoginActivityModule())
                .signUpActivityModule(new SignUpActivityModule())
                .loginActivityModule(new LoginActivityModule())
                .forgotPasswordActivityModule(new ForgotPasswordActivityModule())
                .hostActivityModule(new HostActivityModule())
                .homeFragmentModule(new HomeFragmentModule())
                .couponFragmentModule(new CouponFragmentModule())
                .clubPyccaFragmentModule(new ClubPyccaFragmentModule())
                .accountStatusActivityModule(new AccountStatusActivityModule())
                .quotaIncreaseActivityModule(new QuotaIncreaseActivityModule())
                .quotaCalculatorActivityModule(new QuotaCalculatorActivityModule())
                .virtualCardActivityModule(new VirtualCardActivityModule())
                .cardLockingActivityModule(new CardLockingActivityModule())
                .buyFragmentModule(new BuyFragmentModule())
                .moreFragmentModule(new MoreFragmentModule())
                .clubPyccaPartnerActivityModule(new ClubPyccaPartnerActivityModule())
                .profileActivityModule(new ProfileActivityModule())
                .nearestShopActivityModule(new NearestShopActivityModule())
                .ourShopActivityModule(new OurShopActivityModule())
                .ourShopDetailActivityModule(new OurShopDetailActivityModule())
                .pictureActivityModule(new PictureActivityModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
