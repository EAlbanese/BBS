package com.example.bbs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTrip extends AppCompatActivity {

        com.example.bbs.BBSService BBSService;
        boolean mBound = false;

        private TextView currentLocationValueTextView;
        private TextView destinationValueTextView;
        private TextView currentDateValueTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_newtrip);

            currentLocationValueTextView = findViewById(R.id.currentLocationSet);
            destinationValueTextView = findViewById(R.id.destinationSet);
            currentDateValueTextView = findViewById(R.id.currentDate);

        }

        public void backToOverview(View view){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        public void saveTrip(View view){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        public void start(){

            Date dateNow = new Date();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - HH:mm");

            Intent intent = getIntent();

            String currentLocationSet = intent.getStringExtra(CreateTrip.currentLocation);
            String destinationSet = intent.getStringExtra(CreateTrip.destinationLocation);
            String currentDateSet = dateFormat.format(dateNow);

            TextView destination = findViewById(R.id.destinationSet);
            destination.setText(destinationSet);

            TextView startLocation = findViewById(R.id.currentLocationSet);
            startLocation.setText(currentLocationSet);

            TextView currentDateTime = findViewById(R.id.currentDate);
            currentDateTime.setText(currentDateSet);
        }

        @Override
        protected void onStart() {
            super.onStart();
            Intent intentbmi = new Intent(this, BBSService.class);
            bindService(intentbmi, connection, Context.BIND_AUTO_CREATE);

        }

        @Override
        protected void onStop() {
            super.onStop();
            unbindService(connection);
            mBound = false;
        }

        private ServiceConnection connection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName className, IBinder service) {
                com.example.bbs.BBSService.LocalBinder binder = (com.example.bbs.BBSService.LocalBinder) service;
                BBSService = binder.getService();
                mBound = true;
                start();
            }

            @Override
            public void onServiceDisconnected(ComponentName arg0) {
                mBound = false;
            }
        };
    }
