package com.omarhezi.lovelyphotos.General.DTOs;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.omarhezi.lovelyphotos.General.Misc.Constants;

public class PhotoDTO extends BaseDTO implements Parcelable {
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

    protected PhotoDTO(Parcel in) {
        if (in.readByte() == 0) {
            mAlbumId = null;
        } else {
            mAlbumId = in.readInt();
        }
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readInt();
        }
        mTitle = in.readString();
        mUrl = in.readString();
        mThumbnailUrl = in.readString();
    }

    public static final Creator<PhotoDTO> CREATOR = new Creator<PhotoDTO>() {
        @Override
        public PhotoDTO createFromParcel(Parcel in) {
            return new PhotoDTO(in);
        }

        @Override
        public PhotoDTO[] newArray(int size) {
            return new PhotoDTO[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mAlbumId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mAlbumId);
        }
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mId);
        }
        dest.writeString(mTitle);
        dest.writeString(mUrl);
        dest.writeString(mThumbnailUrl);
    }
}
