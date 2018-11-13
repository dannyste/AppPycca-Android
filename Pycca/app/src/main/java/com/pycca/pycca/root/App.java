package com.pycca.pycca.root;

import android.app.Application;

import com.pycca.pycca.buy.BuyFragmentModule;
import com.pycca.pycca.cardblocking.CardBlockingActivityModule;
import com.pycca.pycca.clubpycca.ClubPyccaFragmentModule;
import com.pycca.pycca.clubpyccapartner.ClubPyccaPartnerActivityModule;
import com.pycca.pycca.coupon.CouponFragmentModule;
import com.pycca.pycca.forgotpassword.ForgotPasswordActivityModule;
import com.pycca.pycca.home.HomeFragmentModule;
import com.pycca.pycca.host.HostActivityModule;
import com.pycca.pycca.login.LoginActivityModule;
import com.pycca.pycca.more.MoreFragmentModule;
import com.pycca.pycca.multilogin.MultiLoginActivityModule;
import com.pycca.pycca.ourshops.OurShopsActivityModule;
import com.pycca.pycca.ourshopsdetails.OurShopsDetailsActivityModule;
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
                .quotaIncreaseActivityModule(new QuotaIncreaseActivityModule())
                .quotaCalculatorActivityModule(new QuotaCalculatorActivityModule())
                .virtualCardActivityModule(new VirtualCardActivityModule())
                .cardBlockingActivityModule(new CardBlockingActivityModule())
                .buyFragmentModule(new BuyFragmentModule())
                .moreFragmentModule(new MoreFragmentModule())
                .clubPyccaPartnerActivityModule(new ClubPyccaPartnerActivityModule())
                .profileActivityModule(new ProfileActivityModule())
                .ourShopsActivityModule(new OurShopsActivityModule())
                .ourShopsDetailsActivityModule(new OurShopsDetailsActivityModule())
                .pictureActivityModule(new PictureActivityModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
