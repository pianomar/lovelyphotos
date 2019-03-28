package com.omarhezi.lovelyphotos.Photos.View;

import com.omarhezi.lovelyphotos.General.Base.IBaseView;
import com.omarhezi.lovelyphotos.General.Base.IProgressView;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;

import java.util.List;

public interface IPhotosView extends IBaseView, IProgressView {

    void onGetPhotos(List<PhotoDTO> list);

    void onLoadMorePhotos(List<PhotoDTO> list);
}
