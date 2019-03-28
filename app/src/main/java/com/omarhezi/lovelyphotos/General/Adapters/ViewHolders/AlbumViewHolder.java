package com.omarhezi.lovelyphotos.General.Adapters.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;
import com.omarhezi.lovelyphotos.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    public interface IAlbumEventsListener {
        void onAlbumClick(AlbumDTO albumDTO, View albumView);
    }

    @BindView(R.id.card_vw_item_album_rootVw)
    CardView mCardVwItemAlbumRootVw;
    @BindView(R.id.txt_vw_item_album_albumTitle)
    TextView mTxtVwItemAlbumAlbumTitle;

    private IAlbumEventsListener mListener;

    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBindViewHolder(AlbumDTO albumDTO) {
        mCardVwItemAlbumRootVw.setCardBackgroundColor(albumDTO.getColor());
        mTxtVwItemAlbumAlbumTitle.setText(albumDTO.getTitle());
        mCardVwItemAlbumRootVw.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onAlbumClick(albumDTO, view);
            }
        });
    }

    public void setListener(IAlbumEventsListener listener) {
        mListener = listener;
    }
}

