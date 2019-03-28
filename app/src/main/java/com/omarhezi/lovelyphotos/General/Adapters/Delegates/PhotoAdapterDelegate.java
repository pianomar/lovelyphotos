package com.omarhezi.lovelyphotos.General.Adapters.Delegates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.omarhezi.lovelyphotos.General.Adapters.ViewHolders.PhotoViewHolder;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;
import com.omarhezi.lovelyphotos.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoAdapterDelegate extends AdapterDelegate<List<PhotoDTO>> {

    public static final int DELEGATE_TYPE = 3;
    private PhotoViewHolder.IPhotoEventsListener mListener;

    public PhotoAdapterDelegate(PhotoViewHolder.IPhotoEventsListener listener) {
        mListener = listener;
    }

    @Override
    protected boolean isForViewType(@NonNull List<PhotoDTO> items, int position) {
        return items.get(position).getType() == DELEGATE_TYPE;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view, mListener);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<PhotoDTO> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        PhotoDTO photoDTO = items.get(position);
        PhotoViewHolder viewHolder = (PhotoViewHolder) holder;
        viewHolder.onBindViewHolder(photoDTO);
    }
}

