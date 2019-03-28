package com.omarhezi.lovelyphotos.Photos.Presenter;

import com.omarhezi.lovelyphotos.General.Adapters.Delegates.PhotoAdapterDelegate;
import com.omarhezi.lovelyphotos.General.Base.BaseGetUseCase;
import com.omarhezi.lovelyphotos.General.DTOs.PaginationDTO;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;
import com.omarhezi.lovelyphotos.Photos.UseCase.GetPhotosUseCase;
import com.omarhezi.lovelyphotos.Photos.View.IPhotosView;
import com.omarhezi.lovelyphotos.R;

import java.util.List;

public class PhotosPresenter implements IPhotosPresenter {
    private static final int PHOTOS_LIMIT = 20;

    private IPhotosView mView;
    private GetPhotosUseCase mUseCase;

    public PhotosPresenter(IPhotosView view) {
        mView = view;
    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void getPhotos(Integer albumId) {
        mUseCase = new GetPhotosUseCase(albumId);
        setupPagination();
        mUseCase.setCallBack(new BaseGetUseCase.IGetCallBack<PhotoDTO>() {
            @Override
            public void onSuccess(List<PhotoDTO> list) {
                if (mView != null) {
                    setupAdditionalInfo(list);
                    mView.hideProgress();
                    mView.onGetPhotos(list);
                }
            }

            @Override
            public void onFail() {
                if (mView != null) {
                    mView.hideProgress();
                    mView.showError(R.string.msg_error_generic);
                }
            }
        });
        mUseCase.execute();
    }

    @Override
    public void loadMorePhotos() {
        if (mUseCase.canLoadMore()) {
            mUseCase.setCallBack(new BaseGetUseCase.IGetCallBack<PhotoDTO>() {
                @Override
                public void onSuccess(List<PhotoDTO> list) {
                    if (mView != null) {
                        setupAdditionalInfo(list);
                        mView.onLoadMorePhotos(list);
                    }
                }

                @Override
                public void onFail() {
                    if (mView != null) {
                        mView.showError(R.string.msg_error_generic);
                    }
                }
            });
            mUseCase.loadMore();
        }
    }

    private void setupAdditionalInfo(List<PhotoDTO> list) {
        for (PhotoDTO photoDTO : list) {
            photoDTO.setType(PhotoAdapterDelegate.DELEGATE_TYPE);
        }
    }

    private void setupPagination() {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setLimit(PHOTOS_LIMIT);
        mUseCase.setPaginationDTO(paginationDTO);
    }
}
