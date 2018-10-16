package com.pycca.pycca.home;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {

    @Provides
    public HomeFragmentMVP.Presenter provideHomeFragmentPresenter(HomeFragmentMVP.Model model){
        return new HomeFragmentPresenter(model);
    }

    @Provides
    public HomeFragmentMVP.Model provideLoginActivityModel(){
        return new HomeFragmentModel();
    }

}
