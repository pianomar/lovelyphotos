package com.omarhezi.lovelyphotos.Albums.View;

import com.omarhezi.lovelyphotos.General.Base.IBaseView;
import com.omarhezi.lovelyphotos.General.Base.IProgressView;
import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;

import java.util.List;

public interface IAlbumsView extends IBaseView, IProgressView {
    void onGetAlbums(List<AlbumDTO> albumDTOS);

}
