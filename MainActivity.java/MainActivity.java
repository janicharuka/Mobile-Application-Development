package com.example.myapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etReenterPassword;
    private RadioGroup rgGender;
    private Spinner spinnerCountry;
    private CheckBox cbAgree;
    private Button btnRegister;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etReenterPassword = findViewById(R.id.et_reenter_password);
        rgGender = findViewById(R.id.rg_gender);
        spinnerCountry = findViewById(R.id.spinner_country);
        cbAgree = findViewById(R.id.cb_agree);
        btnRegister = findViewById(R.id.btn_register);
        profileImage = findViewById(R.id.profile_image);

        // Register button click listener
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    // Proceed with registration (e.g., submit form)
                    Toast.makeText(MainActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateForm() {
        // Name validation
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            etName.setError("Please enter your name");
            return false;
        }

        // Email validation
        String email = etEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Please enter your email");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            return false;
        }

        // Password validation
        String password = etPassword.getText().toString().trim();
        String reenterPassword = etReenterPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter a password");
            return false;
        } else if (password.length() < 8) {  // Check for minimum 8 characters
            etPassword.setError("Password must be at least 8 characters");
            return false;
        } else if (!password.equals(reenterPassword)) {
            etReenterPassword.setError("Passwords do not match");
            return false;
        }

        // Gender validation
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Country validation
        if (spinnerCountry.getSelectedItemPosition() == 0) { // Assuming first item is a prompt
            Toast.makeText(this, "Please select your country", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Terms and conditions validation
        if (!cbAgree.isChecked()) {
            Toast.makeText(this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true; // All validation passed
    }
}
