package com.example.bbs;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

public class BBSService extends Service {
    private final IBinder binder = new LocalBinder();
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
