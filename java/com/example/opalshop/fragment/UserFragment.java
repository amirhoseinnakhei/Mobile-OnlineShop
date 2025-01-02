package com.example.opalshop.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opalshop.Activity.LoginActivity;
import com.example.opalshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    private TextView textViewUserName;
    private Button buttonLogOut;

    private FirebaseAuth mAuth;

    LinearLayout update, support, address , method;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Bind views
        textViewUserName = rootView.findViewById(R.id.text_view_user_name);
        buttonLogOut = rootView.findViewById(R.id.btn_logout);

        // Check if the user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User is signed in
            String userName = currentUser.getDisplayName();
            String userEmail = currentUser.getEmail();

            // If display name exists, show it, otherwise show the email
            if (userName != null && !userName.isEmpty()) {
                textViewUserName.setText("Welcome, " + userName);
            } else if (userEmail != null && !userEmail.isEmpty()) {
                textViewUserName.setText("Hi , " + userEmail);
            } else {
                textViewUserName.setText("Welcome, User");
            }
        } else {
            // No user is signed in, show message
            textViewUserName.setText("No user logged in");
        }

        // Set up log out button
        buttonLogOut.setOnClickListener(v -> {
            // Log out the user
            mAuth.signOut();
            Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
            textViewUserName.setText("No user logged in");

            Intent intent = new Intent(getActivity(), LoginActivity.class); // تغییر به اکتیویتی لاگین
            startActivity(intent);
            getActivity().finish();
        });

        update = rootView.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireActivity(), "No Update Available !", Toast.LENGTH_SHORT).show();
            }
        });

        support = rootView.findViewById(R.id.support);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireActivity(), "@iAmirHosein_Ha", Toast.LENGTH_SHORT).show();
            }
        });

        address = rootView.findViewById(R.id.address);

        // Set OnClickListener for the button
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddressSelectionDialog();
            }
        });

        method = rootView.findViewById(R.id.method);

        // Set OnClickListener for the button
        method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay();
            }
        });

        return rootView;
    }

    private void showAddressSelectionDialog() {
        final String[] addresses = {"Kerman", "Tehran"};

        // Create an AlertDialog with the list of addresses
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Select Address :");
        builder.setItems(addresses, (dialog, which) -> {
            // Get the selected address and show a Toast
            String selectedAddress = addresses[which];
            Toast.makeText(requireActivity(), "Address selected is :  " + selectedAddress, Toast.LENGTH_SHORT).show();
        });
        builder.show();
    }

    private void pay() {
        final String[] method = {"Cash", "Digital Currency"};

        // Create an AlertDialog with the list of addresses
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Payment Method :");
        builder.setItems(method, (dialog, which) -> {
            // Get the selected address and show a Toast
            String selectedMethod = method[which];
            Toast.makeText(requireActivity(), "Method selected is :  " + selectedMethod, Toast.LENGTH_SHORT).show();
        });
        builder.show();
    }
}





