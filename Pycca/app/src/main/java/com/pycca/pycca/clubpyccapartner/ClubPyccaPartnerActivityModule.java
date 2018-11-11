package com.pycca.pycca.clubpyccapartner;

import dagger.Module;
import dagger.Provides;

@Module
public class ClubPyccaPartnerActivityModule {

    @Provides
    ClubPyccaPartnerActivityMVP.Presenter provideClubPyccaPartnerActivityPresenter(ClubPyccaPartnerActivityMVP.Model model) {
        return new ClubPyccaPartnerActivityPresenter(model);
    }

    @Provides
    ClubPyccaPartnerActivityMVP.Model provideClubPyccaPartnerActivityModel() {
        return new ClubPyccaPartnerActivityModel();
    }

}
