package com.pycca.pycca.multilogin;

import dagger.Module;
import dagger.Provides;

@Module
public class MultiLoginModule {

    @Provides
    public MultiLoginActivityMVP.Presenter provideMultiLoginActivityPresenter(MultiLoginActivityMVP.Model model) {
        return new MultiLoginActivityPresenter(model);
    }

    @Provides
    public MultiLoginActivityMVP.Model provideMultiLoginActivityModel() {
        return new MultiLoginActivityModel();
    }

}
