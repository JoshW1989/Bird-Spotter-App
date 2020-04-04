package com.example.birdspotter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText textName;
    private EditText textPass;

    public final static String SHARED_PREF = "sharedPrefs";
    public final static String USER_NAME = "userName";
    public final static String USER_PASS = "userPass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textName = findViewById(R.id.reg_name);
        textPass = findViewById(R.id.reg_password);

        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Register Account");

    }

    // On click even for the register button
    public void createAccount(View view) {
        registerUser();
    }

    // Saves user data into shared preferences
    public void registerUser() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER_NAME, textName.getText().toString());
        editor.putString(USER_PASS, textPass.getText().toString());

        editor.apply();

        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);

        Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show();

    }



}
