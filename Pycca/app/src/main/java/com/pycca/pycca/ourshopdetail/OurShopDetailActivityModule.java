package com.pycca.pycca.ourshopdetail;

import dagger.Module;
import dagger.Provides;

@Module
public class OurShopDetailActivityModule {

    @Provides
    OurShopDetailActivityMVP.Presenter provideOurShopsDetailsActivityPresenter(OurShopDetailActivityMVP.Model model) {
        return new OurShopDetailActivityPresenter(model);
    }

    @Provides
    OurShopDetailActivityMVP.Model provideOurShopsDetailsActivityModel() {
        return new OurShopDetailActivityModel();
    }

}
