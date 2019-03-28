package com.omarhezi.lovelyphotos.General.Adapters.Delegates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.omarhezi.lovelyphotos.General.Adapters.ViewHolders.AlbumViewHolder;
import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;
import com.omarhezi.lovelyphotos.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumsAdapterDelegate extends AdapterDelegate<List<AlbumDTO>> {

    public static final int DELEGATE_TYPE = 1;

    private final AlbumViewHolder.IAlbumEventsListener mListener;

    public AlbumsAdapterDelegate(AlbumViewHolder.IAlbumEventsListener listener) {
        mListener = listener;
    }

    @Override
    protected boolean isForViewType(@NonNull List<AlbumDTO> items, int position) {
        return items.get(position) != null && items.get(position).getType() == DELEGATE_TYPE;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        AlbumViewHolder albumViewHolder = new AlbumViewHolder(view);
        albumViewHolder.setListener(mListener);
        return albumViewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull List<AlbumDTO> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        AlbumViewHolder viewHolder = (AlbumViewHolder) holder;
        AlbumDTO albumDTO = items.get(position);
        viewHolder.onBindViewHolder(albumDTO);
    }
}
