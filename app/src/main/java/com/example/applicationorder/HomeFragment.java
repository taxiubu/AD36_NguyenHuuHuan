package com.example.applicationorder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    Food food1, food2, food3, food4, food5, food6;
    List<Food> foods;
    FoodAdapter foodAdapter;
    RecyclerView recyclerView;
    int countFood=0;
    private InterOnClickFood listener;
    RecyclerView.LayoutManager layoutManager;

    public static HomeFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putSerializable("Type", type);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView= view.findViewById(R.id.rvFood);
        food1= new Food("Pizza Panda", "pizza_panda.jpg", 100000);
        food2= new Food("KFC Super", "kfc_super.jpg", 50000);
        food3= new Food("Chicken Super", "chicken_super.jpg", 200000);
        food4= new Food("Coca Cola", "coca_cola.jpg", 200000);
        food5= new Food("Cup cake", "cup_cake.jpg", 70000);
        food6= new Food("Bread eggs", "bread_eggs.jpg", 20000);
        foods= new ArrayList<>();
        foods.add(food1);
        foods.add(food2);
        foods.add(food3);
        foods.add(food4);
        foods.add(food5);
        foods.add(food6);

        int typeList= (int)getArguments().getSerializable("Type");
        if(typeList==1){
            layoutManager= new GridLayoutManager(getContext(),
                    2, RecyclerView.VERTICAL, false);
        }
        else {
            layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        }


        foodAdapter= new FoodAdapter(foods);
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setLayoutManager(layoutManager);
        foodAdapter.setInterOnClickFood(new InterOnClickFood() {
            @Override
            public void onClickFood(Food food) {
                Toast.makeText(getContext(), R.string.messClickFood, Toast.LENGTH_LONG).show();
                listener.onClickFood(food);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof InterOnClickFood){
            listener= (InterOnClickFood) context;
        }else {
            throw new RuntimeException(context.toString()+"must implement");
        }
    }

}
