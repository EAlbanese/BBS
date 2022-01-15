package com.example.bbs;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Address;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewTrip extends AppCompatActivity {

        com.example.bbs.BBSService BBSService;
        boolean mBound = false;

        private TextView destinationValueTextView;
        private TextView currentDateValueTextView;
        public static final String destinationLocation = "com.example.bbs.destinationLocation";
        public static String currentTime = "";
        public static String destinationTime = "";

        ImageButton saveButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_newtrip);

            destinationValueTextView = findViewById(R.id.destinationSet);
            currentDateValueTextView = findViewById(R.id.currentDate);
            saveButton = findViewById(R.id.saveTrip);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel channel = new NotificationChannel("Save notification", "Save notification", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }

            TextView currentLocation = findViewById(R.id.currentLocationSet);
            String startLocation = "";
            Address location = BBSService.getLocation();
            if (location != null){
                startLocation = location.getThoroughfare();
                currentLocation.setText(startLocation);
            }

            TextView startTime= findViewById(R.id.timeStart);
            TextView endTime = findViewById(R.id.timeEnd);
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, 40);
            startTime.setText(dateFormat.format(date));
            endTime.setText(dateFormat.format(calendar.getTime()));

            currentTime = dateFormat.format(date);
            destinationTime = dateFormat.format(calendar.getTime());
        }

        public void backToOverview(View view){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        public void saveTrip(View view){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(NewTrip.this, "Save notification")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Trip saved")
                    .setContentText("Your trip was successfully saved. We wish you a pleasant journey.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NewTrip.this);
            managerCompat.notify(1, builder.build());

            BBSService.saveTraveltime(currentTime, destinationTime);

            TextView destinationTextView = findViewById(R.id.destinationSet);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(destinationLocation, destinationTextView.getText().toString());


            startActivity(intent);
        }

        public void start(){

            Date dateNow = new Date();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - HH:mm");

            Intent intent = getIntent();

            String destinationSet = intent.getStringExtra(CreateTrip.destinationLocation);
            String currentDateSet = dateFormat.format(dateNow);

            TextView destination = findViewById(R.id.destinationSet);
            destination.setText(destinationSet);

            TextView currentDateTime = findViewById(R.id.currentDate);
            currentDateTime.setText(currentDateSet);
        }

        @Override
        protected void onStart() {
            super.onStart();
            Intent intentbbs = new Intent(this, BBSService.class);
            bindService(intentbbs, connection, Context.BIND_AUTO_CREATE);

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
