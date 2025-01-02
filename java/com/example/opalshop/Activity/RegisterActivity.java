package com.example.opalshop.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.opalshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences ;


    private static final String TAG = "RegisterActivity";
    private EditText mFullname, mEmail, mPassword, mConfirmpass;
    private Button mRegisterbtn;
    private Button mLoginbtn;
    private FirebaseAuth fAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeViews();
        setupListeners();
        fAuth = FirebaseAuth.getInstance();

        //For Just one see the Slider Page
        sharedPreferences = getSharedPreferences("onboardingscreen",MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("firsttime" , true);
        if (isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firsttime",false);
            editor.commit();
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void initializeViews() {
        mFullname = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mConfirmpass = findViewById(R.id.confirmPass);
        mRegisterbtn = findViewById(R.id.registerBtn);
        mLoginbtn = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupListeners() {
        mRegisterbtn.setOnClickListener(v -> attemptRegistration());
        mLoginbtn.setOnClickListener(v -> startActivitySecond());
        /*if (mLoginbtn != null) {
            mLoginbtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));
        } else {
            Log.e(TAG, "Login button not found in layout");
        }*/

    }

    private void attemptRegistration() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String fullname = mFullname.getText().toString().trim();
        String confirmPass = mConfirmpass.getText().toString().trim();

        if (!validateInputs(fullname, email, password, confirmPass)) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        registerUser(email, password);
    }

    private boolean validateInputs(String fullname, String email, String password, String confirmPass) {
        if (TextUtils.isEmpty(fullname)) {
            mFullname.setError("Full Name is Required");
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email is Required");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password is Required");
            return false;
        }
        if (password.length() < 6) {
            mPassword.setError("Must be 6 characters");
            return false;
        }
        if (confirmPass.isEmpty() || !password.equals(confirmPass)) {
            mConfirmpass.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void registerUser(String email, String password) {
        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                sendVerificationEmail();
                startActivitySecond();
            } else {
                Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        });
    }

    private void sendVerificationEmail() {
        FirebaseUser fuser = fAuth.getCurrentUser();
        if (fuser != null) {
            fuser.sendEmailVerification()
                    .addOnSuccessListener(aVoid -> Toast.makeText(RegisterActivity.this, "Verification Email sent.", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Log.d(TAG, "onFailure: Email not sent " + e.getMessage()));
        }
    }

    private void startActivitySecond() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
