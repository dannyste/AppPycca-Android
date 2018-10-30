package com.pycca.pycca.more;

import dagger.Module;
import dagger.Provides;

@Module
public class MoreFragmentModule {

    @Provides
    MoreFragmentMVP.Presenter provideMoreFragmentPresenter(MoreFragmentMVP.Model model) {
        return new MoreFragmentPresenter(model);
    }

    @Provides
    MoreFragmentMVP.Model provideMoreFragmentModel(MoreFragmentRepository moreFragmentRepository) {
        return new MoreFragmentModel(moreFragmentRepository);
    }

    @Provides
    MoreFragmentRepository provideMoreFragmentRepository(){
        return new MoreRepository();
    }

}
