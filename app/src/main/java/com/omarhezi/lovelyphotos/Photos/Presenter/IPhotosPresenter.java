package com.omarhezi.lovelyphotos.Photos.Presenter;

import com.omarhezi.lovelyphotos.General.Base.IBasePresenter;

public interface IPhotosPresenter extends IBasePresenter {
    void getPhotos(Integer albumId);

    void loadMorePhotos();
}

