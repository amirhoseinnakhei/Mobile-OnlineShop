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

public class HeadphoneActivity extends AppCompatActivity {

    ImageView go5,go6,backhead;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_headphone);


        go5 = findViewById(R.id.go5);
        go5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeadphoneActivity.this,DetailsActivity5.class);
                startActivity(intent);
            }
        });

        go6 = findViewById(R.id.go6);
        go6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeadphoneActivity.this,DetailsActivity6.class);
                startActivity(intent);
            }
        });

        backhead = findViewById(R.id.backhead);
        backhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}