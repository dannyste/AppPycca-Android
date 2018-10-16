package com.pycca.pycca.root;

import android.app.Application;

import com.pycca.pycca.clubpycca.ClubPyccaFragmentModule;
import com.pycca.pycca.home.HomeFragmentModule;
import com.pycca.pycca.login.LoginActivityModule;
import com.pycca.pycca.multilogin.MultiLoginActivityModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .multiLoginActivityModule(new MultiLoginActivityModule())
                .loginActivityModule(new LoginActivityModule())
                .homeFragmentModule(new HomeFragmentModule())
                .clubPyccaFragmentModule(new ClubPyccaFragmentModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
