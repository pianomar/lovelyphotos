package com.omarhezi.lovelyphotos.Photos.UseCase;

import android.annotation.SuppressLint;

import com.omarhezi.lovelyphotos.General.Base.BaseGetUseCase;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;
import com.omarhezi.lovelyphotos.Photos.Repository.IPhotosRepository;
import com.omarhezi.lovelyphotos.Photos.Repository.PhotosRepository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GetPhotosUseCase extends BaseGetUseCase<PhotoDTO> {

    private Integer mAlbumId;
    private IPhotosRepository mRepository;

    public GetPhotosUseCase(Integer albumId) {
        mAlbumId = albumId;
    }

    @SuppressLint("CheckResult")
    @Override
    public void execute() {
        if (mAlbumId != null && mAlbumId != 0) { //0 is not a valid albumId
            mRepository = new PhotosRepository();
            mRepository.getPhotos(mAlbumId, mPaginationDTO).subscribeWith(new Observer<List<PhotoDTO>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    //Nothing here
                }

                @Override
                public void onNext(List<PhotoDTO> photoDTOS) {
                    if (mCallBack != null) {
                        if (photoDTOS != null && photoDTOS.isEmpty()) {
                            mCanLoadMore = false;
                        }

                        mCallBack.onSuccess(photoDTOS);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (mCallBack != null) {
                        mCallBack.onFail();
                    }
                }

                @Override
                public void onComplete() {
                    //Nothing here
                }
            });
        }
    }
}