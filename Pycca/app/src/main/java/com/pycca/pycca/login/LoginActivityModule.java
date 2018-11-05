package com.pycca.pycca.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model) {
        return new LoginActivityPresenter(model);
    }

    @Provides
    LoginActivityMVP.Model provideLoginActivityModel() {
        return new LoginActivityModel();
    }

}
