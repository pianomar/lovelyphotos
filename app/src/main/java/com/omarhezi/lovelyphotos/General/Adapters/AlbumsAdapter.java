package com.omarhezi.lovelyphotos.General.Adapters;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;
import com.omarhezi.lovelyphotos.General.Adapters.Delegates.AlbumsAdapterDelegate;
import com.omarhezi.lovelyphotos.General.Adapters.Delegates.TitleAdapterDelegate;
import com.omarhezi.lovelyphotos.General.Adapters.ViewHolders.AlbumViewHolder;
import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;

import java.util.List;

public class AlbumsAdapter extends ListDelegationAdapter<List<AlbumDTO>> {

    public AlbumsAdapter(AlbumViewHolder.IAlbumEventsListener listener) {
        delegatesManager = new AdapterDelegatesManager<>();
        // AdapterDelegatesManager internally assigns view types integers
        delegatesManager
                .addDelegate(new AlbumsAdapterDelegate(listener))
                .addDelegate(new TitleAdapterDelegate());
    }
}
