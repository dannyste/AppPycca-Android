package com.pycca.pycca.cardblocking;

import dagger.Module;
import dagger.Provides;

@Module
public class CardBlockingActivityModule {

    @Provides
    CardBlockingActivityMVP.Presenter provideCardBlockingActivityPresenter(CardBlockingActivityMVP.Model model) {
        return new CardBlockingActivityPresenter(model);
    }

    @Provides
    CardBlockingActivityMVP.Model provideCardBlockingActivityModel() {
        return new CardBlockingActivityModel();
    }

}
