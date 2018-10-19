package com.pycca.pycca.signup;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpActivityModule {

    @Provides
    public SignUpActivityMVP.Presenter provideSignUpActivityPresenter(SignUpActivityMVP.Model model) {
        return new SignUpActivityPresenter(model);
    }

    @Provides
    public SignUpActivityMVP.Model provideSignUpActivityModel() {
        return new SignUpActivityModel();
    }

}
