package com.omarhezi.lovelyphotos;

import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;
import com.omarhezi.lovelyphotos.General.Misc.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    String ALBUMS_ENDPOINT = "albums";
    String PHOTOS_ENDPOINT = "photos";

    @GET(ALBUMS_ENDPOINT)
    Observable<List<AlbumDTO>> getAlbums(@Query(Constants.PAGE_QUERY_KEY) Integer page);

    @GET(PHOTOS_ENDPOINT)
    Observable<List<PhotoDTO>> getPhotos(@Query(Constants.ALBUM_ID_KEY) Integer albumId,
                                         @Query(Constants.PAGE_QUERY_KEY) Integer page,
                                         @Query(Constants.LIMIT_QUERY_KEY) Integer limit);
}
