package com.example.opalshop.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.opalshop.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private GridLayout favoriteContainer;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        favoriteContainer = view.findViewById(R.id.favorite_container);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFavoriteItems();
    }

    private void updateFavoriteItems() {
        favoriteContainer.removeAllViews();

        List<Integer> likedItems = getLikedItems();

        // Calculate column count dynamically
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int padding = 64; // Total padding for GridLayout
        int itemSize = 470; // Fixed item size for better visibility
        int columnCount = (screenWidth - padding) / itemSize;
        columnCount = Math.max(columnCount, 1); // Ensure at least 1 column

        favoriteContainer.setColumnCount(columnCount);
        favoriteContainer.setPivotY(Gravity.CENTER); // Center align items

        for (int itemId : likedItems) {
            View itemCard = createItemCard(itemId, itemSize);
            if (itemCard != null) {
                favoriteContainer.addView(itemCard);
            }
        }
    }

    private View createItemCard(int itemId, int itemSize) {
        // Create CardView
        CardView cardView = new CardView(requireContext());
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();

        params.width = itemSize;
        params.height = itemSize;
        params.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(params);
        cardView.setRadius(16f);
        cardView.setCardElevation(8f);
        cardView.setUseCompatPadding(true);

        // Add ImageView to CardView
        ImageView itemImageView = new ImageView(requireContext());
        itemImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        int imageResource = getImageResourceForItem(itemId);
        if (imageResource != 0) {
            itemImageView.setImageResource(imageResource);
        } else {
            itemImageView.setImageResource(R.drawable.banner9); // نمایش تصویر پیش‌فرض
            Log.e("FavoriteFragment", "Image resource not found for item ID: " + itemId);
        }

        cardView.addView(itemImageView);

        return cardView;
    }

    private int getImageResourceForItem(int itemId) {
        // Map item IDs to image resources
        switch (itemId) {
            case 0:
                return R.drawable.laptop;
            case 1:
                return R.drawable.laptop2;
            case 2:
                return R.drawable.laptop3;
            case 3:
                return R.drawable.laptop4;
            case 4:
                return R.drawable.headphone;
            case 5:
                return R.drawable.headphone2;
            case 6:
                return R.drawable.server;
            case 7:
                return R.drawable.server2;
            default:
                return 0; // Return 0 if item ID is invalid
        }
    }

    private List<Integer> getLikedItems() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String likedItemsJson = prefs.getString("liked_items", "[]");
        Log.d("FavoriteFragment", "Liked items JSON: " + likedItemsJson);

        List<Integer> likedItems = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(likedItemsJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                likedItems.add(jsonArray.getInt(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return likedItems;
    }
}
