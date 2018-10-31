package com.pycca.pycca.splash;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {

    @Provides
    SplashActivityMVP.Presenter provideSplashActivityPresenter(SplashActivityMVP.Model model) {
        return new SplashActivityPresenter(model);
    }

    @Provides
    SplashActivityMVP.Model provideSplashActivityModel() {
        return new SplashActivityModel();
    }


}
