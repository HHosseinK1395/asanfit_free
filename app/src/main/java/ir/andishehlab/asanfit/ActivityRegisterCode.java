package ir.andishehlab.asanfit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.Random;

import FontComponents.MyEditText;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.Globals;
import Others.MyProgressDialog;
import Others.Settings;

/**
 * Created by N550J on 12/3/2017.
 */
public class ActivityRegisterCode extends AppCompatActivity {

    Button btnRegister;
    ScrollView scrollView;
    MyEditText editText;
    TextView txtRepeatCode;
    Globals globals;
    ProgressDialog progress;
    boolean doubleBackToExitPressedOnce;
    private MyProgressDialog pd;
    private Handler h;
    private Runnable r;
    FacadeService facadeService = new FacadeService();
    View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_code);


        //Get Extra String
        String newString = "";
        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras == null)
            {

            }
            else
            {
                newString = extras.getString("MOBILE_NUMBER");
            }
        }



        btnRegister = findViewById(R.id.btn_register_code);
        editText = findViewById(R.id.edt_register_code);
        txtRepeatCode = findViewById(R.id.txt_register_code_body);

        //Init
        txtRepeatCode.setPaintFlags(txtRepeatCode.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//set underline for textview
        globals = new Globals(this);
        doubleBackToExitPressedOnce = false;
        parentView = this.findViewById(android.R.id.content);

        editText.setText("" + GenerateRandomCoe(100000, 999999));

        //OnClick Button Continue
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                btnRegister.setEnabled(false);
                if(editText.getText().toString().equals("") || editText.getText().toString().equals(null))
                {
                    Snackbar.make(parentView, getResources().getString(R.string.register_code_error_one), Snackbar.LENGTH_LONG).show();
                    editText.setText("" + GenerateRandomCoe(100000, 999999));
                    startActivityAfterSeconds();
                }
                else
                if(editText.getText().length() == 6)
                {
                    facadeService.SendValidationCode(new DataCallbbackOutput() {
                        @Override
                        public void onSuccess(String result) {
                            String strFinalToken = null;
                            try {
                                strFinalToken = facadeService.searchnStringObject(result, "result", "accessToken");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(strFinalToken == null)
                            {
                                Snackbar.make(parentView, "ثبت نام به درستی انجام نشد", Snackbar.LENGTH_LONG).show();
                            }
                            else {
                                Settings.getInstance().getProfile().setAuthorizationToken(strFinalToken);
                                Settings.getInstance().saveAll();
                                startActivityAfterSeconds();
                            }
                        }
                    }, getApplicationContext(), editText.getText().toString(), parentView);
                }
                else
                    Snackbar.make(parentView, "تعداد اعداد وارد شده کمتر از حد مجاز است", Snackbar.LENGTH_LONG).show();

                btnRegister.setEnabled(true);
            }
        });

        //Check and Change edittexts when is empty or not and focused change
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    view.setBackgroundResource(R.drawable.shape_edittext_focused);
                }
                else
                {
                    view.setBackgroundResource(R.drawable.shape_edittext);
                }
            }
        });

        //Repeat request code from server
        txtRepeatCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(ActivityRegisterCode.this, getResources().getString(R.string.register_code_error_two), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startActivityAfterSeconds() {

        final MyProgressDialog cdd = new MyProgressDialog(this, "در حال بارگزاری . . .");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                //TODO : check server for currect register code in sms
                //TODO : if code is currect goto user info page
                Intent intent = new Intent(ActivityRegisterCode.this, ActivityUserInfo.class);
                startActivity(intent);
                finish();
                cdd.dismiss();
            }
        }, 700);
    }

    int GenerateRandomCoe(int x, int y)
    {
        Random r = new Random();
        int Low = x;//100000
        int High = y;//999999
        int Result = r.nextInt(High-Low) + Low;

        return Result;
    }
}
