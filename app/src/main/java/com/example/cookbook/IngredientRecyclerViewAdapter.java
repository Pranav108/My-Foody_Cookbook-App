package com.example.cookbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientViewHolder> {

    ArrayList<ArrayList<String>> mData;
    Context mContext;

    public IngredientRecyclerViewAdapter(ArrayList<ArrayList<String>> data, Context context){
        this.mData=data;
        this.mContext=context;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.ingredient_view,parent,false);
        IngredientViewHolder viewHolder=new IngredientViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {

        holder.ingredient.setText(mData.get(position).get(0));
        holder.measurement.setText(mData.get(position).get(1));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
