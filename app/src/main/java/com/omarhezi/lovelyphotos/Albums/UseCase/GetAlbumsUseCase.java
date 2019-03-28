package com.omarhezi.lovelyphotos.Albums.UseCase;

import android.annotation.SuppressLint;

import com.omarhezi.lovelyphotos.Albums.Repository.AlbumsRepository;
import com.omarhezi.lovelyphotos.Albums.Repository.IAlbumsRepository;
import com.omarhezi.lovelyphotos.General.Base.BaseGetUseCase;
import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GetAlbumsUseCase extends BaseGetUseCase<AlbumDTO> {

    @SuppressLint("CheckResult")
    public void execute() {
        IAlbumsRepository repository = new AlbumsRepository();
        repository.getAlbums(mPaginationDTO.getPage()).subscribeWith(new Observer<List<AlbumDTO>>() {
            @Override
            public void onSubscribe(Disposable d) {
                //Nothing here
            }

            @Override
            public void onNext(List<AlbumDTO> albumDTOs) {
                if (mCallBack != null) {
                    //This is done because the API returns an empty list in the case of an error
                    if (albumDTOs != null && !albumDTOs.isEmpty()) {
                        mCallBack.onSuccess(albumDTOs);
                    } else {
                        onError(null);
                    }
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

