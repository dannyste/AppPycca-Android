package com.pycca.pycca.ourshops;

import dagger.Module;
import dagger.Provides;

@Module
public class OurShopsActivityModule {

    @Provides
    OurShopsActivityMVP.Presenter provideOurShopsActivityPresenter(OurShopsActivityMVP.Model model) {
        return new OurShopsActivityPresenter(model);
    }

    @Provides
    OurShopsActivityMVP.Model provideOurShopsActivityModel() {
        return new OurShopsActivityModel();
    }

}
