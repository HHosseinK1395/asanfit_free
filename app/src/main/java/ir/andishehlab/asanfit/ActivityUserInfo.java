package ir.andishehlab.asanfit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import FontComponents.MyEditText;
import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.DateConvertor;
import Others.Globals;
import Others.MyProgressDialog;
import Others.Settings;
import Structures.StructureValidationUser;

/**
 * Created by N550J on 12/3/2017.
 */
public class ActivityUserInfo extends AppCompatActivity {

    private Button btnContinue;
    private ScrollView scrollView;
    private MyEditText editWeight, editHeight, editAge;
    private TextViewFont txtWeightRight, txtWeightLeft, txtHeightRight, txtHeightLeft, txtAgeRight, txtRadioMan, txtRadioWomen;
    private FrameLayout frmMal, frmMal2, frmFemal, frmFemal2;
    private ImageView imgMale, imgFemale;
    private Globals globals;
    private ProgressDialog progress;
    private LinearLayout lnrRadioMan, lnrRadioWomen;
    private boolean doubleBackToExitPressedOnce;
    private String strMaleOrFemal;
    private int gender;
    private FacadeService facadeService = new FacadeService();
    private String strResult;
    private View parentView;


    void daysInThisWeek()
    {
        DateConvertor dc = new DateConvertor();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < 7; i++) {

            String stringDefault = dc.miladiToShamsi(dc.getDateFromTimeStamp(cal.getTimeInMillis()));
            String[] stringNew = stringDefault.split("/");
            if(stringNew[1].equals("12"))
            Log.e("dateTag:", stringDefault);
            String[] str = dc.miladiToShamsi(dc.getDateFromTimeStamp(cal.getTimeInMillis())).split("/");
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        //daysInThisWeek();

        btnContinue = findViewById(R.id.btn_user_info_calculate);
        editWeight = findViewById(R.id.edt_user_info_weight);
        editHeight = findViewById(R.id.edt_user_info_height);
        editAge = findViewById(R.id.edt_user_info_age);

        frmMal = findViewById(R.id.btn_user_info_male);
        frmMal2 = findViewById(R.id.btn_user_info_male_2);
        frmFemal = findViewById(R.id.btn_user_info_female);
        frmFemal2 = findViewById(R.id.btn_user_info_female_2);

        imgMale = findViewById(R.id.img_user_info_male);
        imgFemale = findViewById(R.id.img_user_info_female);
        txtRadioMan = findViewById(R.id.txt_radio_man);
        txtRadioWomen  = findViewById(R.id.txt_radio_women);
        lnrRadioMan = findViewById(R.id.lnr_radio_man);
        lnrRadioWomen = findViewById(R.id.lnr_radio_women);


        txtWeightRight = findViewById(R.id.txt_weight_right);
        txtWeightLeft = findViewById(R.id.txt_weight_left);
        txtHeightRight = findViewById(R.id.txt_height_right);
        txtHeightLeft = findViewById(R.id.txt_height_left);
        txtAgeRight = findViewById(R.id.txt_age_right);

        //Init
        AuthenticateUser();
        globals = new Globals(this);
        doubleBackToExitPressedOnce = false;
        strMaleOrFemal = "female";
        gender = 0;
        editWeight.setHint("" + Settings.getInstance().getProfile().basicInfo.getWeight());
        editHeight.setHint("" + Settings.getInstance().getProfile().basicInfo.getHeight());
        editAge.setHint("" + Settings.getInstance().getProfile().basicInfo.getAge());
        parentView = this.findViewById(android.R.id.content);

        //Login();
        ConfigInfo();

        Settings.getInstance().setbOneTimeFillSpinnerParvandeh(true);
        Settings.getInstance().setbIsRegistered(true);
        Settings.getInstance().saveAll();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnContinue.setEnabled(false);
                int HeightInt = 0, WeightInt = 0, AgeInt = 0;
                if(!editHeight.getText().toString().equals(""))
                    HeightInt = Integer.parseInt(editHeight.getText().toString());

                if(!editWeight.getText().toString().equals(""))
                    WeightInt = Integer.parseInt(editWeight.getText().toString());

                if(!editAge.getText().toString().equals(""))
                    AgeInt = Integer.parseInt(editAge.getText().toString());

                if( HeightInt > 220 || HeightInt < 50 ) {
                    Snackbar.make(parentView, "قد غیر قابل محاسبه است", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if( WeightInt > 200 || WeightInt < 20 ) {
                    Snackbar.make(parentView, "وزن غیر قابل محاسبه است", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if( AgeInt > 90 || HeightInt < 10 ) {
                    Snackbar.make(parentView, "سن مناسب نیست", Snackbar.LENGTH_LONG).show();
                    return;
                }



                if( editAge.getText().toString().equals("") ||
                    editWeight.getText().toString().equals("") ||
                    editHeight.getText().toString().equals(""))
                {
                    Snackbar.make(parentView, getResources().getString(R.string.user_info_error), Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    SendBasicInformationToServer();
                }

                btnContinue.setEnabled(true);
            }
        });

        frmMal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = 1;
                MaleFemaleClicked(gender);
            }
        });

