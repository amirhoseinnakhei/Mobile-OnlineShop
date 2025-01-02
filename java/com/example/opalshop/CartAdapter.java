package com.example.opalshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.example.opalshop.Product;
import com.example.opalshop.R;
import com.google.gson.Gson;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<Product> cartItems;

    public CartAdapter(Context context, List<Product> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }

        Product product = cartItems.get(position);

        TextView nameTextView = convertView.findViewById(R.id.productName);
        TextView priceTextView = convertView.findViewById(R.id.productPrice);
        TextView quantityTextView = convertView.findViewById(R.id.productQuantity);
        ImageView productImageView = convertView.findViewById(R.id.productImage);
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        nameTextView.setText(product.getName());
        priceTextView.setText("$" + product.getPrice());
        quantityTextView.setText("Quantity: " + product.getQuantity());
        productImageView.setImageResource(product.getImageResource());

        // حذف محصول با کاهش مقدار quantity
        deleteButton.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
            } else {
                cartItems.remove(position);
            }
            notifyDataSetChanged();
            saveCartToSharedPreferences();
        });

        return convertView;
    }

    private void saveCartToSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CartPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString("cart", gson.toJson(cartItems));
        editor.apply();
    }


}
