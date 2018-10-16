package com.pycca.pycca.clubpycca;

import dagger.Module;
import dagger.Provides;

@Module
public class ClubPyccaFragmentModule {

    @Provides
    public ClubPyccaFragmentMVP.Presenter provideClubPyccaFragmentPresenter(ClubPyccaFragmentMVP.Model model) {
        return new ClubPyccaFragmentPresenter(model);
    }

    @Provides
    public ClubPyccaFragmentMVP.Model provideClubPyccaFragmentModel(ClubPyccaFragmentRepository clubPyccaFragmentRepository) {
        return new ClubPyccaFragmentModel(clubPyccaFragmentRepository);
    }

    @Provides
    public ClubPyccaFragmentRepository provideClubPyccaFragmentRepository(){
        return new ClubPyccaRepository();
    }

}
