package com.pycca.pycca.buy;

import com.pycca.pycca.home.HomeFragmentMVP;
import com.pycca.pycca.home.HomeFragmentModel;
import com.pycca.pycca.home.HomeFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class BuyFragmentModule {

    @Provides
    public BuyFragmentMVP.Presenter provideBuyFragmentPresenter(BuyFragmentMVP.Model model){
        return new BuyFragmentPresenter(model);
    }

    @Provides
    public BuyFragmentMVP.Model provideBuyFragmentModel(){
        return new BuyFragmentModel();
    }

}
