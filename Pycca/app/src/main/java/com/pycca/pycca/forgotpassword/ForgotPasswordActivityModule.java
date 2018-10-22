package com.pycca.pycca.forgotpassword;

import com.pycca.pycca.login.LoginActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ForgotPasswordActivityModule {

    @Provides
    public ForgotPasswordActivityMVP.Presenter provideForgotPasswordActivityPresenter(ForgotPasswordActivityMVP.Model model) {
        return new ForgotPasswordActivityPresenter(model);
    }

    @Provides
    public ForgotPasswordActivityMVP.Model provideForgotPasswordActivityModel() {
        return new ForgotPasswordActivityModel();
    }


}
