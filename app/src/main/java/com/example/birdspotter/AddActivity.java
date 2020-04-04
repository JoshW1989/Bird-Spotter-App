package com.example.birdspotter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
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

    private EditText editTextName;
    private EditText editTextDesc;
    private EditText editTextLoc;
    private EditText editTextDateD;
    private EditText editTextDateM;
    private EditText editTextDateY;
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
        }


        editTextDesc = findViewById(R.id.edit_text_desc);
        editTextLoc = findViewById(R.id.edit_text_loc);
        editTextDateD = findViewById(R.id.edit_text_DateD);
        editTextDateM = findViewById(R.id.edit_text_DateM);
        editTextDateY = findViewById(R.id.edit_text_DateY);


        // here
        // Sets the back symbol as a button to go to home (this change is program wide)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add new bird");

    }

    // Adds a bird to the database and navigates to the landing page
    private void SaveBird(){




            String desc = editTextDesc.getText().toString();
            String loc = editTextLoc.getText().toString();
            String dateD = editTextDateD.getText().toString();
            String dateM = editTextDateM.getText().toString();
            String dateY = editTextDateY.getText().toString();
            String date = dateD + "/" + dateM + "/" + dateY;

            //used for date check
            Integer intdateY = Integer.parseInt(dateY);
            Integer intdateM = Integer.parseInt(dateM);
            Integer intdateD = Integer.parseInt(dateD);
            Calendar currentC = Calendar.getInstance();

            // Sets the calendar to todays date
            currentC.set(Calendar.HOUR_OF_DAY, 23);
            currentC.set(Calendar.MINUTE, 59);
            currentC.set(Calendar.SECOND, 59);
            currentC.set(Calendar.MILLISECOND, 99999999);
            Date currentDate = currentC.getTime();

            //Reuse the previous calendar and uses it to set entered date
            currentC.set(Calendar.YEAR, intdateY);
            currentC.set(Calendar.MONTH, intdateM);
            currentC.set(Calendar.DAY_OF_MONTH, intdateD);
            Date dateCheck = currentC.getTime();

            if (dateCheck.before(currentDate)){
                Intent intent = new Intent(AddActivity.this, LandingActivity.class);
                Bird bird = new Bird(submitName,desc,loc,date);
                birdViewModel.insert(bird);

                Toast.makeText(this,"Sighting added", Toast.LENGTH_LONG).show();

                startActivity(intent);}

            else {
                Toast.makeText(this, "Date is incorrect", Toast.LENGTH_LONG).show();
            }

        }


    // OnClick function for the submit button
    public void submitBird(View view) {
        SaveBird();
    }

    public void birdClick(View view) {
        TextView birdClicked = view.findViewById(R.id.birdnametext);
        submitName = birdClicked.getText().toString();
    }
}
