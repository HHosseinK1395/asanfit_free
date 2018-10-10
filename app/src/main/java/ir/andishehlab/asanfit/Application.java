package ir.andishehlab.asanfit;

import android.content.Context;

import Others.Settings;

public class Application extends android.app.Application {

    public static final int PERMISSION_READ_STATE = 112, PERMISSION_PHONE_CALL = 113;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

//        Global.bProfileImageChanged = true;
        Settings.getInstance().loadAll();
//        EventBus.getDefault().register(this);

        Settings.getInstance().setStrNotificationMessage("");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        EventBus.getDefault().unregister(this);
    }
}
