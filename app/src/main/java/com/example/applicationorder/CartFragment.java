package com.example.applicationorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
    List<Food> nameFoodList;
    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    int TotalPrice=0;
    TextView tvPrice;
    Button btOrder;

    public static CartFragment newInstance(Food foodshow, int countFood) {

        Bundle args = new Bundle();
        args.putSerializable("Show", foodshow);
        args.putSerializable("Count", countFood);
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View viewCart= inflater.inflate(R.layout.cart_fragment, container, false);
        recyclerView= viewCart.findViewById(R.id.rvFoodShow);
        tvPrice= viewCart.findViewById(R.id.tvSumPrice);
        nameFoodList= new ArrayList<>();

        int count= (int) getArguments().getSerializable("Count");
        if(count==0)
            nameFoodList.add(new Food("", "", 0));
        else {

            for(int i=0; i<count; i++){
                Food foodshow= (Food) getArguments().getSerializable("Show");

                nameFoodList.add(foodshow);
            }
        }

        for(Food i: nameFoodList) {
            TotalPrice = TotalPrice + i.getPrice();
        }
        tvPrice.setText(String.valueOf(TotalPrice)+" VND");

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        foodAdapter= new FoodAdapter(nameFoodList);
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setLayoutManager(layoutManager);

        foodAdapter.setInterOnClickFood(new InterOnClickFood() {
            @Override
            public void onClickFood(final Food food) {

                PopupMenu popupMenu= new PopupMenu(getContext(), viewCart);
                popupMenu.getMenuInflater().inflate(R.menu.menu_delete, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        nameFoodList.remove(food);
                        foodAdapter.notifyDataSetChanged();
                        TotalPrice= TotalPrice- food.getPrice();
                        tvPrice.setText(String.valueOf(TotalPrice)+" VND");
                        return false;
                    }
                });
                popupMenu.show();


            }
        });


        btOrder= viewCart.findViewById(R.id.btOrder);
        btOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Order thành công, hết "+String.valueOf(TotalPrice)+" VND", Toast.LENGTH_LONG).show();
            }
        });
        return viewCart;
    }
}
