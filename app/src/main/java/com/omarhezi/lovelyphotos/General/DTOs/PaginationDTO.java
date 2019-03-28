package com.omarhezi.lovelyphotos.General.DTOs;

public class PaginationDTO {
    private Integer mLimit;
    private Integer mPage;

    public PaginationDTO() {
        mLimit = 10;
        mPage = 1;
    }

    public Integer getLimit() {
        return mLimit;
    }

    public Integer getPage() {
        return mPage;
    }

    public void setLimit(Integer limit) {
        mLimit = limit;
    }

    public void setPage(Integer page) {
        mPage = page;
    }
}
