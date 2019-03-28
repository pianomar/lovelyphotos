package com.omarhezi.lovelyphotos.General.Base;

import com.omarhezi.lovelyphotos.General.DTOs.PaginationDTO;

import java.util.List;

public abstract class BaseGetUseCase<T> {
    protected IGetCallBack<T> mCallBack;
    protected PaginationDTO mPaginationDTO;
    protected boolean mCanLoadMore = true;

    public BaseGetUseCase() {
        mPaginationDTO = new PaginationDTO();
    }

    public boolean canLoadMore() {
        return mCanLoadMore;
    }

    public interface IGetCallBack<T> {
        void onSuccess(List<T> list);

        void onFail();
    }

    public void setCallBack(IGetCallBack<T> callBack) {
        mCallBack = callBack;
    }

    public void setPaginationDTO(PaginationDTO paginationDTO) {
        mPaginationDTO = paginationDTO;
    }

    private void updatePagination() {
        if (mPaginationDTO != null) {
            mPaginationDTO.setPage(mPaginationDTO.getPage() + 1);
        }
    }

    public abstract void execute();

    public void loadMore() {
        updatePagination();
        execute();
    }
}

