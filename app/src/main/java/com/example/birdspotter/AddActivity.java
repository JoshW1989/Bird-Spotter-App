package com.example.birdspotter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.ListPreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;


import java.util.ArrayList;


public class AddActivity extends AppCompatActivity {

    // Connection to the Viewmodel to communicate with SQL Database
    private BirdViewModel birdViewModel;


    private EditText editTextDesc;
    private EditText editTextLoc;
    private EditText editTextDateD;
    private EditText editTextDateM;
    private EditText editTextDateY;
    public TextView selectedText;
    public  String   submitName;


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        String birds[] = getResources().getStringArray(R.array.birds);
        setContentView(R.layout.activity_add);

        birdViewModel = ViewModelProviders.of(this).get(BirdViewModel.class);

        LinearLayout gallery = findViewById(R.id.gallery);

        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < 6; i++) {

            View view = inflater.inflate(R.layout.bird_scroll, gallery, false);

            TextView birdname = view.findViewById(R.id.birdnametext);
            birdname.setText(birds[i]);


            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.birdpics + i);

            gallery.addView(view);

            if (i == 0) {
                selectedText = birdname;
            }

        }


        editTextDesc = findViewById(R.id.edit_text_desc);
        editTextLoc = findViewById(R.id.edit_text_loc);
        editTextDateD = findViewById(R.id.edit_text_DateD);
        editTextDateM = findViewById(R.id.edit_text_DateM);
        editTextDateY = findViewById(R.id.edit_text_DateY);


        // Sets the back symbol as a button to go to home (this change is program wide)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add new bird");

    }

    // Adds a bird to the database and navigates to the landing page
    private void SaveBird() {
        String desc = editTextDesc.getText().toString();
        String loc = editTextLoc.getText().toString();
        String dateD = editTextDateD.getText().toString();
        String dateM = editTextDateM.getText().toString();
        String dateY = editTextDateY.getText().toString();
        String date = dateD + "/" + dateM + "/" + dateY;

        //Validates user entry for logging bird fields
        //Ensures fields are not blank
        if (editTextDesc.getText().toString().contentEquals("") &&
                editTextLoc.getText().toString().contentEquals("") &&
                (editTextDateD.getText().toString().contentEquals("") ||
                        editTextDateM.getText().toString().contentEquals("") ||
                        editTextDateY.getText().toString().contentEquals(""))) {
            Toast.makeText(this, "Desc/Date/Location cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextDesc.getText().toString().contentEquals("") &&
                editTextLoc.getText().toString().contentEquals("")) {
            Toast.makeText(this, "Desc/Location cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextDesc.getText().toString().contentEquals("") &&
                (editTextDateD.getText().toString().contentEquals("") ||
                        editTextDateM.getText().toString().contentEquals("") ||
                        editTextDateY.getText().toString().contentEquals(""))) {
            Toast.makeText(this, "Desc/Date cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextLoc.getText().toString().contentEquals("") &&
                (editTextDateD.getText().toString().contentEquals("") ||
                        editTextDateM.getText().toString().contentEquals("") ||
                        editTextDateY.getText().toString().contentEquals(""))) {
            Toast.makeText(this, "Desc/Location/Date cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextDesc.getText().toString().contentEquals("")) {
            Toast.makeText(this, "Desc cannot be blank!", Toast.LENGTH_LONG).show();
        } else if ((editTextDateD.getText().toString().contentEquals("") ||
                editTextDateM.getText().toString().contentEquals("") ||
                editTextDateY.getText().toString().contentEquals(""))) {
            Toast.makeText(this, "Date cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextDesc.getText().toString().contentEquals("") &&
                editTextLoc.getText().toString().contentEquals("")) {
            Toast.makeText(this, "Desc/Location cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextDesc.getText().toString().contentEquals("") &&
                (editTextDateD.getText().toString().contentEquals("") &&
                        editTextDateM.getText().toString().contentEquals("") &&
                        editTextDateY.getText().toString().contentEquals(""))) {
            Toast.makeText(this, "Desc/Date cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextLoc.getText().toString().contentEquals("") &&
                (editTextDateD.getText().toString().contentEquals("") ||
                        editTextDateM.getText().toString().contentEquals("") ||
                        editTextDateY.getText().toString().contentEquals(""))) {
            Toast.makeText(this, "Location/Date cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextDesc.getText().toString().contentEquals("")) {
            Toast.makeText(this, "Desc cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextLoc.getText().toString().contentEquals("")) {
            Toast.makeText(this, "Location cannot be blank!", Toast.LENGTH_LONG).show();
        } else if (editTextDateD.getText().toString().contentEquals("") ||
                editTextDateM.getText().toString().contentEquals("") ||
                editTextDateY.getText().toString().contentEquals("")) {
            Toast.makeText(this, "Date cannot be blank!", Toast.LENGTH_LONG).show();
        } else {

            //used for date check
            Integer intdateY = Integer.parseInt(dateY);
            Integer intdateM = Integer.parseInt(dateM);
            Integer intdateD = Integer.parseInt(dateD);


            Calendar currentC = Calendar.getInstance();
            // Sets the calendar to todays date
            currentC.set(Calendar.HOUR_OF_DAY, 23);
            currentC.set(Calendar.MINUTE, 59);
            Date currentDate = currentC.getTime();



            Calendar givenC = Calendar.getInstance();

            //Reuse the previous calendar and uses it to set entered date
            givenC.set(Calendar.YEAR, intdateY);
            givenC.set(Calendar.MONTH, intdateM - 1);
            givenC.set(Calendar.DAY_OF_MONTH, intdateD);
            Date dateCheck = givenC.getTime();

            if (dateCheck.before(currentDate)) {
                Intent intent = new Intent(AddActivity.this, LandingActivity.class);
                Bird bird = new Bird(submitName, desc, loc, date);
                birdViewModel.insert(bird);

                Toast.makeText(this, "Sighting added", Toast.LENGTH_LONG).show();

                startActivity(intent);
            } else {
                Toast.makeText(this, "Date is incorrect", Toast.LENGTH_LONG).show();
            }

        }
    }

    // OnClick function for the submit button
    public void submitBird(View view) {
        SaveBird();
    }

    public void birdClick(View view) {

        // Set the current selectedText (assigned to the 1st in list as default) to normal.
        selectedText.setAllCaps(false);
        selectedText.setTypeface(null, Typeface.NORMAL);


        // Assign selectedText to the one just clicked
        selectedText = view.findViewById(R.id.birdnametext);

        // Add the new values
        selectedText.setTypeface(null,Typeface.BOLD_ITALIC);
        selectedText.setAllCaps(true);

        // Pass to the database or submission
        submitName = selectedText.getText().toString();
    }
}
