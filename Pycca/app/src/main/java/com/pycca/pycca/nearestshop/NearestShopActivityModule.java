package com.pycca.pycca.nearestshop;

import dagger.Module;
import dagger.Provides;

@Module
public class NearestShopActivityModule {

    @Provides
    NearestShopActivityMVP.Presenter provideNearestShopActivityPresenter(NearestShopActivityMVP.Model model) {
        return new NearestShopActivityPresenter(model);
    }

    @Provides
    NearestShopActivityMVP.Model provideNearestShopActivityModel() {
        return new NearestShopActivityModel();
    }

}
