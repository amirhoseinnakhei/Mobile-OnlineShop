package com.example.opalshop.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.opalshop.R;
import com.example.opalshop.fragment.HomeFragment;

public class LaptopActivity extends AppCompatActivity {

    ImageView go1,go2,go3,go4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_laptop);

        go1 = findViewById(R.id.go1);
        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaptopActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });

        go2 = findViewById(R.id.go2);
        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaptopActivity.this,DetailsActivity2.class);
                startActivity(intent);
            }
        });

        go3 = findViewById(R.id.go3);
        go3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaptopActivity.this,DetailsActivity3.class);
                startActivity(intent);
            }
        });

        go4 = findViewById(R.id.go4);
        go4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaptopActivity.this,DetailsActivity4.class);
                startActivity(intent);
            }
        });


    }
}