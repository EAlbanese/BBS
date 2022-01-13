package com.example.bbs;

import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

public class BBSService extends Service {
    private final IBinder binder = new LocalBinder();

    public static Address address;

    public static void saveLocation(Address addresses) {
        address = addresses;
    }

    public static Address getLocation(){
        return address;
    }

    public class LocalBinder extends Binder {
        public BBSService getService() {
            return BBSService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
