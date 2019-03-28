package com.omarhezi.lovelyphotos.Albums.Presenter;

import com.omarhezi.lovelyphotos.General.Base.IBasePresenter;

public interface IAlbumsPresenter extends IBasePresenter {
    void getAlbums();

    void setScreenTitle(String title);
}
