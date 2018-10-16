package com.pycca.pycca.root;

import android.app.Application;

import com.pycca.pycca.login.LoginModule;
import com.pycca.pycca.multilogin.MultiLoginModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .multiLoginModule(new MultiLoginModule())
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
