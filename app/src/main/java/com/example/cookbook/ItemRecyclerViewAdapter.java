package com.example.cookbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewHolder> {

    public static final String NAME ="name" ;
    public static final String ID ="id" ;
    public static final String IMAGE ="image" ;
    private List<Item> meals=new ArrayList<>();
    private Context context=null;

    public ItemRecyclerViewAdapter(Context mContext, List<Item> list){
        meals=list;
        context=mContext;
    }

    @NonNull
    @Override
    public ItemRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_view,parent,false);
        ItemRecyclerViewHolder viewHolder=new ItemRecyclerViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ItemRecyclerViewHolder holder, int position) {
        String url =meals.get(position).getStrMealThumb();
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.dinner)
                .into(holder.imageView);
        holder.name_tv.setText(meals.get(position).getStrMeal());
        holder.favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Add to favourite list
                    String name = meals.get(position).getStrMeal();
                    String id = meals.get(position).getIdMeal();
                DbItem newMeal = new DbItem(id,name,url);
                ((MainActivity)context).addIntoFav(newMeal);
                Toast.makeText(context,"Added To The Favourite Section",Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("meal_id", meals.get(position).getIdMeal());//where customer is an instance of ItemData object
                context.startActivity(intent);
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
//                            Pair.create(holder.imageView,
//                                    holder.imageView.getTransitionName()));
//                    context.startActivity(intent,options.toBundle());
//                }else {
//
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void updateList(List<Item> newList){
        meals = newList;
        notifyDataSetChanged();
    }
}
