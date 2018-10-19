package com.pycca.pycca.more;

import dagger.Module;
import dagger.Provides;

@Module
public class MoreFragmentModule {

    @Provides
    public MoreFragmentMVP.Presenter provideMoreFragmentPresenter(MoreFragmentMVP.Model model) {
        return new MoreFragmentPresenter(model);
    }

    @Provides
    public MoreFragmentMVP.Model provideMoreFragmentModel(MoreFragmentRepository moreFragmentRepository) {
        return new MoreFragmentModel(moreFragmentRepository);
    }

    @Provides
    public MoreFragmentRepository provideMoreFragmentRepository(){
        return new MoreRepository();
    }

}
