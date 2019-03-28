package com.omarhezi.lovelyphotos.Photos.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.arasthel.spannedgridlayoutmanager.SpanSize;
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager;
import com.omarhezi.lovelyphotos.General.Adapters.PhotosAdapter;
import com.omarhezi.lovelyphotos.General.Adapters.ViewHolders.PhotoViewHolder;
import com.omarhezi.lovelyphotos.General.Base.BaseActivity;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;
import com.omarhezi.lovelyphotos.General.Misc.Constants;
import com.omarhezi.lovelyphotos.Photos.Presenter.IPhotosPresenter;
import com.omarhezi.lovelyphotos.Photos.Presenter.PhotosPresenter;
import com.omarhezi.lovelyphotos.R;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends BaseActivity implements IPhotosView, PhotoViewHolder.IPhotoEventsListener {

    public static final int COLUMNS_NUM = 4;

    @BindView(R.id.rec_vw_photos)
    RecyclerView mRecVwPhotos;
    @BindView(R.id.const_vw_photos_rootVw)
    ConstraintLayout mConstVwPhotosRootVw;
    @BindView(R.id.progress_vw)
    View mProgress;
    @BindView(R.id.progressvw_load_more)
    View mProgressLoadMore;

    private IPhotosPresenter mPresenter;
    private PhotosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        ButterKnife.bind(this);
        enableBackButton();
        setupRecyclerView();
        setupPresenter();
        getPhotos();
    }

    private void setupRecyclerView() {
        mAdapter = new PhotosAdapter(this);
        mRecVwPhotos.setAdapter(mAdapter);
        // use a linear layout manager
        setupSpanSize();
        mRecVwPhotos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(View.SCROLL_AXIS_VERTICAL)) {
                    mProgressLoadMore.setVisibility(View.VISIBLE);
                    mPresenter.loadMorePhotos();
                }
            }
        });
    }

    private void setupSpanSize() {
        SpanSize largerCell = new SpanSize(2, 2);
        SpanSize normalCell = new SpanSize(1, 1);
        SpannedGridLayoutManager layoutManager = new SpannedGridLayoutManager(
                SpannedGridLayoutManager.Orientation.VERTICAL, COLUMNS_NUM);
        layoutManager.setSpanSizeLookup(new SpannedGridLayoutManager.SpanSizeLookup(position -> {
            if (position % (COLUMNS_NUM) == 0) {
                return largerCell;
            } else {
                return normalCell;
            }
        }));
        mRecVwPhotos.setLayoutManager(layoutManager);
    }

    private void getPhotos() {
        if (getIntent() != null) {
            mProgress.setVisibility(View.VISIBLE);
            Integer albumId = getIntent().getIntExtra(Constants.ALBUM_ID_KEY, 0);
            mPresenter.getPhotos(albumId);
        }
    }

    private void setupPresenter() {
        mPresenter = new PhotosPresenter(this);
    }

    @Override
    public void showError(int message) {
        super.showError(mConstVwPhotosRootVw, getString(message));
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onGetPhotos(List<PhotoDTO> list) {
        runOnUiThread(() -> {
            mAdapter.setItems(list);
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onLoadMorePhotos(List<PhotoDTO> list) {
        runOnUiThread(() -> {
            mProgressLoadMore.setVisibility(View.GONE);
            mAdapter.loadMore(list);
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(() -> mProgress.setVisibility(View.GONE));
    }

    @Override
    public void onClickPhoto(PhotoDTO photoDTO) {
        // TODO: 3/28/2019 Take to full screen
    }
}
