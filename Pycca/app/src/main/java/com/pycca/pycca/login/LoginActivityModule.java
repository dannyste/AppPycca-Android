package com.pycca.pycca.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model) {
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel() {
        return new LoginActivityModel();
    }

}
