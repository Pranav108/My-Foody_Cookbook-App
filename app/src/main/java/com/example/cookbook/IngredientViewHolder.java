package com.example.cookbook;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientViewHolder extends RecyclerView.ViewHolder {

    TextView ingredient;

    TextView measurement;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        ingredient=itemView.findViewById(R.id.ingredient_tv);
        measurement=itemView.findViewById(R.id.measurement_tv);
    }
}
