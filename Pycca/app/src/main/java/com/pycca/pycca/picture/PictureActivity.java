package com.pycca.pycca.picture;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class PictureActivity extends AppCompatActivity implements PictureActivityMVP.View {

    @Inject
    public PictureActivityMVP.Presenter presenter;

    PhotoView photoView;
    TextView tvDescription;
    RelativeLayout rl_root_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ((App) getApplication()).getApplicationComponent().inject(PictureActivity.this);
        photoView = findViewById(R.id.photo_view);
        tvDescription = findViewById(R.id.tv_description);
        rl_root_view = findViewById(R.id.rl_root_view);
        getSupportActionBar().hide();
    }

    @Override
    public void setData(ImageResource imageResource) {
        if(imageResource != null){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions
                    .centerInside()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .error(R.drawable.ic_broken_image);
            Glide.with(this)
                    .load(imageResource.getPath())
                    .apply(requestOptions)
                    .into(photoView);
            tvDescription.setText(imageResource.getDescription());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(PictureActivity.this);
        presenter.receiveData(this);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public void showMessage(int errorCode) {
        Util.showMessage(rl_root_view, getResources().getString(errorCode));
    }

}
