package com.pycca.pycca.virtualcard;

import dagger.Module;
import dagger.Provides;

@Module
public class VirtualCardActivityModule {

    @Provides
    VirtualCardActivityMVP.Presenter provideVirtualCardActivityPresenter(VirtualCardActivityMVP.Model model) {
        return new VirtualCardActivityPresenter(model);
    }

    @Provides
    VirtualCardActivityMVP.Model provideVirtualCardActivityModel() {
        return new VirtualCardActivityModel();
    }

}
