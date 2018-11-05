package com.pycca.pycca.picture;

import com.pycca.pycca.home.HomeFragmentMVP;
import com.pycca.pycca.home.HomeFragmentModel;
import com.pycca.pycca.home.HomeFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PictureActivityModule {

    @Provides
    public PictureActivityMVP.Presenter providePictureActivityPresenter(){
        return new PictureActivityPresenter();
    }
}
