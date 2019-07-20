package com.example.applicationorder;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.applicationorder.R.color.colorWhite;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.Viewhoder> {
    List<Food> foodList;
    InterOnClickFood interOnClickFood;
    public FoodAdapter(List<Food> foodList){
        this.foodList= foodList;
    }

    public void setInterOnClickFood(InterOnClickFood interOnClickFood) {
        this.interOnClickFood = interOnClickFood;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.item_food, parent, false);
        Viewhoder viewhoder= new Viewhoder(view);
        return viewhoder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        final Food food= foodList.get(position);

        holder.tvName.setText(food.getNameFood());
        holder.tvPrice.setText(String.valueOf(food.getPrice())+" VND");
        switch (food.getImage()){
            case "pizza_panda.jpg":
                holder.ivFood.setImageResource(R.drawable.pizza_panda);
                break;
            case "kfc_super.jpg":
                holder.ivFood.setImageResource(R.drawable.kfc);
                break;
            case "bread_eggs.jpg":
                holder.ivFood.setImageResource(R.drawable.bread_eggs);
                break;
            case "chicken_super.jpg":
                holder.ivFood.setImageResource(R.drawable.chicken_super);
                break;
            case "cup_cake.jpg":
                holder.ivFood.setImageResource(R.drawable.cup_cake);
                break;
            case "coca_cola.jpg":
                holder.ivFood.setImageResource(R.drawable.coca_cola);
                break;
            case "":
                holder.ivFood.setBackgroundColor(colorWhite);
                break;
        }
        holder.ivFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interOnClickFood.onClickFood(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView ivFood;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);

            tvName= itemView.findViewById(R.id.tvNameFood);
            tvPrice= itemView.findViewById(R.id.tvPriceFood);
            ivFood= itemView.findViewById(R.id.ivFood);
        }
    }
}
