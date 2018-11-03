package com.pycca.pycca.profile;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileActivityModule {

    @Provides
    ProfileActivityMVP.Presenter provideProfileActivityPresenter(ProfileActivityMVP.Model model) {
        return new ProfileActivityPresenter(model);
    }

    @Provides
    ProfileActivityMVP.Model provideProfileActivityModel() {
        return new ProfileActivityModel();
    }

}
