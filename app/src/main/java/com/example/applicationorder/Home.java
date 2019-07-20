package com.example.applicationorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Home extends AppCompatActivity implements InterOnClickFood{


    private static final String TAG = "Home";
    BottomNavigationView navigationView;
    String userName;
    Food foodshow;
    int countFood=0;
    int type=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final ActionBar toolbar= getSupportActionBar();
        userName= getIntent().getStringExtra("name");
        navigationView= findViewById(R.id.navigation);
        getFragment(HomeFragment.newInstance(type));
        toolbar.setTitle("Hello "+userName);
        //foodshow= new Food("fasdf", "asdf", 12341234);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.itHome:{
                        getFragment(HomeFragment.newInstance(type));
                        toolbar.setTitle(R.string.home);
                        break;
                    }

                    case R.id.itCart:
                    {
                        getFragment(CartFragment.newInstance(foodshow, countFood));
                        toolbar.setTitle(R.string.cart);
                        break;

                    }
                    case R.id.Notifi:
                    {
                        getFragment(NotificationFragment.newInstance());
                        toolbar.setTitle(R.string.notif);
                        break;
                    }
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itLogOut:
                onBackPressed();
                break;
            case  R.id.itType1:
            {

                //type=1;
                Toast.makeText(getBaseContext(),R.string.complete, Toast.LENGTH_LONG).show();
                break;
            }

            case R.id.itType2:{
               // type=2;
                Toast.makeText(getBaseContext(),R.string.complete, Toast.LENGTH_LONG).show();
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void getFragment(Fragment fragment){
        try{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();


        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "getFragment: "+e.getMessage());
        }
    }

    @Override
    public void onClickFood(Food food) {
        foodshow= food;
        countFood++;

    }
}
