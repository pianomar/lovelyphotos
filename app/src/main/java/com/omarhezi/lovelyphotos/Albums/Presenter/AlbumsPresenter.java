package com.omarhezi.lovelyphotos.Albums.Presenter;

import android.graphics.Color;

import com.omarhezi.lovelyphotos.Albums.UseCase.GetAlbumsUseCase;
import com.omarhezi.lovelyphotos.Albums.View.IAlbumsView;
import com.omarhezi.lovelyphotos.General.Adapters.Delegates.AlbumsAdapterDelegate;
import com.omarhezi.lovelyphotos.General.Adapters.Delegates.TitleAdapterDelegate;
import com.omarhezi.lovelyphotos.General.Base.BaseGetUseCase;
import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;
import com.omarhezi.lovelyphotos.General.DTOs.PaginationDTO;
import com.omarhezi.lovelyphotos.R;

import java.util.List;
import java.util.Random;

public class AlbumsPresenter implements IAlbumsPresenter {

    private IAlbumsView mView;
    private GetAlbumsUseCase mGetAlbumsUseCase;
    private String mScreenTitle;

    public AlbumsPresenter(IAlbumsView view) {
        mView = view;
    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void getAlbums() {
        mGetAlbumsUseCase = new GetAlbumsUseCase();
        //PaginationDTO has default values
        mGetAlbumsUseCase.setPaginationDTO(new PaginationDTO());
        mGetAlbumsUseCase.setCallBack(new BaseGetUseCase.IGetCallBack<AlbumDTO>() {
            @Override
            public void onSuccess(List<AlbumDTO> albumDTOS) {
                setupAdditionalInfo(albumDTOS);
                addTitleItem(albumDTOS);
                if (mView != null) {
                    mView.onGetAlbums(albumDTOS);
                    mView.hideProgress();
                }
            }

            @Override
            public void onFail() {
                if (mView != null) {
                    mView.hideProgress();
                    mView.showError(R.string.msg_error_generic);
                }
            }
        });
        mGetAlbumsUseCase.execute();
    }

    @Override
    public void setScreenTitle(String string) {
        mScreenTitle = string;
    }

    private void addTitleItem(List<AlbumDTO> albumDTOS) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setType(TitleAdapterDelegate.DELEGATE_TYPE);
        albumDTO.setTitle(mScreenTitle);
        albumDTOS.add(0, albumDTO);
    }

    /**
     * Sets a color to each album to show instead of preview photos
     * (inability to implement preview photos because of API error)
     *
     * @param albumDTOS
     */
    private void setupAdditionalInfo(List<AlbumDTO> albumDTOS) {
        for (AlbumDTO albumDTO : albumDTOS) {
            setColor(albumDTO);
            albumDTO.setType(AlbumsAdapterDelegate.DELEGATE_TYPE);
        }
    }

    private void setColor(AlbumDTO albumDTO) {
        Random rnd = new Random();
        int color = Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        albumDTO.setColor(color);
    }
}
