package com.pycca.pycca.quotacalculator;

import dagger.Module;
import dagger.Provides;

@Module
public class QuotaCalculatorActivityModule {

    @Provides
    QuotaCalculatorActivityMVP.Presenter provideQuotaCalculatorActivityPresenter(QuotaCalculatorActivityMVP.Model model) {
        return new QuotaCalculatorActivityPresenter(model);
    }

    @Provides
    QuotaCalculatorActivityMVP.Model provideQuotaCalculatorActivityModel() {
        return new QuotaCalculatorActivityModel();
    }

}
