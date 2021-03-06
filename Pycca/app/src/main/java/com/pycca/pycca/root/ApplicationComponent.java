package com.pycca.pycca.root;

import com.pycca.pycca.accountstatus.AccountStatusActivity;
import com.pycca.pycca.accountstatus.AccountStatusActivityModule;
import com.pycca.pycca.buy.BuyFragment;
import com.pycca.pycca.buy.BuyFragmentModule;
import com.pycca.pycca.cardlocking.CardLockingActivity;
import com.pycca.pycca.cardlocking.CardLockingActivityModule;
import com.pycca.pycca.clubpycca.ClubPyccaFragment;
import com.pycca.pycca.clubpycca.ClubPyccaFragmentModule;
import com.pycca.pycca.clubpyccapartner.ClubPyccaPartnerActivity;
import com.pycca.pycca.clubpyccapartner.ClubPyccaPartnerActivityModule;
import com.pycca.pycca.coupon.CouponFragment;
import com.pycca.pycca.coupon.CouponFragmentModule;
import com.pycca.pycca.forgotpassword.ForgotPasswordActivity;
import com.pycca.pycca.forgotpassword.ForgotPasswordActivityModule;
import com.pycca.pycca.home.HomeFragment;
import com.pycca.pycca.home.HomeFragmentModule;
import com.pycca.pycca.host.HostActivity;
import com.pycca.pycca.host.HostActivityModule;
import com.pycca.pycca.login.LoginActivity;
import com.pycca.pycca.login.LoginActivityModule;
import com.pycca.pycca.more.MoreFragment;
import com.pycca.pycca.more.MoreFragmentModule;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.multilogin.MultiLoginActivityModule;
import com.pycca.pycca.nearestshop.NearestShopActivity;
import com.pycca.pycca.nearestshop.NearestShopActivityModule;
import com.pycca.pycca.ourshop.OurShopActivity;
import com.pycca.pycca.ourshop.OurShopActivityModule;
import com.pycca.pycca.ourshopdetail.OurShopDetailActivity;
import com.pycca.pycca.ourshopdetail.OurShopDetailActivityModule;
import com.pycca.pycca.picture.PictureActivity;
import com.pycca.pycca.picture.PictureActivityModule;
import com.pycca.pycca.profile.ProfileActivity;
import com.pycca.pycca.profile.ProfileActivityModule;
import com.pycca.pycca.quotacalculator.QuotaCalculatorActivity;
import com.pycca.pycca.quotacalculator.QuotaCalculatorActivityModule;
import com.pycca.pycca.quotaincrease.QuotaIncreaseActivity;
import com.pycca.pycca.quotaincrease.QuotaIncreaseActivityModule;
import com.pycca.pycca.signup.SignUpActivity;
import com.pycca.pycca.signup.SignUpActivityModule;
import com.pycca.pycca.splash.SplashActivity;
import com.pycca.pycca.splash.SplashActivityModule;
import com.pycca.pycca.virtualcard.VirtualCardActivity;
import com.pycca.pycca.virtualcard.VirtualCardActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
                            ApplicationModule.class,
                            SplashActivityModule.class,
                            MultiLoginActivityModule.class,
                            SignUpActivityModule.class,
                            LoginActivityModule.class,
                            ForgotPasswordActivityModule.class,
                            HostActivityModule.class,
                            HomeFragmentModule.class,
                            CouponFragmentModule.class,
                            ClubPyccaFragmentModule.class,
                            AccountStatusActivityModule.class,
                            QuotaIncreaseActivityModule.class,
                            QuotaCalculatorActivityModule.class,
                            VirtualCardActivityModule.class,
                            CardLockingActivityModule.class,
                            BuyFragmentModule.class,
                            MoreFragmentModule.class,
                            ClubPyccaPartnerActivityModule.class,
                            ProfileActivityModule.class,
                            NearestShopActivityModule.class,
                            OurShopActivityModule.class,
                            OurShopDetailActivityModule.class,
                            PictureActivityModule.class
                     })
public interface ApplicationComponent {

    void inject(SplashActivity splashActivity);

    void inject(MultiLoginActivity multiLoginActivity);

    void inject(SignUpActivity signUpActivity);

    void inject(LoginActivity loginActivity);

    void inject(ForgotPasswordActivity forgotPasswordActivity);

    void inject(HostActivity hostActivity);

    void inject(HomeFragment homeFragment);

    void inject(CouponFragment couponFragment);

    void inject(ClubPyccaFragment clubPyccaFragment);

    void inject(QuotaIncreaseActivity quotaIncreaseActivity);

    void inject(QuotaCalculatorActivity quotaCalculatorActivity);

    void inject(VirtualCardActivity virtualCardActivity);

    void inject(CardLockingActivity cardLockingActivity);

    void inject(BuyFragment buyFragment);

    void inject(MoreFragment moreFragment);

    void inject(ClubPyccaPartnerActivity clubPyccaPartnerActivity);

    void inject(ProfileActivity profileActivity);

    void inject(NearestShopActivity nearestShopActivity);

    void inject(OurShopActivity ourShopActivity);

    void inject(OurShopDetailActivity ourShopDetailActivity);

    void inject(PictureActivity pictureActivity);

    void inject(AccountStatusActivity accountStatusActivity);

}
