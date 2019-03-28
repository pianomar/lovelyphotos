package com.omarhezi.lovelyphotos.General.Adapters.Delegates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.omarhezi.lovelyphotos.General.Adapters.ViewHolders.TitleViewHolder;
import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;
import com.omarhezi.lovelyphotos.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TitleAdapterDelegate extends AdapterDelegate<List<AlbumDTO>> {

    public static final int DELEGATE_TYPE = 2;

    @Override
    protected boolean isForViewType(@NonNull List<AlbumDTO> items, int position) {
        return items.get(position) != null && items.get(position).getType() == DELEGATE_TYPE;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
        return new TitleViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<AlbumDTO> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        TitleViewHolder viewHolder = (TitleViewHolder) holder;
        AlbumDTO titleDTO = items.get(position);
        viewHolder.onBindViewHolder(titleDTO);
    }
}
