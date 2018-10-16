package com.pycca.pycca.root;

import com.pycca.pycca.clubpycca.ClubPyccaFragment;
import com.pycca.pycca.clubpycca.ClubPyccaFragmentModule;
import com.pycca.pycca.home.HomeFragment;
import com.pycca.pycca.home.HomeFragmentModule;
import com.pycca.pycca.login.LoginActivity;
import com.pycca.pycca.login.LoginActivityModule;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.multilogin.MultiLoginActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, MultiLoginActivityModule.class, LoginActivityModule.class, HomeFragmentModule.class, ClubPyccaFragmentModule.class})
public interface ApplicationComponent {

    void inject(MultiLoginActivity multiLoginActivity);

    void inject(LoginActivity loginActivity);

    void inject(HomeFragment homeFragment);

    void inject(ClubPyccaFragment clubPyccaFragment);

}
