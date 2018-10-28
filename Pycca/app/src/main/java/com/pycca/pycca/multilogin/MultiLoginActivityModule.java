package com.pycca.pycca.multilogin;

import dagger.Module;
import dagger.Provides;

@Module
public class MultiLoginActivityModule {

    @Provides
    MultiLoginActivityMVP.Presenter provideMultiLoginActivityPresenter(MultiLoginActivityMVP.Model model) {
        return new MultiLoginActivityPresenter(model);
    }

    @Provides
    MultiLoginActivityMVP.Model provideMultiLoginActivityModel() {
        return new MultiLoginActivityModel();
    }

}
