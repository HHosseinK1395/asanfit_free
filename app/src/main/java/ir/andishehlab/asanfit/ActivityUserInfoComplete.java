package ir.andishehlab.asanfit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hookedonplay.decoviewlib.DecoView;

import FontComponents.TextViewFont;
import Others.Globals;
import Others.MyProgressDialog;
import Others.Settings;

/**
 * Created by N550J on 12/3/2017.
 */
public class ActivityUserInfoComplete extends AppCompatActivity {


    Button btnStart;
    DecoView arcView, arcView_2, arcUnderUp, arcUnderUp_2, arcUnderDown, arcUnderDown_2;
    TextViewFont txtShakhes, txtHint, txtUnderUp, txtUnderDown, txtWeightHint;
    ImageView
        imgWeight18,
        imgWeight25,
        imgWeight30,
        imgWeight40,
        imgBottomIcon,
        imgLinesFlat1,
        imgLinesNotFlat1,
        imgLinesFlat2,
        imgLinesNotFlat2,
        imgLinesFlat3,
        imgLinesNotFlat3,
        imgLinesFlat4,
        imgLinesNotFlat4;

    LinearLayout lnrWeight18, lnrWeight25, lnrWeight30, lnrWeight40;
    ProgressDialog progress;
    boolean doubleBackToExitPressedOnce;
    String strMaleOrFemale;
    String strWeight;
    String strHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_complete);

        strWeight = getIntent().getStringExtra("EXTRA_WEIGHT");
        strHeight = getIntent().getStringExtra("EXTRA_HEIGHT");
        strMaleOrFemale = getIntent().getStringExtra("EXTRA_MALE_OR_FEMALE");


        btnStart = findViewById(R.id.btn_user_info_complete_start);
        arcView = findViewById(R.id.circle_progress_shakhes);
        arcView_2 = findViewById(R.id.circle_progress_shakhes_1);
        arcUnderUp = findViewById(R.id.circle_progress_under_up);
        arcUnderUp_2 = findViewById(R.id.circle_progress_under_up_1);
        arcUnderDown = findViewById(R.id.circle_progress_under_down);
        arcUnderDown_2 = findViewById(R.id.circle_progress_under_down_1);
        txtShakhes = findViewById(R.id.txt_shakhes_measure);
        txtHint = findViewById(R.id.txt_weight_hint_1);
        txtUnderUp = findViewById(R.id.txt_under_up);
        txtUnderDown = findViewById(R.id.txt_under_down);
        imgWeight18 = findViewById(R.id.img_weight_18);
        imgWeight25 = findViewById(R.id.img_weight_25);
        imgWeight30 = findViewById(R.id.img_weight_30);
        imgWeight40 = findViewById(R.id.img_weight_40);
        lnrWeight18 = findViewById(R.id.linear_weight_18);
        lnrWeight25 = findViewById(R.id.linear_weight_25);
        lnrWeight30 = findViewById(R.id.linear_weight_30);
        lnrWeight40 = findViewById(R.id.linear_weight_40);
        txtWeightHint = findViewById(R.id.txt_weight_hint_1);
        imgBottomIcon = findViewById(R.id.img_shakhes_bottom_image);
        imgLinesFlat1 = findViewById(R.id.img_lines_flat_1);
        imgLinesNotFlat1 = findViewById(R.id.img_lines_not_flat_1);
        imgLinesFlat2 = findViewById(R.id.img_lines_flat_2);
        imgLinesNotFlat2  = findViewById(R.id.img_lines_not_flat_2);
        imgLinesFlat3  = findViewById(R.id.img_lines_flat_3);
        imgLinesNotFlat3  = findViewById(R.id.img_lines_not_flat_3);
        imgLinesFlat4  = findViewById(R.id.img_lines_flat_4);
        imgLinesNotFlat4 = findViewById(R.id.img_lines_not_flat_4);


        //Init
        doubleBackToExitPressedOnce = false;
        float sqr = Float.parseFloat(strHeight)/100;
        final float iShakhes = Float.parseFloat(strWeight) / (float)Math.pow(sqr, 2);
        final float iIdeal = 23 * sqr * sqr;
        final float distance = (iIdeal - iShakhes)/4;

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setEnabled(false);
                Settings.getInstance().getDashbord().setWeight(strWeight);
                Settings.getInstance().getDashbord().setHeight(strHeight);
                Settings.getInstance().getDashbord().setIdealWeight(String.valueOf(iIdeal));
                Settings.getInstance().getDashbord().setShakhes("" + iShakhes);
                Settings.getInstance().getDashbord().setOverweight("" + distance);

                Settings.getInstance().getDashbord().setCurrentWeight(strWeight);
                Settings.getInstance().getDashbord().setDoreKamar("0");
