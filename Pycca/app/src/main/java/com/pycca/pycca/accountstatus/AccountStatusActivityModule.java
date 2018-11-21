package com.pycca.pycca.accountstatus;

import com.pycca.pycca.balance.BalanceActivityMVP;
import com.pycca.pycca.balance.BalanceActivityModel;
import com.pycca.pycca.balance.BalanceActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AccountStatusActivityModule {

    @Provides
    public AccountStatusActivityMVP.Presenter provideAccountStatusActivityPresenter(AccountStatusActivityMVP.Model model){
        return new AccountStatusActivityPresenter(model);
    }

    @Provides
    public AccountStatusActivityMVP.Model provideAccountStatusActivityModel(){
        return new AccountStatusActivityModel();
    }

}
