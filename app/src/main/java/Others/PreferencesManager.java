package Others;

import android.content.Context;
import android.content.SharedPreferences;

import ir.andishehlab.asanfit.Application;
import ir.andishehlab.asanfit.BuildConfig;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/14/2017.
 */
public class PreferencesManager {

    private Context context;
    private SharedPreferences sharedPreferences;

    private static PreferencesManager ourInstance;

    static{
        ourInstance = new PreferencesManager();
    }

    public static PreferencesManager getInstance() {
        if(ourInstance == null)
            ourInstance = new PreferencesManager();
        return ourInstance;
    }

    public PreferencesManager() {
    }

    public void getSharedPreferences(Context myContext)
    {
        sharedPreferences = myContext.getSharedPreferences(myContext.getString(R.string.register_shared_preferences_name), Context.MODE_PRIVATE);
    }

    public void writeSHaredPreferences(Context myContext)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(myContext.getString(R.string.register_shared_preferences_key), "INIT_OK");
        editor.commit();
    }

    public boolean checkPreferences(Context myContext)
    {
        boolean status = false;
        if(sharedPreferences.getString(myContext.getString(R.string.register_shared_preferences_key), "null").equals("null"))
        {
            status = false;
        }
        else
            status = true;

        return status;
    }

    public void clearPreferences()
    {
        sharedPreferences.edit().clear().apply();
    }

    public String readValuesFromFile(String Key) {
        SharedPreferences sharedPref = Application.context.getSharedPreferences(BuildConfig.APPLICATION_ID, 0);
        String defaultValue = "";
        String valueToRead = sharedPref.getString(Key, defaultValue);

        if (valueToRead == defaultValue)
            return null;

        return valueToRead;
    }

    public void writeValuesOnFile(String key, String value) {
        SharedPreferences sharedPref = Application.context.getSharedPreferences(BuildConfig.APPLICATION_ID, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

}
