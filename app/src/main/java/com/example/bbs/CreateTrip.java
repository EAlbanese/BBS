package com.example.bbs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CreateTrip extends AppCompatActivity {

    public static final String currentLocation = "com.example.bbs.currentLocation";
    public static final String destinationLocation = "com.example.bbs.destinationLocation";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);


    }


    public void destinationInput(View view){
        EditText destinationTextView = findViewById(R.id.destinationTextView);

        Intent intent = new Intent(this, NewTrip.class);
        intent.putExtra(destinationLocation, destinationTextView.getText().toString());
        startActivity(intent);
    }

}
