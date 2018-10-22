package com.pycca.pycca.promotion;

import dagger.Module;
import dagger.Provides;

@Module
public class PromotionFragmentModule {
    @Provides
    public PromotionFragmentMVP.Presenter providePromotionFragmenPresenter(PromotionFragmentMVP.Model model){
        return new PromotionFragmentPresenter(model);
    }

    @Provides
    public PromotionFragmentMVP.Model providePromotionFragmenModel(){
        return new PromotionFragmentModel();
    }

}