        frmFemal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = 0;
                MaleFemaleClicked(gender);
            }
        });

        editWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        editHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        editAge.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

    }

    void Login()
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
                    //startActivityAfterSeconds();
                    ConfigInfo();
                }
            }
        }, getApplicationContext(), "123456", parentView);
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

                Intent intent = new Intent(ActivityUserInfo.this, ActivityUserInfoComplete.class);
                intent.putExtra("EXTRA_WEIGHT", editWeight.getText().toString());
                intent.putExtra("EXTRA_HEIGHT", editHeight.getText().toString());
                intent.putExtra("EXTRA_MALE_OR_FEMALE", strMaleOrFemal);

                startActivity(intent);
                finish();
                cdd.dismiss();
            }
        }, 700);
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

    void AuthenticateUser()
    {
        StructureValidationUser validation = new StructureValidationUser("", "");
        validation.setCode("");
        validation.setToken("");
        //TODO : Edit Edit Edit
        //validation = facadeService.SendValidationCode(getApplicationContext(), validation);
        //if(validation != null) {
            //if (validation.getToken().equals(Settings.getInstance().getGlobalToken())) {
                //Toast.makeText(getApplicationContext(), "احراز هویت با مشکل روبرو شد دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
                //finish();
            //}
        //}
        //else
        //{
            //TODO : Edit Edit Edit
            //Toast.makeText(getApplicationContext(), "احراز هویت با مشکل روبرو شد دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
            //finish();
        //}
    }

    void MaleFemaleClicked(int type)
    {
        if(type == 0)
        {
            frmFemal.setBackgroundColor(getResources().getColor(R.color.transparent));
            imgFemale.setColorFilter(getResources().getColor(R.color.white));
            imgMale.setColorFilter(getResources().getColor(R.color.gray));
            txtRadioMan.setTextColor(getResources().getColor(R.color.user_info_text_color_3));
            txtRadioWomen.setTextColor(getResources().getColor(R.color.white));
            lnrRadioWomen.setBackground(getResources().getDrawable(R.drawable.img_radio_shape));
            lnrRadioMan.setBackground(getResources().getDrawable(R.drawable.shape_button_3));
            frmMal.setVisibility(View.VISIBLE);
            frmMal2.setVisibility(View.GONE);
            frmFemal2.setVisibility(View.VISIBLE);
            frmFemal.setVisibility(View.GONE);
            strMaleOrFemal = "female";
        }

        if(type == 1)
        {
            frmMal.setBackgroundColor(getResources().getColor(R.color.transparent));
            imgFemale.setColorFilter(getResources().getColor(R.color.gray));
            imgMale.setColorFilter(getResources().getColor(R.color.white));
            txtRadioMan.setTextColor(getResources().getColor(R.color.white));
            txtRadioWomen.setTextColor(getResources().getColor(R.color.user_info_text_color_3));
            lnrRadioMan.setBackground(getResources().getDrawable(R.drawable.img_radio_shape));
            lnrRadioWomen.setBackground(getResources().getDrawable(R.drawable.shape_button_3));
            frmMal.setVisibility(View.GONE);
            frmMal2.setVisibility(View.VISIBLE);
            frmFemal2.setVisibility(View.GONE);
            frmFemal.setVisibility(View.VISIBLE);
            strMaleOrFemal = "male";
        }
    }

    void ConfigInfo()
    {
        MaleFemaleClicked((Settings.getInstance().getProfile()).basicInfo.getGender());
    }

    void SendBasicInformationToServer()
    {
        Settings.getInstance().getProfile().basicInfo.setWeight(Integer.parseInt(editWeight.getText().toString()));
        Settings.getInstance().getProfile().basicInfo.setHeight(Integer.parseInt(editHeight.getText().toString()));
        Settings.getInstance().getProfile().basicInfo.setAge(Integer.parseInt(editAge.getText().toString()));
        Settings.getInstance().getProfile().basicInfo.setGender(gender);
        Settings.getInstance().getProfile().basicInfo.setBirthDate("1990/1/1");
        Settings.getInstance().getProfile().basicInfo.setBmi(12.4f);
        Settings.getInstance().getProfile().basicInfo.setWaist(0);
        Settings.getInstance().getProfile().basicInfo.setWrist(0);
        Settings.getInstance().setToodehBadani(Settings.getInstance().getProfile());
        Settings.getInstance().saveAll();

        facadeService.SendBasicInformation(new DataCallbbackOutput() {
            @Override
            public void onSuccess(String result) {
                if(!result.equals(""))
                {
                    Snackbar.make(parentView, Html.fromHtml("<font color=\"#00FF00\">ذخیره اطلاعات موفقیت آمیز بود</font>"), Snackbar.LENGTH_LONG)
                            .addCallback(new Snackbar.Callback(){
                                @Override
                                public void onDismissed(Snackbar transientBottomBar, int event) {
                                }

                                @Override
                                public void onShown(Snackbar sb) {
                                    startActivityAfterSeconds();
                                    super.onShown(sb);
                                }
                            })
                            .show();

                }
                else
                {
                    Snackbar.make(parentView, Html.fromHtml("<font color=\"#FF0000\">اشکال در ذخیره اطلاعات، مجددا تلاش کنید</font>"), Snackbar.LENGTH_LONG).show();
                }
            }
        }, this, parentView, Settings.getInstance().getProfile());

        btnContinue.setEnabled(true);
    }
}
