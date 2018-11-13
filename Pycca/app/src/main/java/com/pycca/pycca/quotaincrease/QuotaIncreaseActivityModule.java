package com.pycca.pycca.quotaincrease;

import dagger.Module;
import dagger.Provides;

@Module
public class QuotaIncreaseActivityModule {

    @Provides
    QuotaIncreaseActivityMVP.Presenter provideQuotaIncreaseActivityPresenter(QuotaIncreaseActivityMVP.Model model) {
        return new QuotaIncreaseActivityPresenter(model);
    }

    @Provides
    QuotaIncreaseActivityMVP.Model provideQuotaIncreaseActivityModel() {
        return new QuotaIncreaseActivityModel();
    }

}
