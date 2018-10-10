package Network;

import android.content.Context;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import Structures.StructureCustomer;
import Structures.StructurePurchase;
import Structures.StructureWeightForUpdate;

public class FacadeService
{
    private UsersService usersService;
    private PlansService plansService;
    private PaymentService paymentService;

    public FacadeService()
    {
        usersService = new UsersService();
        plansService = new PlansService();
        paymentService =new PaymentService();
    }

    //1 -> OK
    public void SendMobile(final DataCallbbackOutput callbackOut, Context context, final View view, String mobile) throws JSONException, UnsupportedEncodingException {
        final String[] myResult = {""};
        usersService.SendPhoneNumber(new DataCallback()
        {
            @Override
            public void onSuccess(final String result)
            {
                callbackOut.onSuccess(result);
            }
        }, context, view, mobile);
    }

    //2 -> OK
    public void SendValidationCode(final DataCallbbackOutput callbackOut, Context context, String code, final View view)
    {
        usersService.sendValidaionCode(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                callbackOut.onSuccess(result);
            }
        }, context, code, view);
    }

    //3 -> OK
    public void SendBasicInformation(final DataCallbbackOutput callbackOut, Context context, final View view, StructureCustomer profile)
    {
        usersService.SendBasicInformation(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                callbackOut.onSuccess(result);
            }
        }, context, view, profile);
    }

    //4 -> OK
    public void GetProfile(final DataCallbbackOutput callbackOut, final Context context)
    {
        usersService.GetProfile(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                callbackOut.onSuccess(result);
            }
        }, context);
    }

    public void UpdateProfile(final DataCallbbackOutput callbackOut, Context context, StructureCustomer profile, final View view, boolean bType)
    {
        usersService.UpdateProfile(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                callbackOut.onSuccess(result);
            }
        }, context, profile, view, bType);
    }

    public void GetMoshaverProfile(final DataCallbbackOutput callbackOut, Context context, final View view)
    {
        usersService.GetConsultantProfile(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    callbackOut.onSuccess(obj.get("result").toString());
                }
                catch (Exception e)
                {

                }
            }
        },context, view);
    }

    public void UpdateWeightInfo(final DataCallbbackOutput callback, final Context context, final View view, StructureWeightForUpdate dashbord)
    {
        usersService.UpdateWeightInfo(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }
        }, context, view, dashbord);
    }

    //Plan Services
    public void GetPlan(final DataCallbbackOutput callbackOut, Context context, final View view)
    {
        plansService.GetPlans(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    callbackOut.onSuccess(obj.get("result").toString());
                }
                catch (Exception e)
                {

                }
            }
        }, context, view);
    }

    public void GetDiet(final DataCallbbackOutput callbackOut, Context context, View view, String fromTime, String toTime)
    {
        usersService.GetDiet(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    callbackOut.onSuccess(obj.get("result").toString());
                }catch (Exception e)
                {

                }
            }
        }, context, view, fromTime, toTime);
    }

    public void Purchase(final DataCallbbackOutput callbackOut, Context context, final View view, StructurePurchase plan)
    {
        paymentService.Purchase(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    callbackOut.onSuccess(obj.get("result").toString());
                }catch (Exception e)
                {

                }

            }
        }, context, view, plan);
    }

    public void UpdateParvandehPezeshki(final DataCallbbackOutput callbackOut, Context context, StructureCustomer profile, final View view)
    {
        usersService.UpdateParvandehPezeshki(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                callbackOut.onSuccess(result);
            }
        }, context, profile, view);
    }

    public void GetMoshaverRecommended(final DataCallbbackOutput callbackOut, Context context, final View view)
    {
        usersService.GetMoshaverRecommended(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    callbackOut.onSuccess(obj.get("result").toString());
                }catch (Exception e)
                {

                }
            }
        }, context, view);
    }

    public void GetDailyRecord(final DataCallbbackOutput callbbackOutput, Context context, final View view)
    {
        usersService.GetDailyRecord(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    callbbackOutput.onSuccess(obj.get("result").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, context, view);
    }


    public void GetNotification(final DataCallbbackOutput callbbackOutput, Context context, final View view)
    {
        usersService.GetNotification(new DataCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    callbbackOutput.onSuccess(obj.get("result").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, context, view);
    }

    public void MethodA()
    {
        //usersService.GetInfo();
        //plansService.GetPlans();
        //paymentService.Pay();
    }

    public void MethodB()
    {
        //usersService.GetInfo();
    }

    public void GetUser()
    {
        //usersService.GetInfo();
    }

    public boolean searchnObject(String jsonString, String key, String searchVale) throws JSONException {
        boolean bResult = false;
        bResult  = new JSONObject(jsonString).getJSONObject(key).getBoolean(searchVale);
        return bResult;
    }

    public String searchnStringObject(String jsonString, String key, String searchVale) throws JSONException {
        String strResult = null;
        strResult  = new JSONObject(jsonString).getJSONObject(key).getString(searchVale);
        return strResult;
    }

    public int searchnIntObject(String jsonString, String key, String searchVale) throws JSONException {
        int iResult = -1;
        iResult  = new JSONObject(jsonString).getJSONObject(key).getInt(searchVale);
        return iResult;
    }

}
