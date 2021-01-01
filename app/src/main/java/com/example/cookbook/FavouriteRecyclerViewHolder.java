package com.example.cookbook;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


class FavouriteRecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    ImageView favImage;

    TextView name_tv;

    private FavouriteRecyclerViewHolder(View itemView) {
        super(itemView);
        favImage=itemView.findViewById(R.id.favourite);
        favImage.setVisibility(View.GONE);
        imageView=itemView.findViewById(R.id.image);
        name_tv=itemView.findViewById(R.id.name_tv);
    }
}