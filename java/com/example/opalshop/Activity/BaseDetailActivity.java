package com.example.opalshop.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.opalshop.Product;
import com.example.opalshop.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // متد اضافه کردن محصول به سبد خرید
    protected void addToCart(Product product) {
        SharedPreferences sharedPreferences = getSharedPreferences("CartPreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cart", null);

        // بازیابی لیست محصولات در سبد خرید
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        List<Product> cart = json == null ? new ArrayList<>() : gson.fromJson(json, type);

        // بررسی اینکه آیا محصول در سبد خرید وجود دارد
        boolean productExists = false;
        for (Product cartItem : cart) {
            if (cartItem.getName().equals(product.getName())) {
                // افزایش تعداد محصول
                cartItem.setQuantity(cartItem.getQuantity() + product.getQuantity());
                productExists = true;
                break;
            }
        }

        // اگر محصول وجود نداشت، آن را به لیست اضافه کنید
        if (!productExists) {
            cart.add(product);
        }

        // ذخیره لیست به‌روزرسانی شده در SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cart", gson.toJson(cart));
        editor.apply();

        // نمایش پیام موفقیت
        Toast.makeText(this, "Product added to cart!", Toast.LENGTH_SHORT).show();
    }

}
