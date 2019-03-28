package com.omarhezi.lovelyphotos.General.Adapters.ViewHolders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;
import com.omarhezi.lovelyphotos.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private final View mItemView;
    private final RequestOptions mImageRequestOptions;
    private IPhotoEventsListener mListener;

    @BindView(R.id.img_vw_item_photo)
    ImageView mImgVwItemPhoto;

    public interface IPhotoEventsListener {
        void onClickPhoto(PhotoDTO photoDTO);
    }

    public PhotoViewHolder(@NonNull View itemView, IPhotoEventsListener listener) {
        super(itemView);
        mItemView = itemView;
        mImageRequestOptions = new RequestOptions().error(new ColorDrawable(Color.GRAY));
        ButterKnife.bind(this, mItemView);
        mListener = listener;
    }

    public void onBindViewHolder(PhotoDTO photoDTO) {
        Glide.with(mItemView)
                .load(photoDTO.getThumbnailUrl())
                .apply(mImageRequestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mImgVwItemPhoto);
        mImgVwItemPhoto.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onClickPhoto(photoDTO);
            }
        });
    }
}