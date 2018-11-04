package com.pycca.pycca.host;

import dagger.Module;
import dagger.Provides;

@Module
public class HostActivityModule {

    @Provides
    HostActivityMVP.Presenter provideHostActivityPresenter(HostActivityMVP.Model model) {
        return new HostActivityPresenter(model);
    }

    @Provides
    HostActivityMVP.Model provideHostActivityModel() {
        return new HostActivityModel();
    }

}
