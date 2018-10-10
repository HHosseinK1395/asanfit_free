package ir.andishehlab.asanfit;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Network.DataCallback;
import Network.FacadeService;
import Others.Settings;
import Structures.Notification;
import Structures.StructureNotification;
import Structures.Url;

/**
 * Created by N550J on 3/15/2018.
 */

public class NotificationService extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private FacadeService facadeService = new FacadeService();
    StructureNotification notification = new StructureNotification();
    public static int NOtIFICAtiONDELAY = 86400000; // 24 ساعت


    public static final String
            ACTION_LOCATION_BROADCAST = NotificationService.class.getName() + "LocationBroadcast",
            EXTRA_NOTIFICATION = "message_content";


    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

//            if(Helper.isAppRunning(ActivityMain.this, "ir.andishehlab.asanfit"))
//            FragmentFirstPage.getInstance().RunNotificationThread();

            GetNotification_mine(new DataCallback()
            {
                @Override
                public void onSuccess(String result) {
                    notification = new Gson().fromJson(result, StructureNotification.class);
                    if(notification != null)
                    {
                        Random randomizer = new Random();
                        Notification notify = notification.getItems().get(randomizer.nextInt(notification.getItems().size()));
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        Date date = null;
                        try
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date d = sdf.parse(notify.getValidFromTime());
                            String Date1 = output.format(d);
                            ShowToast("تاریخ جدید : " + Date1);

                            String Date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());


                            if(CompareDates(Date1, Date2))
                            {
                                ShowToast("تاریخ پیام ها درست است");
                                ShowNotification(notify.getTipMessage());
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, null, null);



            final Handler ha=new Handler();
            ha.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    //call function
                    GetNotification_mine(new DataCallback()
                    {
                        @Override
                        public void onSuccess(String result) {
                            notification = new Gson().fromJson(result, StructureNotification.class);
                            if(notification != null)
                            {
                                Random randomizer = new Random();
                                Notification notify = notification.getItems().get(randomizer.nextInt(notification.getItems().size()));
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                                Date date = null;
                                try
                                {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date d = sdf.parse(notify.getValidFromTime());
                                    String Date1 = output.format(d);
                                    ShowToast("تاریخ جدید : " + Date1);

                                    String Date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());


                                    if(CompareDates(Date1, Date2))
                                    {
                                        ShowToast("تاریخ پیام ها درست است");
                                        ShowNotification(notify.getTipMessage());
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, this, null);
                    ha.postDelayed(this, NOtIFICAtiONDELAY);
                }
            }, NOtIFICAtiONDELAY);

            //stopSelf(msg.arg1);
        }
    }

    boolean CompareDates(String Date1, String Date2)
    {
        boolean bResult = false;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = sdf.parse(Date1);
            Date date2 = sdf.parse(Date2);

            if (date1.compareTo(date2) > 0)
            {
                System.out.println("Date1 is after Date2");
                bResult = false;
            } else if (date1.compareTo(date2) < 0)
            {
                System.out.println("Date1 is before Date2");
                bResult = true;
            } else if (date1.compareTo(date2) == 0)
            {
                System.out.println("Date1 is equal to Date2");
                bResult = false;
            } else{
                System.out.println("How to get here?");
                bResult = false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return bResult;
    }

    void ShowNotification(String strnotification)
    {
        // Create intent that will bring our app to the front, as if it was tapped in the app
        // launcher
        Random rand = new Random();
        int n = rand.nextInt(123456); //

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, n + "")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("آسان فیت")
                .setContentText(strnotification)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if(!isForeground("ir.andishehlab.asanfit")) {
            int iFlag = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK;
            Intent notifyIntent = new Intent(this, ActivityMain.class);
            notifyIntent.setFlags(iFlag);
            notifyIntent.putExtra("message_content", strnotification);
            PendingIntent notifyPendingIntent = PendingIntent.getActivity
                    (
                            this,
                            0,
                            notifyIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );

            mBuilder.setContentIntent(notifyPendingIntent);
        }
        else
        {
            sendBroadcastMessage(strnotification);
        }

        Settings.getInstance().setStrNotificationMessage(strnotification);
        Settings.getInstance().saveAll();
        notificationManager.notify(Integer.parseInt(n + ""), mBuilder.build());




    }

    private void sendBroadcastMessage(String location) {
        if (location != null) {
            Intent intent = new Intent(ACTION_LOCATION_BROADCAST);
            intent.putExtra(EXTRA_NOTIFICATION, location);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    public void ShowToast(String strMessage)
    {
        //Toast.makeText(this, strMessage, Toast.LENGTH_LONG).show();
    }


    public void GetNotification_mine(final DataCallback callbbackOutput, Runnable context, final View view)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = Url.GETNOTIFICATION;


        JSONObject obj = new JSONObject();
        try {
            obj.put("ActiveState", true);
            obj.put("Sorting", "");
            obj.put("SkipCount", 0);
            obj.put("MaxResultCount", 100);
        }catch (Exception e)
        {

        }

        final JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, obj,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            callbbackOutput.onSuccess(response.get("result").toString());
                        }
                        catch (Exception e)
                        {

                        }

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json-patch+json");
                header.put("Authorization", "bearer " + Settings.getInstance().getProfile().getAuthorizationToken());
                header.put("Abp.TenantId", "" + Settings.getInstance().getHeader().getTanentId());
                return header;
            }

            @Override
            public String getBodyContentType() {
                return "application/json-patch+json";
            }

        };

        queue.add(stringRequest);
    }

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                1000);
        thread.start();

        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        //Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }

    public void GetNotification(final DataCallback callback, final Context context, final View viewl)
    {

    }

    public boolean isForeground(String myPackage) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfo = manager.getRunningTasks(1);
        ComponentName componentInfo = runningTaskInfo.get(0).topActivity;
        return componentInfo.getPackageName().equals(myPackage);
    }


}
