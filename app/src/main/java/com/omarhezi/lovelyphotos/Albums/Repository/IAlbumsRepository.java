package com.omarhezi.lovelyphotos.Albums.Repository;

import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;

import java.util.List;

import io.reactivex.Observable;

public interface IAlbumsRepository {
    Observable<List<AlbumDTO>> getAlbums(Integer page);
}
