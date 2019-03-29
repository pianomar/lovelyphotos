package com.omarhezi.lovelyphotos.PhotosFullScreen.View;

import android.os.Bundle;
import android.view.View;

import com.omarhezi.lovelyphotos.General.Adapters.PhotosFullScreenAdapter;
import com.omarhezi.lovelyphotos.General.Base.BaseActivity;
import com.omarhezi.lovelyphotos.General.Misc.Constants;
import com.omarhezi.lovelyphotos.General.Widgets.FullScreenImageViewPager;
import com.omarhezi.lovelyphotos.R;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.internal.operators.maybe.MaybeToObservable;

public class PhotosFullScreenActivity extends BaseActivity implements PhotosFullScreenAdapter.IFullScreenListener {

    @BindView(R.id.vw_pager_fullScreenPhotos)
    FullScreenImageViewPager mVwPagerFullScreenPhotos;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private boolean mSystemUiIsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_full_screen);
        ButterKnife.bind(this);
        PhotosFullScreenAdapter adapter = new PhotosFullScreenAdapter();
        adapter.setListener(this);
        if (getIntent() != null) {
            adapter.setPhotoDTOS(getIntent().getParcelableArrayListExtra(Constants.PHOTOS_KEY));
            mVwPagerFullScreenPhotos.setAdapter(adapter);
            mVwPagerFullScreenPhotos.setCurrentItem(getIntent().getIntExtra(Constants.PHOTO_POSITION_KEY, 0));
        }
        setSupportActionBar(mToolbar);
        enableBackButton();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        } else {
            showSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        mToolbar.setVisibility(View.INVISIBLE);
        mSystemUiIsVisible = false;
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mToolbar.setVisibility(View.VISIBLE);
        mSystemUiIsVisible = true;
    }

    @Override
    public void didTapPhoto() {
        toggleSystemUI();
    }

    private void toggleSystemUI() {
        if (mSystemUiIsVisible) {
            hideSystemUI();
        } else {
            showSystemUI();
        }
    }


}