package com.omarhezi.lovelyphotos.Albums.Repository;

import com.omarhezi.lovelyphotos.General.Base.BaseRepository;
import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;
import com.omarhezi.lovelyphotos.Service;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AlbumsRepository extends BaseRepository implements IAlbumsRepository {
    @Override
    public Observable<List<AlbumDTO>> getAlbums(Integer page) {
        return getRetrofit().create(Service.class)
                .getAlbums(page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread());
    }
}
