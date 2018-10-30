package com.pycca.pycca.clubpycca;

import dagger.Module;
import dagger.Provides;

@Module
public class ClubPyccaFragmentModule {

    @Provides
    ClubPyccaFragmentMVP.Presenter provideClubPyccaFragmentPresenter(ClubPyccaFragmentMVP.Model model) {
        return new ClubPyccaFragmentPresenter(model);
    }

    @Provides
    ClubPyccaFragmentMVP.Model provideClubPyccaFragmentModel(ClubPyccaFragmentRepository clubPyccaFragmentRepository) {
        return new ClubPyccaFragmentModel(clubPyccaFragmentRepository);
    }

    @Provides
    ClubPyccaFragmentRepository provideClubPyccaFragmentRepository(){
        return new ClubPyccaRepository();
    }

}
