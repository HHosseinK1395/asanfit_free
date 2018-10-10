package Network;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import Others.Settings;
import Structures.BasicInfo;
import Structures.Profile;
import Structures.StructureCustomer;
import Structures.StructurePurchase;
import Structures.StructureWeightForUpdate;
import Structures.Url;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.NotificationService;

class ApiClinet {

    //private String url;
    //private int port;

    public static Activity activity;

    private void SetProperties(String url, int port) {
        //this.url = url;
        //this.port = port;
    }

    private void SetPropetires(String url){
        SetProperties(url,1234);
    }

    public ApiClinet(String url, int port) {
        SetProperties(url, port);
    }

    public ApiClinet(String url) {
        SetProperties(url, 1234);
    }

    public void Post(String param) {

    }

    public void Put(String param) {

    }

    public void Get(String param) {

    }

}

final class UsersService extends BaseService
{
    public UsersService()
    {
        super(new ApiClinet("mehrnaz.com/Users")
        {

        });
    }

    //1 -> OK
    public void SendPhoneNumber(final DataCallback callback, final Context context, final View view, final String mobile) throws JSONException, UnsupportedEncodingException
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url = "http://hemad.halehnouri.com/api/services/app/Account/IsTenantAvailable";
        final String DEFAULT_PARAMS_ENCODING = "UTF-8";
        String url = Url.ACCOUNTREGISTER;


