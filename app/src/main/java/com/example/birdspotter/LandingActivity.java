package com.example.birdspotter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// Landing activity functions (navigates to other pages)
public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void goBirdView(View view) {
        Intent intent = new Intent(LandingActivity.this, BirdViewActivity.class);
        startActivity(intent);

    }

    public void goAddView(View view) {
        Intent intent = new Intent(LandingActivity.this, AddActivity.class);
        startActivity(intent);
    }

    public void signOut(View view) {
        Intent intent = new Intent(LandingActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
