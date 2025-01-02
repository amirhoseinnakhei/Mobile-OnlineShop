package com.example.opalshop.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.opalshop.CartAdapter;
import com.example.opalshop.Product;
import com.example.opalshop.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ImageView backcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView cartListView = findViewById(R.id.cart_list_view);
        TextView totalPriceTextView = findViewById(R.id.total_price);
        Button payButton = findViewById(R.id.paybtn);

        payButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PaymentActivity.class);
            startActivityForResult(intent, 1); // انتظار نتیجه از صفحه پرداخت
        });

        updateCartUI();
        backcart = findViewById(R.id.backcart);
        backcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            boolean paymentSuccess = data.getBooleanExtra("payment_success", false);
            if (paymentSuccess) {
                // پاک کردن سبد خرید
                SharedPreferences sharedPreferences = getSharedPreferences("CartPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("cart");
                editor.apply();

                // به‌روزرسانی رابط کاربری
                updateCartUI();
            }
        }
    }

    private void updateCartUI() {
        SharedPreferences sharedPreferences = getSharedPreferences("CartPreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cart", null);

        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        List<Product> cart = json == null ? new ArrayList<>() : gson.fromJson(json, type);

        double totalPrice = 0;
        for (Product product : cart) {
            totalPrice += product.getPrice() * product.getQuantity();
        }

        // نمایش قیمت کل
        TextView totalPriceTextView = findViewById(R.id.total_price);
        totalPriceTextView.setText("Total: $" + totalPrice);

        // تنظیم آداپتور لیست سبد خرید
        CartAdapter adapter = new CartAdapter(this, cart);
        ListView cartListView = findViewById(R.id.cart_list_view);
        cartListView.setAdapter(adapter);

        adapter.notifyDataSetChanged(); // اطمینان از به‌روزرسانی لیست

        // نمایش یا پنهان کردن دکمه پرداخت بر اساس وجود محصولات در سبد خرید
        Button payButton = findViewById(R.id.paybtn);
        if (cart.isEmpty()) {
            payButton.setVisibility(View.GONE); // مخفی کردن دکمه اگر سبد خرید خالی باشد
        } else {
            payButton.setVisibility(View.VISIBLE); // نمایش دکمه اگر سبد خرید دارای محصول باشد
        }
    }
}
