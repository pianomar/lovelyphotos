package com.omarhezi.lovelyphotos.Photos.Repository;

import com.omarhezi.lovelyphotos.General.Base.BaseRepository;
import com.omarhezi.lovelyphotos.General.DTOs.PaginationDTO;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;
import com.omarhezi.lovelyphotos.Service;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class PhotosRepository extends BaseRepository implements IPhotosRepository {
    @Override
    public Observable<List<PhotoDTO>> getPhotos(Integer albumId, PaginationDTO paginationDTO) {
        return getRetrofit().create(Service.class)
                .getPhotos(albumId, paginationDTO.getPage(), paginationDTO.getLimit())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread());
    }
}
