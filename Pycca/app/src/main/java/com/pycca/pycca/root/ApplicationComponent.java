package com.pycca.pycca.root;

import com.pycca.pycca.home.HomeFragment;
import com.pycca.pycca.home.HomeFragmentModule;
import com.pycca.pycca.login.LoginActivity;
import com.pycca.pycca.login.LoginModule;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.multilogin.MultiLoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, MultiLoginModule.class, LoginModule.class, HomeFragmentModule.class})
public interface ApplicationComponent {

    void inject(MultiLoginActivity multiLoginActivity);

    void inject(LoginActivity loginActivity);

    void inject(HomeFragment homeFragment);

}
