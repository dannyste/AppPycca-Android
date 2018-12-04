package com.pycca.pycca.ourshop;

import dagger.Module;
import dagger.Provides;

@Module
public class OurShopActivityModule {

    @Provides
    OurShopActivityMVP.Presenter provideOurShopsActivityPresenter(OurShopActivityMVP.Model model) {
        return new OurShopActivityPresenter(model);
    }

    @Provides
    OurShopActivityMVP.Model provideOurShopsActivityModel() {
        return new OurShopActivityModel();
    }

}
