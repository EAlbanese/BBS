package com.example.bbs;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateTrip extends AppCompatActivity {

    public static final String currentLocation = "com.example.bbs.currentLocation";
    public static final String destinationLocation = "com.example.bbs.destinationLocation";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        Address location = BBSService.getLocation();
        if (location != null){
            location.getThoroughfare();
        }
    }


    public void destinationInput(View view){
        EditText destinationTextView = findViewById(R.id.destinationTextView);

        Intent intent = new Intent(this, NewTrip.class);
        intent.putExtra(destinationLocation, destinationTextView.getText().toString());
        startActivity(intent);
    }

}
