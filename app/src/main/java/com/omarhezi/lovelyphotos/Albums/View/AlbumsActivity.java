package com.omarhezi.lovelyphotos.Albums.View;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.omarhezi.lovelyphotos.Albums.Presenter.AlbumsPresenter;
import com.omarhezi.lovelyphotos.Albums.Presenter.IAlbumsPresenter;
import com.omarhezi.lovelyphotos.General.Adapters.AlbumsAdapter;
import com.omarhezi.lovelyphotos.General.Adapters.ViewHolders.AlbumViewHolder;
import com.omarhezi.lovelyphotos.General.Base.BaseActivity;
import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;
import com.omarhezi.lovelyphotos.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Explode;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionListenerAdapter;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumsActivity extends BaseActivity implements IAlbumsView, AlbumViewHolder.IAlbumEventsListener {

    public static final int COLUMNS_NUM = 2;

    @BindView(R.id.rec_vw_home_albums)
    RecyclerView mRecVwHomeAlbums;
    @BindView(R.id.const_vw_home_rootVw)
    ConstraintLayout mConstVwHomeRootVw;
    @BindView(R.id.progress_vw)
    View mProgressVw;
    @BindView(R.id.offline_vw)
    View mOfflineVw;

    private IAlbumsPresenter mPresenter;
    private AlbumsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        if (isOnline()) {
            setupPresenter();
            setupRecyclerView();
            getAlbums();
        } else {
            mProgressVw.setVisibility(View.GONE);
            mOfflineVw.setVisibility(View.VISIBLE);
        }
    }

    private void getAlbums() {
        mProgressVw.setVisibility(View.VISIBLE);
        mPresenter.getAlbums();
    }

    private void setupRecyclerView() {
        mAdapter = new AlbumsAdapter(this);
        mRecVwHomeAlbums.setAdapter(mAdapter);
        // use a linear layout manager
        GridLayoutManager layoutManager = new GridLayoutManager(this, COLUMNS_NUM);
        mRecVwHomeAlbums.setLayoutManager(layoutManager);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) { //Header's position
                    return COLUMNS_NUM;
                } else {
                    return 1;
                }
            }
        });
    }

    private void setupPresenter() {
        mPresenter = new AlbumsPresenter(this);
        mPresenter.setScreenTitle(getString(R.string.lbl_albums));
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void showError(int message) {
        super.showError(mConstVwHomeRootVw, getString(R.string.msg_error_generic));
    }

    @Override
    public void onGetAlbums(List<AlbumDTO> albumDTOS) {
        runOnUiThread(() -> {
            mAdapter.setItems(albumDTOS);
            mAdapter.notifyDataSetChanged();
        });
    }

    private void explode(AlbumDTO albumDTO, View clickedView) {
        final Rect viewRect = new Rect();
        clickedView.getGlobalVisibleRect(viewRect);

        final Explode explode = new Explode();
        explode.setEpicenterCallback(new Transition.EpicenterCallback() {
            @Override
            public Rect onGetEpicenter(@NonNull Transition transition) {
                return viewRect;
            }
        });
        explode.excludeTarget(clickedView, true);
        TransitionSet set = new TransitionSet()
                .addTransition(explode)
                .addTransition(new Fade().addTarget(clickedView))
                .addListener(new TransitionListenerAdapter() {
                    @Override
                    public void onTransitionEnd(@NonNull Transition transition) {
                        transition.removeListener(this);
                        goToPhotosActivity(albumDTO);
                    }
                });
        TransitionManager.beginDelayedTransition(mRecVwHomeAlbums, set);

        // remove all views from Recycler View
        mRecVwHomeAlbums.setAdapter(null);
    }

    /**
     * Because of the exploding animation, this has to be done so he
     */
    @Override
    protected void onResume() {
        super.onResume();
        mRecVwHomeAlbums.setAdapter(mAdapter);
    }

    private void goToPhotosActivity(AlbumDTO albumDTO) {
        // TODO: 3/27/2019 Go to photo activity
    }

    @Override
    public void onAlbumClick(AlbumDTO albumDTO, View view) {
        explode(albumDTO, view);
    }

    @Override
    public void hideProgress() {
        runOnUiThread(() -> mProgressVw.setVisibility(View.GONE));
    }
}
