package com.pycca.pycca.cardlocking;

import dagger.Module;
import dagger.Provides;

@Module
public class CardLockingActivityModule {

    @Provides
    CardLockingActivityMVP.Presenter provideCardBlockingActivityPresenter(CardLockingActivityMVP.Model model) {
        return new CardLockingActivityPresenter(model);
    }

    @Provides
    CardLockingActivityMVP.Model provideCardBlockingActivityModel() {
        return new CardLockingActivityModel();
    }

}
