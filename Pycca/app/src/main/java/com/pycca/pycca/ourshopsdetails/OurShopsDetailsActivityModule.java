package com.pycca.pycca.ourshopsdetails;

import dagger.Module;
import dagger.Provides;

@Module
public class OurShopsDetailsActivityModule {

    @Provides
    OurShopsDetailsActivityMVP.Presenter provideOurShopsDetailsActivityPresenter(OurShopsDetailsActivityMVP.Model model) {
        return new OurShopsDetailsActivityPresenter(model);
    }

    @Provides
    OurShopsDetailsActivityMVP.Model provideOurShopsDetailsActivityModel() {
        return new OurShopsDetailsActivityModel();
    }

}
