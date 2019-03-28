package com.omarhezi.lovelyphotos.General.DTOs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omarhezi.lovelyphotos.General.Misc.Constants;

public class PhotoDTO extends BaseDTO {
    @SerializedName(Constants.ALBUM_ID_KEY)
    @Expose
    private Integer mAlbumId;
    @SerializedName(Constants.ID_KEY)
    @Expose
    private Integer mId;
    @SerializedName(Constants.TITLE_KEY)
    @Expose
    private String mTitle;
    @SerializedName(Constants.URL_KEY)
    @Expose
    private String mUrl;
    @SerializedName(Constants.THUMBNAIL_URL_KEY)
    @Expose
    private String mThumbnailUrl;

    public Integer getAlbumId() {
        return mAlbumId;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }


}
