package com.pycca.pycca.balance;

import com.pycca.pycca.home.HomeFragmentMVP;
import com.pycca.pycca.home.HomeFragmentModel;
import com.pycca.pycca.home.HomeFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class BalanceActivityModule {

    @Provides
    public BalanceActivityMVP.Presenter provideBalanceActivityPresenter(BalanceActivityMVP.Model model){
        return new BalanceActivityPresenter(model);
    }

    @Provides
    public BalanceActivityMVP.Model provideBalanceActivityModel(){
        return new BalanceActivityModel();
    }

}
