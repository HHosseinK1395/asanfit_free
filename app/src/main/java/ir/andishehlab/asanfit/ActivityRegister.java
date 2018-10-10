package ir.andishehlab.asanfit;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import FontComponents.MyEditText;
import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.Globals;
import Others.Settings;
import Structures.StructureCustomer;

/**
 * Created by N550J on 12/3/2017.
 */
public class ActivityRegister extends AppCompatActivity {

    Button btnRegister;
    ScrollView scrollView;
    MyEditText editText;
    Globals globals;
    TextViewFont textRules;
    boolean doubleBackToExitPressedOnce;
    LinearLayout lnrRegisterDirection;
    private ImageView imgTick;
    private boolean bRule;
    StructureCustomer registerInfo = new StructureCustomer();
    FacadeService facadeService = new FacadeService();
    View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        CheckRegisteredOrNot();


        btnRegister = findViewById(R.id.btn_register_code);
        editText = findViewById(R.id.edt_register_code);
        textRules = findViewById(R.id.txt_register_code_rule_1);
        lnrRegisterDirection =findViewById(R.id.lnr_register_rtl_layout);
        imgTick = findViewById(R.id.img_btn_rules_tick);

        //Init
        globals = new Globals(this);
        doubleBackToExitPressedOnce = false;
        btnRegister.setEnabled(false);
        bRule = false;
        textRules.setPaintFlags(textRules.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        ViewCompat.setLayoutDirection (lnrRegisterDirection, ViewCompat.LAYOUT_DIRECTION_RTL);
        parentView = this.findViewById(android.R.id.content);

        //TODO : Test Test Test
        editText.setText("");//"911" + GenerateRandomCoe(1000000, 9000000));

        //OnClick for Register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.setEnabled(false);
                if(editText.getText().toString().equals("") || editText.getText().toString().equals(null))
                {
                    Snackbar.make(parentView, getResources().getString(R.string.register_error_one), Snackbar.LENGTH_LONG).show();
                    btnRegister.setEnabled(true);
                }
                else {
                    if (isValidMobile(editText.getText().toString()))
                    {
                        try {
                            facadeService.SendMobile(new DataCallbbackOutput()
                            {
                                @Override
                                public void onSuccess(String result) {
                                    boolean res = false;
                                    try {
                                        res = facadeService.searchnObject(result, "result", "canLogin");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    //TODO : Edit Edit Edit
                                    if(!res)
                                    {
                                        Snackbar.make(parentView, Html.fromHtml("<font color=\"#FF0000\">مشکلی در حساب کاربری رخ داده</font>"), Snackbar.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Snackbar.make(parentView, Html.fromHtml("<font color=\"#00FF00\">ورود موفقیت آمیز</font>"), Snackbar.LENGTH_LONG).show();
                                        String strMobile = editText.getText().toString();
                                        Settings.getInstance().getProfile().setTenantId(1);
                                        Settings.getInstance().getProfile().profile.setName(strMobile);
                                        Settings.getInstance().getProfile().profile.setSurname(strMobile);
                                        Settings.getInstance().getProfile().profile.setUserName(strMobile);
                                        Settings.getInstance().getProfile().profile.setEmail( strMobile + "@gmail.com");
                                        Settings.getInstance().getProfile().profile.setPhoneNumber(strMobile);
                                        Settings.getInstance().getProfile().profile.setReferedByCode("string");
                                        Settings.getInstance().getProfile().profile.setPassword("123456");
                                        Settings.getInstance().getProfile().profile.setCaptchaResponse("string");
                                        Settings.getInstance().saveAll();

                                        Intent intent = new Intent(ActivityRegister.this, ActivityRegisterCode.class);
                                        intent.putExtra("MOBILE_NUMBER", editText.toString());
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }, getApplicationContext(), parentView, editText.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Snackbar.make(parentView, getResources().getString(R.string.register_error_two), Snackbar.LENGTH_LONG).show();
                    }
                }

                btnRegister.setEnabled(true);
            }
        });

        //Check and Change edittexts if it is empty or not and focused change
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

        //Open company rules url
        textRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgTick.setBackgroundResource(R.drawable.ic_rules_orange);
                bRule = true;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.register_rule_url)));
                startActivity(browserIntent);
                //imgTick.setBackgroundTintList(getResources().getColor(R.color.mycolor_yellow)));
            }
        });

        imgTick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bRule) {
                    bRule = true;
                    imgTick.setBackgroundResource(R.drawable.ic_rules_orange);
                    btnRegister.setEnabled(true);
                }
                else
                {
                    bRule = false;
                    imgTick.setBackgroundResource(R.drawable.ic_rules);
                    btnRegister.setEnabled(false);
                }
            }
        });
    }

    private boolean isValidMobile(String phone) {
        if(phone.matches("(\\+98|0)?9\\d{9}"))
            return true;
        else
            return false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentView,  getResources().getString(R.string.user_info_back_pressed), Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        imgTick.setBackgroundResource(R.drawable.ic_rules);
    }

    void CheckRegisteredOrNot()
    {
        if(Settings.getInstance().getbIsRegistered())
        {
            //Intent intent = new Intent(ActivityRegister.this, ActivityUserInfo.class);
            Intent intent = new Intent(ActivityRegister.this, ActivityMain.class);
            startActivity(intent);
            finish();
        }
    }

    int GenerateRandomCoe(int x, int y)
    {
        Random r = new Random();
        int Low = x;
        int High = y;
        int Result = r.nextInt(High-Low) + Low;

        return Result;
    }
}