        final JSONObject params = new JSONObject();
        try {
            params.put("name", mobile);
            params.put("surname", mobile);
            params.put("userName", mobile);
            params.put("gender", 0);
            params.put("emailAddress", mobile + "@gmail.com");
            params.put("phoneNumber",  mobile);
            params.put("referedByCode", "string");
            params.put("password", "123456");
            params.put("captchaResponse", "string");
            //params.put("tenancyName", "default");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,
                url,  new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("GGGGGGGGGG", "Error: " + error.getMessage());
                //Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG);
            }
        }) {

            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json-patch+json");
                header.put("Abp.TenantId", "" + 1);
                return header;
            }

            @Override
            protected String getParamsEncoding() {
                return DEFAULT_PARAMS_ENCODING;
            }

            @Override
            public byte[] getBody()
            {

                return params.toString().getBytes();
            }

           @Override
           public String getBodyContentType() {
                return "application/json-patch+json";
            }
        };
        queue.add(stringRequest);

    }

    //2 -> OK
    public void sendValidaionCode(final DataCallback callback, final Context context, String code, final View view)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        final String DEFAULT_PARAMS_ENCODING = "UTF-8";
        String url = Url.TOKENAUTHAUTHENTICATEURL;

        final JSONObject params = new JSONObject();
        try {
            params.put("userNameOrEmailAddress", Settings.getInstance().getProfile().profile.getEmail());//"9121111111");//
            params.put("password", Settings.getInstance().getProfile().profile.getPassword());//"123456");//
            params.put("rememberClient", true);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        callback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d("ValidationCode", "Error: " + error.getMessage());
                //Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG);
            }
        })
        {

            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json-patch+json");
                header.put("Abp.TenantId", "" + 1);
                return header;
            }

            @Override
            protected String getParamsEncoding() {
                return DEFAULT_PARAMS_ENCODING;
            }

            @Override
            public byte[] getBody()
            {

                return params.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json-patch+json";
            }
        };

        queue.add(stringRequest);
    }

    //3 -> OK
    public void SendBasicInformation(final DataCallback callback, final Context context,final View view, final StructureCustomer basicInfo)
    {
        JSONObject myObject = null;
        final JSONObject obj;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.USERUPDATEPROFILEDATA;


        String jsonString = new GsonBuilder().create().toJson(basicInfo.basicInfo, BasicInfo.class);

        Gson gson = new Gson();
        final Object request = gson.toJson(jsonString);
//            try {
//                myObject = new JSONObject(jsonString);
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

        String str = Settings.getInstance().getProfile().getAuthorizationToken();

        //obj = myObject;

        //url += "?data=" + obj.toString();

        //if(myObject != null) {
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callback.onSuccess(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("SendBasicInformation", "Error: " + error.getMessage());
                    //Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<String, String>();
                    header.put("Content-Type", "application/json");
                    header.put("Abp.TenantId", "" + 1);
                    header.put("Authorization", "bearer " + Settings.getInstance().getProfile().getAuthorizationToken());
                    return header;
                }

                @Override
                public byte[] getBody()
                {

                    return request.toString().getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };

            queue.add(stringRequest);
        //}
        //else
        //{
            //Toast.makeText(context, "ارسال اطلاعات پایه - مشکل در ساختار اطلاعات", Toast.LENGTH_LONG).show();
        //}
    }

    //Profile Page
    public void GetProfile(final DataCallback callback, final Context context)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.USERGETPROFILEDATAURL;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        callback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //Toast.makeText(context, Globals.netWorkError + ":" + error.getMessage(), Toast.LENGTH_LONG).show();
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

    public void UpdateProfile(final DataCallback callback, final Context context, StructureCustomer profile, final View view, boolean bType)
    {
        JSONObject myObject = null;
        final JSONObject obj;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.USERUPDATEPROFILEDATA;

        String jsonString;
        if(bType)
            jsonString  = new GsonBuilder().create().toJson(profile.profile, Profile.class);
        else
            jsonString = new GsonBuilder().create().toJson(profile, StructureCustomer.class);

        Gson gson = new Gson();
        final Object request = gson.toJson(jsonString);
        //try {
            //myObject = new JSONObject(jsonString);
        //} catch (JSONException e) {
            //// TODO Auto-generated catch block
            //e.printStackTrace();
        //}

        String str = Settings.getInstance().getProfile().getAuthorizationToken();

        //obj = myObject;

        //url += "?data=" + obj.toString();

        //if(myObject != null) {
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callback.onSuccess(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("SendBasicInformation", "Error: " + error.getMessage());
                    Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<String, String>();
                    header.put("Content-Type", "application/json");
                    header.put("Abp.TenantId", "" + 1);
                    header.put("Authorization", "bearer " + Settings.getInstance().getProfile().getAuthorizationToken());
                    return header;
                }

                @Override
                public byte[] getBody()
                {

                    return request.toString().getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };

            queue.add(stringRequest);
        //}
        //else
        //{
            //Toast.makeText(context, "ارسال اطلاعات پایه - مشکل در ساختار اطلاعات", Toast.LENGTH_LONG).show();
        //}
    }

    //Register Weight Page
    public void GetDailyRecord(final DataCallback callback, final Context context, final View viewl)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.GETMYALLDAILYRECORD;


        JSONObject obj = new JSONObject();
        try {
            obj.put("FromTime", "");
            obj.put("ToTime", "");
            obj.put("Strict", true);
            obj.put("ActiveState", true);
            obj.put("Sorting", "");
            obj.put("SkipCount", 0);
            obj.put("MaxResultCount", 100);
        }catch (Exception e)
        {

        }

        url += "?" + obj.toString();

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        callback.onSuccess(response.toString());
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

    public void UpdateWeightInfo(final DataCallback callback, final Context context, final View view, StructureWeightForUpdate dasboard)
    {
        JSONObject myObject = null;
        final JSONObject obj;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.UPDATEMYDAILYRECORD;


//        String jsonString = new GsonBuilder().create().toJson(dasboard, StructureCustomer.class);
//
//        Gson gson = new Gson();
//        final Object request = gson.toJson(jsonString);
//        try {
//            myObject = new JSONObject(jsonString);
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        String jsonString = new GsonBuilder().create().toJson(dasboard, StructureWeightForUpdate.class);

        Gson gson = new Gson();
        final Object request = gson.toJson(jsonString);

        //String str = Settings.getInstance().getProfile().getAuthorizationToken();

        //obj = myObject;

        //assert obj != null;
        //url += "?data=" + obj.toString();

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("SendBasicInformation", "Error: " + error.getMessage());
                Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json");
                header.put("Abp.TenantId", "" + 1);
                header.put("Authorization", "bearer " + Settings.getInstance().getProfile().getAuthorizationToken());
                return header;
            }

            @Override
            public byte[] getBody()
            {

                return request.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        queue.add(stringRequest);
    }


    //Parvandeh Pezeshki Page
    public void UpdateParvandehPezeshki(final DataCallback callback, final Context context, StructureCustomer profile, final View view)
    {
        JSONObject myObject = null;
        final JSONObject obj;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.USERUPDATEPROFILEDATA;


        String jsonString = new GsonBuilder().create().toJson(profile, StructureCustomer.class);

        Gson gson = new Gson();
        final Object request = gson.toJson(jsonString);
        //try {
        //myObject = new JSONObject(jsonString);
        //} catch (JSONException e) {
        //// TODO Auto-generated catch block
        //e.printStackTrace();
        //}

        String str = Settings.getInstance().getProfile().getAuthorizationToken();

        //obj = myObject;

        //url += "?data=" + obj.toString();

        //if(myObject != null) {
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("SendBasicInformation", "Error: " + error.getMessage());
                Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json");
                header.put("Abp.TenantId", "" + 1);
                header.put("Authorization", "bearer " + Settings.getInstance().getProfile().getAuthorizationToken());
                return header;
            }

            @Override
            public byte[] getBody()
            {

                return request.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        queue.add(stringRequest);
        //}
        //else
        //{
        //Toast.makeText(context, "ارسال اطلاعات پایه - مشکل در ساختار اطلاعات", Toast.LENGTH_LONG).show();
        //}
    }


    //Moshaver Page
    public void GetConsultantProfile(final DataCallback callback, final Context context, final View view)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.GETALLMYACTIVECONSULTANT;


        JSONObject obj = new JSONObject();
        try {
            obj.put("ActiveState", true);
            obj.put("Sorting", "");
            obj.put("SkipCount", 0);
            obj.put("MaxResultCount", 100);
        }catch (Exception e)
        {

        }

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, obj,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        callback.onSuccess(response.toString());
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

    public void GetMoshaverRecommended(final DataCallback callback, final Context context, final View view)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.GETALLMYASSINMENTS;



        JSONObject obj = new JSONObject();
        try {
            obj.put("ActiveState", true);
            obj.put("Sorting", "");
            obj.put("SkipCount", 0);
            obj.put("MaxResultCount", 100);
        }catch (Exception e)
        {

        }


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, obj,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        callback.onSuccess(response.toString());
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

