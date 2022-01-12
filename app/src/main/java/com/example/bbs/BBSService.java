package com.example.bbs;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
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
