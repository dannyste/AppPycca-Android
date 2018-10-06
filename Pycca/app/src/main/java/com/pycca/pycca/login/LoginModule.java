package com.pycca.pycca.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model) {
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository loginRepository) {
        return new LoginActivityModel(loginRepository);
    }

    @Provides
    public LoginRepository provideLoginRepository() {
        return new FirebaseRepository();
    }

}