//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String> params = new HashMap<String, String>();
//                return params;
//            }

            @Override
            public String getBodyContentType() {
                return "application/json-patch+json";
            }

        };

        queue.add(stringRequest);
    }


    //Diet Page
    public void GetDiet(final DataCallback callback, final Context context, final View view, String fromTime, String toTime)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.GETALLDIET;

        JSONObject obj = new JSONObject();
        try {

            //obj.put("FromTime", fromTime);
            //obj.put("ToTime", toTime);
            obj.put("ActiveState", true);
            obj.put("Sorting", "");
            obj.put("SkipCount", 0);
            obj.put("MaxResultCount", 100);
        }catch (Exception e)
        {

        }


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, obj,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        callback.onSuccess(response.toString());
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

//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String> params = new HashMap<String, String>();
//                return params;
//            }

            @Override
            public String getBodyContentType() {
                return "application/json-patch+json";
            }

        };

        queue.add(stringRequest);
    }

    public void GetNotification(final DataCallback callback, final Context context, final View viewl)
    {
        RequestQueue queue;
        if(context != null)
            queue = Volley.newRequestQueue(context);
        else
        {
            NotificationService notificationService = new NotificationService();
            queue = Volley.newRequestQueue(notificationService.getApplicationContext());
        }

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

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, obj,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        callback.onSuccess(response.toString());
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





}

final class PaymentService
{
    public void Purchase(final DataCallback callback, final Context context, final View view, StructurePurchase plan)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url = "http://hemad.halehnouri.com/api/services/app/Account/IsTenantAvailable";
        final String DEFAULT_PARAMS_ENCODING = "UTF-8";
        String url = Url.PURCHASE;


            final JSONObject params = new JSONObject();
            try {
                params.put("planId", plan.getItems().get(0).getPlanId());
                params.put("couponCode", plan.getItems().get(0).getCouponId());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String str = Settings.getInstance().getProfile().getAuthorizationToken();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,
                url,  new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("GGGGGGGGGG", "Error: " + error.getMessage());
                Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG);
            }
        }) {

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
            protected String getParamsEncoding() {
                return DEFAULT_PARAMS_ENCODING;
            }

            @Override
            public byte[] getBody()
            {

                return params.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json-patch+json";
            }
        };
        queue.add(stringRequest);

    }
}















final class PlansService
{
    public void GetPlans(final DataCallback callback, final Context context, final View view)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.PLANGETALL;

        JSONObject obj = new JSONObject();
        try {
            obj.put("Sorting", "");
            obj.put("SkipCount", 0);
            obj.put("MaxResultCount", 100);
        }catch (Exception e)
        {

        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, obj,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        callback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG);
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json");
                header.put("Abp.TenantId", "" + 1);
                header.put("Authorization", "bearer " + Settings.getInstance().getProfile().getAuthorizationToken());
                return header;
            }
        };

        queue.add(stringRequest);
    }

    public void GetFoodPlan(final DataCallback callback, final Context context, final View view)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.GETMYALLDAILYRECORD;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json");
                header.put("Abp.TenantId", "" + 1);
                header.put("Authorization", "bearer " + Settings.getInstance().getProfile().getAuthorizationToken());
                return header;
            }
        };

        queue.add(stringRequest);
    }
}

final class ChatService
{

}

final class Notification
{
    public void GetNotification(final DataCallback callback, final Context context)
    {
        final String[] strResult = {""};
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Url.GETNOTIFICATION;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //Toast.makeText(context, Globals.netWorkError, Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", Settings.getInstance().getHeader().getToken());
                params.put("Abp.TenantId", "" + Settings.getInstance().getHeader().getTanentId());
                return params;
            }
        };

        queue.add(stringRequest);
    }
}