package com.example.opalshop.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.opalshop.Product;
import com.example.opalshop.R;

public class DetailsActivity7 extends BaseDetailActivity {

    ImageView back7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details7);
        back7 = findViewById(R.id.back7);


        back7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // مقدار اولیه



    }

}
