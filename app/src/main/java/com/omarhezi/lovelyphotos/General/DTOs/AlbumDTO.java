package com.omarhezi.lovelyphotos.General.DTOs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omarhezi.lovelyphotos.General.Misc.Constants;

public class AlbumDTO extends BaseDTO {
    @SerializedName(Constants.USER_ID_KEY)
    @Expose
    private Integer mUserId;
    @SerializedName(Constants.ID_KEY)
    @Expose
    private Integer mId;
    @SerializedName(Constants.TITLE_KEY)
    @Expose
    private String mTitle;

    private int mColor;

    public Integer getUserId() {
        return mUserId;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
