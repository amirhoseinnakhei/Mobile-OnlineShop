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

public class ServerActivity extends AppCompatActivity {

    ImageView go7,go8;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_server);


        go7 = findViewById(R.id.go7);
        go7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServerActivity.this,DetailsActivity7.class);
                startActivity(intent);
            }
        });

        go8 = findViewById(R.id.go8);
        go8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServerActivity.this,DetailsActivity8.class);
                startActivity(intent);
            }
        });


    }
}