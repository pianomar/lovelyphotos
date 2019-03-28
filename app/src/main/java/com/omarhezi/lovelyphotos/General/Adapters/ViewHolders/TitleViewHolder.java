package com.omarhezi.lovelyphotos.General.Adapters.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.omarhezi.lovelyphotos.General.DTOs.AlbumDTO;
import com.omarhezi.lovelyphotos.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TitleViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_vw_item_title)
    TextView mTxtVwItemTitle;

    public TitleViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBindViewHolder(AlbumDTO title) {
        mTxtVwItemTitle.setText(title.getTitle());
    }
}

