package com.example.opalshop.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.opalshop.R;
import com.example.opalshop.adapter.AdapterViewPager;
import com.example.opalshop.fragment.FavoriteFragment;
import com.example.opalshop.fragment.HomeFragment;
import com.example.opalshop.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager2 pagerMain ;

    BottomNavigationView bottomNav;
    ArrayList <Fragment> fragmentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagerMain = findViewById(R.id.pagerMain);
        bottomNav = findViewById(R.id.bottomNav);

        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new FavoriteFragment());
        fragmentArrayList.add(new UserFragment());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this , fragmentArrayList);

        pagerMain.setAdapter(adapterViewPager);
        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0 :
                        bottomNav.setSelectedItemId(R.id.itHome);
                        break;
                    case 1 :
                        bottomNav.setSelectedItemId(R.id.itFavorite);
                        break;
                    case 2 :
                        bottomNav.setSelectedItemId(R.id.itPerson);
                        break;
                }


                super.onPageSelected(position);
            }
        });
        bottomNav.setOnItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.itHome) {
                pagerMain.setCurrentItem(0);
            } else if (id == R.id.itFavorite) {
                pagerMain.setCurrentItem(1);
            } else if (id == R.id.itPerson) {
                pagerMain.setCurrentItem(2);
            }
            return true;
        });




    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearLikedItems();
    }

    private void clearLikedItems() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("liked_items", "[]"); // مقدار خالی برای آیتم‌ها
        editor.apply();
    }
}