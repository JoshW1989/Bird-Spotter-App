package com.example.birdspotter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textName;
    private EditText textPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = findViewById(R.id.mainUserName);
        textPass = findViewById(R.id.mainPassword);

    }

    // Checks if the username and password provided matches that in shared preferences
    // Set to admin as default
    public void checkLogin(View view) {

        SharedPreferences mPrefs = getSharedPreferences("sharedPrefs",0);

        String checkName = mPrefs.getString("userName", "admin");
        String checkPass = mPrefs.getString("userPass", "admin");

        String givenName = textName.getText().toString();
        String givenPass = textPass.getText().toString();


        if (givenName.equals(checkName) && givenPass.equals(checkPass)) {
            Intent intent = new Intent(MainActivity.this, LandingActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Password incorrect", Toast.LENGTH_LONG).show();
        }
    }


    public void manageAccount(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
}
