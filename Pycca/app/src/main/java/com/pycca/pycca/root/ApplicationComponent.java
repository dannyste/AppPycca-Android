package com.pycca.pycca.root;

import com.pycca.pycca.login.LoginActivity;
import com.pycca.pycca.login.LoginModule;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.multilogin.MultiLoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    //void inject(MultiLoginActivity multiLoginActivity);

    void inject(LoginActivity loginActivity);

}
