package com.pycca.pycca.accountstatus;


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
