package com.omarhezi.lovelyphotos.Photos.Repository;

import com.omarhezi.lovelyphotos.General.DTOs.PaginationDTO;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;

import java.util.List;

import io.reactivex.Observable;

public interface IPhotosRepository {
    Observable<List<PhotoDTO>> getPhotos(Integer albumId, PaginationDTO paginationDTO);
}
