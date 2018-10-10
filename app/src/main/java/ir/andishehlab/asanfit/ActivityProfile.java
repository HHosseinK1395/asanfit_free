package ir.andishehlab.asanfit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.avocarrot.json2view.DynamicView;
import com.avocarrot.json2view.DynamicViewId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by N550J on 12/1/2017.
 */
public class ActivityProfile extends AppCompatActivity implements View.OnClickListener  {


    private AppCompatSpinner spinner;
    private SwitchCompat switchNotification, switchMotivational;
    private Button btnExit;

    private String readFile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets().open(fileName);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line;
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null) isr.close();
                if (fIn != null) fIn.close();
                if (input != null) input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    static public class SampleViewHolder {
        @DynamicViewId(id = "testClick")
        public View clickableView;

        public SampleViewHolder() {
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.avocarrot.com/")));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JSONObject jsonObject;

        try {

            jsonObject = new JSONObject(readFile("sample.json", this));

        } catch (JSONException je) {
            je.printStackTrace();
            jsonObject = null;
        }

        if (jsonObject != null) {

            /* create dynamic view and return the view with the holder class attached as tag */
            View sampleView = DynamicView.createView(this, jsonObject, SampleViewHolder.class);
            /* get the view with id "testClick" and attach the onClickListener */

            /* add Layout Parameters in just created view and set as the contentView of the activity */
            setContentView(sampleView);

        } else {
            Log.e("Json2View", "Could not load valid json file");
        }



        /*spinner = findViewById(R.id.spinner);
        switchNotification =  findViewById(R.id.switch_resolve_notification_message);
        switchMotivational = findViewById(R.id.switch_resolve_motivational_message);
        btnExit = findViewById(R.id.btn_exit);

        List<String> categories = new ArrayList<String>();
        categories.add("مازندران");
        categories.add("تهران");
        categories.add("شیراز");
        categories.add("اصفهان");
        categories.add("مشهد");
        categories.add("کرمانشاه");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.layout_spinner, categories);
        dataAdapter.setDropDownViewResource(R.layout.layout_spinner);
        spinner.setAdapter(dataAdapter);

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    Toast.makeText(getApplicationContext(), "اعلانات فعال شد", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "اعلانات غیر فعال شد", Toast.LENGTH_LONG).show();
            }
        });

        switchMotivational.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    Toast.makeText(getApplicationContext(), "انگیزشی فعال شد", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "انگیزشی غیر فعال شد", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "خروج از حساب کاربری", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void XmlToJson() {

    }

}

