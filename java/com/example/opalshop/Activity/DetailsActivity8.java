package com.example.opalshop.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.opalshop.Product;
import com.example.opalshop.R;

public class DetailsActivity8 extends BaseDetailActivity {

    ImageView back8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details8);
        back8 = findViewById(R.id.back8);
        Button cartButton = findViewById(R.id.cartBtn8);
        Button increaseButton = findViewById(R.id.increase_button8);
        Button decreaseButton = findViewById(R.id.decrease_button8);
        TextView quantityText = findViewById(R.id.quantity_text8);

        back8.setOnClickListener(new View.OnClickListener() {
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
            Product product = new Product("Server ASUS", 980.0, quantity[0], R.drawable.server2);
            addToCart(product);
        });
    }

}
