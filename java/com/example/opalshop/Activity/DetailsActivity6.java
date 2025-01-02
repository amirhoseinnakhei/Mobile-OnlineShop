package com.example.opalshop.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.opalshop.Product;
import com.example.opalshop.R;

public class DetailsActivity6 extends BaseDetailActivity {

    ImageView back6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details6);
        back6 = findViewById(R.id.back6);
        Button cartButton = findViewById(R.id.cartBtn6);
        Button increaseButton = findViewById(R.id.increase_button6);
        Button decreaseButton = findViewById(R.id.decrease_button6);
        TextView quantityText = findViewById(R.id.quantity_text6);

        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // مقدار اولیه
        int[] quantity = {1}; // استفاده از آرایه برای امکان تغییر مقدار در Listener ها

        // مدیریت دکمه افزایش
        increaseButton.setOnClickListener(v -> {
            quantity[0]++;
            quantityText.setText(String.valueOf(quantity[0]));
        });

        // مدیریت دکمه کاهش
        decreaseButton.setOnClickListener(v -> {
            if (quantity[0] > 1) {
                quantity[0]--;
                quantityText.setText(String.valueOf(quantity[0]));
            }
        });

        // دکمه افزودن به سبد خرید
        cartButton.setOnClickListener(v -> {
            Product product = new Product("Headphone TSCO", 350.0, quantity[0], R.drawable.headphone2);
            addToCart(product);
        });
    }

}