//                Settings.getInstance().getDashbord().setKaheshVaznTaKonoon(
//                        "" +
//                        (Integer.parseInt(Settings.getInstance().getDashbord().getWeight()) -
//                        Integer.parseInt(Settings.getInstance().getDashbord().getCurrentWeight())));
                Settings.getInstance().saveAll();
                startActivityAfterSeconds();
            }
        });





        Globals globals = new Globals(this);
        globals.InitArcView(arcView, txtShakhes, iShakhes, 2, 100, getResources().getColor(R.color.user_info_complete_circle_green_1), true, 500, false);
        globals.InitArcView(arcView_2, txtShakhes, iShakhes, 2, 100, getResources().getColor(R.color.user_info_complete_circle_green_2),true, 500, false);

        globals.InitArcView(arcUnderUp, txtUnderUp, iIdeal, 2, 100,getResources().getColor(R.color.user_info_complete_circle_green_1),true, 500, false);
        globals.InitArcView(arcUnderUp_2, txtUnderUp, iIdeal, 2, 100,getResources().getColor(R.color.user_info_complete_circle_green_2),true, 500, false);

        globals.InitArcView(arcUnderDown, txtUnderDown, distance, 2, 100, getResources().getColor(R.color.user_info_complete_circle_yellow_1),true,500, false);
        globals.InitArcView(arcUnderDown_2, txtUnderDown, distance, 2, 100, getResources().getColor(R.color.user_info_complete_circle_yellow_2),true,500, false);

        ConfigWeightShape(iShakhes);
    }

    private void ConfigWeightShape(float iShakhes) {

        if(strMaleOrFemale.equals("male"))
        {
            if(iShakhes > 0 && iShakhes <= 18)
            {
                imgWeight18.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_white_1));
                lnrWeight18.setBackgroundColor(getResources().getColor(R.color.weight_color_gray));

                txtWeightHint.setText("وزن کم");
                txtWeightHint.setTextColor(getResources().getColor(R.color.weight_color_gray));

                imgLinesFlat1.setVisibility(View.GONE);
                imgLinesNotFlat1.setVisibility(View.VISIBLE);
                imgBottomIcon.setColorFilter(getResources().getColor(R.color.user_info_complete_fat_green));


                imgWeight25.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_2));
                imgWeight30.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_3));
                imgWeight40.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_4));

            }

            if(iShakhes > 18 && iShakhes <= 25)
            {
                imgWeight25.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_white_2));
                lnrWeight25.setBackgroundColor(getResources().getColor(R.color.user_info_complete_fat_green));

                txtWeightHint.setText("وزن مناسب");
                txtWeightHint.setTextColor(getResources().getColor(R.color.user_info_complete_fat_green));

                imgLinesFlat2.setVisibility(View.GONE);
                imgLinesNotFlat2.setVisibility(View.VISIBLE);
                imgBottomIcon.setColorFilter(getResources().getColor(R.color.user_info_complete_fat_green));

                imgWeight18.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_1));
                imgWeight30.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_3));
                imgWeight40.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_4));
            }

            if(iShakhes > 25 && iShakhes <= 30)
            {
                imgWeight30.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_white_3));
                lnrWeight30.setBackgroundColor(getResources().getColor(R.color.weight_color_yellow_up));

                txtWeightHint.setText("وزن بالا");
                txtWeightHint.setTextColor(getResources().getColor(R.color.weight_color_yellow_up));

                imgLinesFlat3.setVisibility(View.GONE);
                imgLinesNotFlat3.setVisibility(View.VISIBLE);
                imgBottomIcon.setColorFilter(getResources().getColor(R.color.user_info_complete_fat_green));

                imgWeight18.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_1));
                imgWeight25.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_2));
                imgWeight40.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_4));
            }

            if(iShakhes > 30 )
            {
                imgWeight40.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_white_4));
                lnrWeight40.setBackgroundColor(getResources().getColor(R.color.weight_color_red_up));

                txtWeightHint.setText("اضافه وزن زیاد");
                txtWeightHint.setTextColor(getResources().getColor(R.color.weight_color_red_up));

                imgLinesFlat4.setVisibility(View.GONE);
                imgLinesNotFlat4.setVisibility(View.VISIBLE);
                imgBottomIcon.setColorFilter(getResources().getColor(R.color.user_info_complete_fat_green));

                imgWeight18.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_1));
                imgWeight25.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_2));
                imgWeight30.setImageDrawable(getResources().getDrawable(R.drawable.fat_man_3));
            }
        }




        if(strMaleOrFemale.equals("female"))
        {
            if(iShakhes > 0 && iShakhes <= 18)
            {
                imgWeight18.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_white_1));
                lnrWeight18.setBackgroundColor(getResources().getColor(R.color.weight_color_gray));

                txtWeightHint.setText("وزن کم");
                txtWeightHint.setTextColor(getResources().getColor(R.color.weight_color_gray));

                imgLinesFlat1.setVisibility(View.GONE);
                imgLinesNotFlat1.setVisibility(View.VISIBLE);
                imgBottomIcon.setColorFilter(getResources().getColor(R.color.user_info_complete_fat_green));

                imgWeight25.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_2));
                imgWeight30.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_3));
                imgWeight40.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_4));

            }

            if(iShakhes > 18 && iShakhes <= 25)
            {
                imgWeight25.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_white_2));
                lnrWeight25.setBackgroundColor(getResources().getColor(R.color.user_info_complete_fat_green));

                txtWeightHint.setText("وزن مناسب");
                txtWeightHint.setTextColor(getResources().getColor(R.color.user_info_complete_fat_green));

                imgLinesFlat2.setVisibility(View.GONE);
                imgLinesNotFlat2.setVisibility(View.VISIBLE);
                imgBottomIcon.setColorFilter(getResources().getColor(R.color.user_info_complete_fat_green));

                imgWeight18.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_1));
                imgWeight30.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_3));
                imgWeight40.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_4));
            }

            if(iShakhes > 25 && iShakhes <= 30)
            {
                imgWeight30.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_white_3));
                lnrWeight30.setBackgroundColor(getResources().getColor(R.color.weight_color_yellow_up));

                txtWeightHint.setText("وزن بالا");
                txtWeightHint.setTextColor(getResources().getColor(R.color.weight_color_yellow_up));

                imgLinesFlat3.setVisibility(View.GONE);
                imgLinesNotFlat3.setVisibility(View.VISIBLE);
                imgBottomIcon.setColorFilter(getResources().getColor(R.color.user_info_complete_fat_green));

                imgWeight18.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_1));
                imgWeight25.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_2));
                imgWeight40.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_4));
            }

            if(iShakhes > 30 )
            {
                imgWeight40.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_white_4));
                lnrWeight40.setBackgroundColor(getResources().getColor(R.color.weight_color_red_up));

                txtWeightHint.setText("اضافه وزن زیاد");
                txtWeightHint.setTextColor(getResources().getColor(R.color.weight_color_red_up));

                imgLinesFlat4.setVisibility(View.GONE);
                imgLinesNotFlat4.setVisibility(View.VISIBLE);
                imgBottomIcon.setColorFilter(getResources().getColor(R.color.user_info_complete_fat_green));

                imgWeight18.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_1));
                imgWeight25.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_2));
                imgWeight30.setImageDrawable(getResources().getDrawable(R.drawable.fat_women_3));
            }
        }


    }

    private void startActivityAfterSeconds()
    {
        final MyProgressDialog cdd = new MyProgressDialog(this, "در حال بارگزاری . . .");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                Intent intent = new Intent(ActivityUserInfoComplete.this, ActivityMain.class);
                intent.putExtra("CURRENT_WEIGHT", strWeight);
                startActivity(intent);
//                finish();
                cdd.dismiss();
                btnStart.setEnabled(true);
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
        Snackbar.make(parentLayout,  getResources().getString(R.string.user_info_back_pressed), Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
