package com.omarhezi.lovelyphotos.General.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;
import com.omarhezi.lovelyphotos.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class PhotosFullScreenAdapter extends PagerAdapter {

    public interface IFullScreenListener {
        void didTapPhoto();
    }

    private List<PhotoDTO> mPhotoDTOS;
    private IFullScreenListener mListener;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {

        View view = LayoutInflater.from(collection.getContext()).inflate(R.layout.item_fullscreen_photos, collection, false);
        PhotoView imageVw = view.findViewById(R.id.img_vw_fullScreen);
        TextView txtVwTitle = view.findViewById(R.id.txt_vw_fullScreen_title);
        PhotoDTO photoDTO = mPhotoDTOS.get(position);
        if (photoDTO != null) {
            Glide.with(view)
                    .load(photoDTO.getUrl())
                    .into(imageVw);
            txtVwTitle.setText(photoDTO.getTitle());
            imageVw.setOnPhotoTapListener((view1, x, y) -> {
                if (mListener != null) {
                    mListener.didTapPhoto();
                }
            });
        }
        collection.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setPhotoDTOS(List<PhotoDTO> photoDTOS) {
        mPhotoDTOS = photoDTOS;
    }

    @Override
    public int getCount() {
        return mPhotoDTOS != null ? mPhotoDTOS.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void setListener(IFullScreenListener listener) {
        mListener = listener;
    }
}

