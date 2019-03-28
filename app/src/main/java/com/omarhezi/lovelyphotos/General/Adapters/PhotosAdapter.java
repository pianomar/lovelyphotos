package com.omarhezi.lovelyphotos.General.Adapters;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;
import com.omarhezi.lovelyphotos.General.Adapters.Delegates.PhotoAdapterDelegate;
import com.omarhezi.lovelyphotos.General.Adapters.ViewHolders.PhotoViewHolder;
import com.omarhezi.lovelyphotos.General.DTOs.PhotoDTO;

import java.util.List;

public class PhotosAdapter extends ListDelegationAdapter<List<PhotoDTO>> {

    public PhotosAdapter(PhotoViewHolder.IPhotoEventsListener listener) {
        delegatesManager = new AdapterDelegatesManager<>();
        delegatesManager.addDelegate(new PhotoAdapterDelegate(listener));
    }


    public void loadMore(List<PhotoDTO> list) {
        int itemCount = getItemCount();
        items.addAll(list);
        notifyItemRangeInserted(itemCount, getItemCount());
    }
}
