package com.example.bbs;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateTrip extends AppCompatActivity {

    public static final String destinationLocation = "com.example.bbs.destinationLocation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        TextView currentLocation = findViewById(R.id.startLocation);
        String startLocation = "";
        Address location = BBSService.getLocation();
        if (location != null){
            startLocation = location.getThoroughfare();
            currentLocation.setText(startLocation);
        }
    }


    public void destinationInput(View view){
        EditText destinationTextView = findViewById(R.id.destinationTextView);

        BBSService.saveDestination(destinationTextView.getText().toString());

        Intent intent = new Intent(this, NewTrip.class);
        intent.putExtra(destinationLocation, destinationTextView.getText().toString());
        startActivity(intent);
    }

}
