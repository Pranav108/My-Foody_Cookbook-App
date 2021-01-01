package com.example.cookbook;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemRecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    ImageView favImage;

    TextView name_tv;

    public ItemRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        favImage=itemView.findViewById(R.id.favourite);
        imageView=itemView.findViewById(R.id.image);
        name_tv=itemView.findViewById(R.id.name_tv);
    }
}
