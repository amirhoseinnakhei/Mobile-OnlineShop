package com.example.opalshop.fragment;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.opalshop.Activity.CartActivity;
import com.example.opalshop.Activity.DetailActivity;
import com.example.opalshop.Activity.DetailsActivity2;
import com.example.opalshop.Activity.DetailsActivity3;
import com.example.opalshop.Activity.DetailsActivity4;
import com.example.opalshop.Activity.DetailsActivity5;
import com.example.opalshop.Activity.DetailsActivity6;
import com.example.opalshop.Activity.DetailsActivity7;
import com.example.opalshop.Activity.DetailsActivity8;
import com.example.opalshop.Activity.HeadphoneActivity;
import com.example.opalshop.Activity.LaptopActivity;
import com.example.opalshop.Activity.ServerActivity;
import com.example.opalshop.R;

import org.json.JSONArray;
import org.json.JSONException;

public class HomeFragment extends Fragment {

    private ImageView[] like, dlike;

    RelativeLayout relativeLayout1,relativeLayout2,relativeLayout3,relativeLayout4,relativeLayout5,relativeLayout6,relativeLayout7,relativeLayout8;

    private LinearLayout cat1,cat2,cat3,cat4;

    EditText edittext1;

    ImageView cartIcon,search ;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private void saveLikeStatus(int itemId, boolean isLiked) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("like_status_" + itemId, isLiked);
        editor.apply();
    }

    private boolean getLikeStatus(int itemId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        return prefs.getBoolean("like_status_" + itemId, false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dlike = new ImageView[]{
                view.findViewById(R.id.dlike1),
                view.findViewById(R.id.dlike2),
                view.findViewById(R.id.dlike3),
                view.findViewById(R.id.dlike4),
                view.findViewById(R.id.dlike5),
                view.findViewById(R.id.dlike6),
                view.findViewById(R.id.dlike7),
                view.findViewById(R.id.dlike8),
        };

        like = new ImageView[]{
                view.findViewById(R.id.like1),
                view.findViewById(R.id.like2),
                view.findViewById(R.id.like3),
                view.findViewById(R.id.like4),
                view.findViewById(R.id.like5),
                view.findViewById(R.id.like6),
                view.findViewById(R.id.like7),
                view.findViewById(R.id.like8),
        };

        for (int i = 0; i < dlike.length; i++) {
            final int index = i;
            dlike[i].setOnClickListener(v -> {
                like[index].setVisibility(View.VISIBLE);
                dlike[index].setVisibility(View.GONE);
                saveLikedItem(index);
            });

            like[i].setOnClickListener(v -> {
                like[index].setVisibility(View.GONE);
                dlike[index].setVisibility(View.VISIBLE);
                removeLikedItem(index);
            });
        }

        relativeLayout1 = view.findViewById(R.id.relative1);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

        relativeLayout2 = view.findViewById(R.id.relative2);
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DetailsActivity2.class);
                startActivity(intent);
            }
        });

        relativeLayout3 = view.findViewById(R.id.relative3);
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DetailsActivity3.class);
                startActivity(intent);
            }
        });

        relativeLayout4 = view.findViewById(R.id.relative4);
        relativeLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DetailsActivity4.class);
                startActivity(intent);
            }
        });

        relativeLayout5 = view.findViewById(R.id.relative5);
        relativeLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DetailsActivity5.class);
                startActivity(intent);
            }
        });

        relativeLayout6 = view.findViewById(R.id.relative6);
        relativeLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DetailsActivity6.class);
                startActivity(intent);
            }
        });

        relativeLayout7 = view.findViewById(R.id.relative7);
        relativeLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DetailsActivity7.class);
                startActivity(intent);
            }
        });

        relativeLayout8 = view.findViewById(R.id.relative8);
        relativeLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), DetailsActivity8.class);
                startActivity(intent);
            }
        });

        cat1 = view.findViewById(R.id.cat1);
        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), LaptopActivity.class);
                startActivity(intent);
            }
        });


        cat2 = view.findViewById(R.id.cat2);
        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), HeadphoneActivity.class);
                startActivity(intent);
            }
        });

        cat3 = view.findViewById(R.id.cat3);
        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), ServerActivity.class);
                startActivity(intent);
            }
        });

        cat4 = view.findViewById(R.id.cat4);
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireActivity(), "SOON !", Toast.LENGTH_SHORT).show();
            }
        });

        cartIcon = view.findViewById(R.id.cartIcon);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), CartActivity.class);
                startActivity(intent);
            }
        });



        edittext1 = view.findViewById(R.id.edittext1);
        search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namep ;
                namep = edittext1.getText().toString();

                if (namep.equals("laptop")) {
                    Intent intent = new Intent(requireActivity(),LaptopActivity.class);
                    startActivity(intent);
                } else if (namep.equals("Asus") || namep.equals("asus")){
                    Intent intent = new Intent(requireActivity(),DetailActivity.class);
                    startActivity(intent);
                } else if (namep.equals("headphone") || namep.equals("Headphone")){
                    Intent intent = new Intent(requireActivity(),HeadphoneActivity.class);
                    startActivity(intent);
                } else if (namep.equals("Server") || namep.equals("server")){
                    Intent intent = new Intent(requireActivity(),ServerActivity.class);
                    startActivity(intent);
                }else if (namep.equals("lenovo") || namep.equals("Lenovo")){
                    Intent intent = new Intent(requireActivity(),DetailsActivity3.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(requireActivity(), "Not Find TryAgain !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void saveLikedItem(int itemId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String likedItems = prefs.getString("liked_items", "[]");
        try {
            JSONArray jsonArray = new JSONArray(likedItems);
            if (!jsonArray.toString().contains(String.valueOf(itemId))) {
                jsonArray.put(itemId);
                prefs.edit().putString("liked_items", jsonArray.toString()).apply();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void removeLikedItem(int itemId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String likedItems = prefs.getString("liked_items", "[]");
        try {
            JSONArray jsonArray = new JSONArray(likedItems);
            JSONArray newArray = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getInt(i) != itemId) {
                    newArray.put(jsonArray.getInt(i));
                }
            }
            prefs.edit().putString("liked_items", newArray.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
