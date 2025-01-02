package com.example.opalshop.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.opalshop.Product;
import com.example.opalshop.R;

public class DetailsActivity3 extends BaseDetailActivity {

    ImageView back3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details3);
        back3 = findViewById(R.id.back3);
        Button cartButton = findViewById(R.id.cartBtn3);
        Button increaseButton = findViewById(R.id.increase_button3);
        Button decreaseButton = findViewById(R.id.decrease_button3);
        TextView quantityText = findViewById(R.id.quantity_text3);

        back3.setOnClickListener(new View.OnClickListener() {
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
            Product product = new Product("Lenovo - LOQ", 627.0, quantity[0], R.drawable.laptop3);
            addToCart(product);
        });
    }

}
