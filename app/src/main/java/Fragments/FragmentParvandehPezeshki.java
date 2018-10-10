package Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import FontComponents.MyButton;
import FontComponents.MyEditText;
import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.Globals;
import Others.MyProgressDialog;
import Others.Settings;
import Structures.StructureCustomer;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;


public class FragmentParvandehPezeshki extends Fragment {

    //publics
    public int MaxYear;

    private List<String> lstMedicalInfo = new ArrayList<String>();

    private List<String> lstYear = new ArrayList<String>();
    private List<String> lstMonth = new ArrayList<String>();
    private List<String> lstDay = new ArrayList<String>();
    private List<String> lstRegimAdd = new ArrayList<String>();

    private List<String> lstParvandehBasicActivity = new ArrayList<String>();
    private List<String> lstParvandehBasicIntrest = new ArrayList<String>();
    private List<String> lstParvandehBasicExcersisProblem = new ArrayList<String>();

    private List<String> lstParvandehMedicalHistoryPhysiologic = new ArrayList<String>();
    private List<String> lstParvandehMedicalHistoryLaboratoryProblem = new ArrayList<String>();
    private List<String> lstParvandehMedicalHistorySurgery = new ArrayList<String>();
    private List<String> lstParvandehMedicalHistoryMedicine = new ArrayList<String>();
    private List<String> lstParvandehMedicalHistorySpecialSickness = new ArrayList<String>();

    private List<String> lstParvandehFoodHabbitationBreakfast = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationLunch = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationDinner = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationAmount = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationHabit = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationBeloved = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationSensitivity = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationFruitInDay = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationVegetables = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationRice = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationMilk = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationMeat = new ArrayList<String>();
    private List<String> lstParvandehFoodHabbitationFastFood = new ArrayList<String>();

    private List<String> strParvandehOthersNighttimeSleep = new ArrayList<String>();
    private List<String> strParvandehOthersPreviousRegime = new ArrayList<String>();
    private List<String> lstParvandehOthersNumber = new ArrayList<String>();
    private EditText edtParvandehOthersEdit;
    private TextView txtParvandehOtherNumber;
    private ImageView[] imgTicks = new ImageView[3];
    private ImageView[] btnImageGozineh = new ImageView[3];
    private TextViewFont[] txtGozineh = new TextViewFont[3];
    private TextViewFont txtGoalWeight, txtHeight, txtWeight, txtSex, txtAge, txtDoreKamar, txtDoreMoch;

    private MyButton btnSaveData;
    private View parentView;

    private MyEditText edtFoodHabitationBeloved, edtFoodHabitationSensitivity, edtParvandehYourName;

    private AppCompatSpinner spinnerYear;
    private AppCompatSpinner spinnerMonth;
    private AppCompatSpinner spinnerDay;
    private AppCompatSpinner spinnerMedicalInformation;
    private AppCompatSpinner spinnerParvandehBasicActivity;
    private AppCompatSpinner spinnerParvandehBasicIntrest;
    private AppCompatSpinner spinnerParvandehBasicExcersisProblem;
    private AppCompatSpinner spinnerParvandehMedicalHistoryPhysiologic;
    private AppCompatSpinner spinnerParvandehMedicalHistoryLaboratoryProblem;
    private AppCompatSpinner spinnerParvandehMedicalHistorySurgery;
    private AppCompatSpinner spinnerParvandehMedicalHistoryMedicine;
    private AppCompatSpinner spinnerParvandehMedicalHistorySpecialSickness;
    private AppCompatSpinner spinnerParvandehFoodHabbitationBreakfast;
    private AppCompatSpinner spinnerParvandehFoodHabbitationLunch;
    private AppCompatSpinner spinnerParvandehFoodHabbitationDinner;
    private AppCompatSpinner spinnerParvandehFoodHabbitationAmount;
    private AppCompatSpinner spinnerParvandehFoodHabbitationHabit;
    private AppCompatSpinner spinnerParvandehFoodHabbitationFruitInDay;
    private AppCompatSpinner spinnerParvandehFoodHabbitationVegetables;
    private AppCompatSpinner spinnerParvandehFoodHabbitationRice;
    private AppCompatSpinner spinnerParvandehFoodHabbitationMilk;
    private AppCompatSpinner spinnerParvandehFoodHabbitationMeat;
    private AppCompatSpinner spinnerParvandehFoodHabbitationFastFood;
    private AppCompatSpinner spinnerParvandehOthersNighttimeSleep;
    private AppCompatSpinner spinnerParvandehOthersPreviousRegime;
    private AppCompatSpinner spinnerParvandehOthersNumber;

    private Spinner mySpinner;
    private Typeface myFont;
    private FacadeService facadeService = new FacadeService();
    //private AppCompatSpinner spinnerParvandehRegim;

    private static FragmentParvandehPezeshki instance;
    public FragmentParvandehPezeshki() {
        // Required empty public constructor
    }

    public static FragmentParvandehPezeshki getInstance()
    {
        if (instance == null)
            instance = new FragmentParvandehPezeshki();

        return instance;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.activity_parvandeh_pezeshki, container, false);

        spinnerDay = parentView.findViewById(R.id.spinner_parvandeh_day);
        FrameLayout frameLayoutParvandehOne = parentView.findViewById(R.id.frame_parvandeh_one);
        FrameLayout frameLayoutParvandehTwo = parentView.findViewById(R.id.frame_parvandeh_two);
        FrameLayout frameLayoutParvandehOThree = parentView.findViewById(R.id.frame_parvandeh_three);
        //ImageView imgRegimAdd = view.findViewById(R.id.btn_parvandeh_add_regim);
        //ImageView imgRegimRemove = view.findViewById(R.id.btn_parvandeh_remove_regim);
        //spinnerParvandehRegim = view.findViewById(R.id.spinner_parvandeh_regim);
        spinnerYear = parentView.findViewById(R.id.spinner_parvandeh_year);
        spinnerMonth = parentView.findViewById(R.id.spinner_parvandeh_month);
        spinnerMedicalInformation = parentView.findViewById(R.id.spinner_parvandeh_medical_information);
        spinnerParvandehBasicActivity = parentView.findViewById(R.id.spinner_parvandeh_basic_info_activity);
        spinnerParvandehBasicIntrest = parentView.findViewById(R.id.spinner_parvandeh_basic_info_intrest);
        spinnerParvandehBasicExcersisProblem = parentView.findViewById(R.id.spinner_parvandeh_basic_info_excersis_problem);
        spinnerParvandehMedicalHistoryPhysiologic = parentView.findViewById(R.id.spinner_parvandeh_medical_history_physiologic);
        spinnerParvandehMedicalHistoryLaboratoryProblem = parentView.findViewById(R.id.spinner_parvandeh_medical_history_laboratory_problem);
        spinnerParvandehMedicalHistorySurgery = parentView.findViewById(R.id.spinner_parvandeh_medical_history_surgery);
        spinnerParvandehMedicalHistoryMedicine = parentView.findViewById(R.id.spinner_parvandeh_medical_history_medicine);
        spinnerParvandehMedicalHistorySpecialSickness = parentView.findViewById(R.id.spinner_parvandeh_medical_history_special_sickness);
        spinnerParvandehFoodHabbitationBreakfast = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_breakfast);
        spinnerParvandehFoodHabbitationLunch = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_lunch);
        spinnerParvandehFoodHabbitationDinner = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_dinner);
        spinnerParvandehFoodHabbitationAmount = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_amount);
        spinnerParvandehFoodHabbitationHabit = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_habit);
        spinnerParvandehFoodHabbitationFruitInDay = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_fruit_in_day);
        spinnerParvandehFoodHabbitationVegetables = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_vegetables);
        spinnerParvandehFoodHabbitationRice = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_rice);
        spinnerParvandehFoodHabbitationMilk = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_milk);
        spinnerParvandehFoodHabbitationMeat = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_meat);
        spinnerParvandehFoodHabbitationFastFood = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_fast_food);
        spinnerParvandehOthersNighttimeSleep = parentView.findViewById(R.id.spinner_parvandeh_others_nighttime_sleep);
        spinnerParvandehOthersPreviousRegime = parentView.findViewById(R.id.spinner_parvandeh_others_previous_regime);

        EditText spinnerParvandehFoodHabbitationBeloved = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_beloved);
        EditText spinnerParvandehFoodHabbitationSensitivity = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_sensitivity);

        spinnerParvandehOthersNumber = parentView.findViewById(R.id.spinner_parvandeh_others_number);
        edtParvandehOthersEdit = parentView.findViewById(R.id.spinner_parvandeh_others_edit);
        txtParvandehOtherNumber = parentView.findViewById(R.id.txt_parvandeh_other_number);
        btnSaveData = parentView.findViewById(R.id.btn_parvandeh_save_data);

        imgTicks[0] = parentView.findViewById(R.id.btn_parvandeh_tick_one);
        imgTicks[1] = parentView.findViewById(R.id.btn_parvandeh_tick_two);
        imgTicks[2] = parentView.findViewById(R.id.btn_parvandeh_tick_three);

        btnImageGozineh[0] = parentView.findViewById(R.id.btn_parvandeh_one);
        btnImageGozineh[1] = parentView.findViewById(R.id.btn_parvandeh_two);
        btnImageGozineh[2] = parentView.findViewById(R.id.btn_parvandeh_three);

        txtGozineh[0] = parentView.findViewById(R.id.txt_parvandeh_one);
        txtGozineh[1] = parentView.findViewById(R.id.txt_parvandeh_two);
        txtGozineh[2] = parentView.findViewById(R.id.txt_parvandeh_three);

        edtFoodHabitationBeloved = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_beloved);
        edtFoodHabitationSensitivity = parentView.findViewById(R.id.spinner_parvandeh_food_habbitation_sensitivity);
        edtParvandehYourName = parentView.findViewById(R.id.edt_parvandeh_your_name);

        txtGoalWeight = parentView.findViewById(R.id.txt_parvandeh_payeh_goal_weight);
        txtHeight = parentView.findViewById(R.id.txt_parvandeh_payeh_height);
        txtWeight = parentView.findViewById(R.id.txt_parvandeh_payeh_weight);
        txtSex = parentView.findViewById(R.id.txt_parvandeh_payeh_sex);
        txtAge = parentView.findViewById(R.id.txt_parvandeh_payeh_age);
        txtDoreKamar = parentView.findViewById(R.id.txt_parvandeh_dore_kamar);
        txtDoreMoch = parentView.findViewById(R.id.txt_parvandeh_dore_moch);


        //Inits
        MaxYear = 1396;
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);
        myFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IranSansFaNum.ttf");
        imgTicks[0].setImageResource(R.drawable.ic_parvandeh_tick_black);
        imgTicks[1].setImageResource(R.drawable.ic_parvandeh_tick_gray);
        imgTicks[2].setImageResource(R.drawable.ic_parvandeh_tick_gray);




        txtGozineh[0].setTextColor(getResources().getColor(R.color.white));
        txtGozineh[1].setTextColor(getResources().getColor(R.color.black));
        txtGozineh[2].setTextColor(getResources().getColor(R.color.black));

        ConfigAllLists();
        ConfigParvandeh();
        //SetupSpinnersAdapter(spinnerParvandehRegim, lstRegimAdd);

        SetupSpinnersAdapter(spinnerParvandehBasicActivity, lstParvandehBasicActivity);
        SetupSpinnersAdapter(spinnerParvandehBasicIntrest, lstParvandehBasicIntrest);
        SetupSpinnersAdapter(spinnerParvandehBasicExcersisProblem, lstParvandehBasicExcersisProblem);

        SetupSpinnersAdapter(spinnerParvandehMedicalHistoryPhysiologic, lstParvandehMedicalHistoryPhysiologic);
        SetupSpinnersAdapter(spinnerParvandehMedicalHistoryLaboratoryProblem, lstParvandehMedicalHistoryLaboratoryProblem);
        SetupSpinnersAdapter(spinnerParvandehMedicalHistorySurgery, lstParvandehMedicalHistorySurgery);
        SetupSpinnersAdapter(spinnerParvandehMedicalHistoryMedicine, lstParvandehMedicalHistoryMedicine);
        SetupSpinnersAdapter(spinnerParvandehMedicalHistorySpecialSickness, lstParvandehMedicalHistorySpecialSickness);

        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationBreakfast, lstParvandehFoodHabbitationBreakfast);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationLunch, lstParvandehFoodHabbitationLunch);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationDinner, lstParvandehFoodHabbitationDinner);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationAmount, lstParvandehFoodHabbitationAmount);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationHabit, lstParvandehFoodHabbitationHabit);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationFruitInDay, lstParvandehFoodHabbitationFruitInDay);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationVegetables, lstParvandehFoodHabbitationVegetables);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationRice, lstParvandehFoodHabbitationRice);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationMilk, lstParvandehFoodHabbitationMilk);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationMeat, lstParvandehFoodHabbitationMeat);
        SetupSpinnersAdapter(spinnerParvandehFoodHabbitationFastFood, lstParvandehFoodHabbitationFastFood);

        SetupSpinnersAdapter(spinnerParvandehOthersNighttimeSleep, strParvandehOthersNighttimeSleep);
        SetupSpinnersAdapter(spinnerParvandehOthersPreviousRegime, strParvandehOthersPreviousRegime);
        SetupSpinnersAdapter(spinnerParvandehOthersNumber, lstParvandehOthersNumber);
        txtParvandehOtherNumber.setEnabled(false);
        txtParvandehOtherNumber.setTextColor(getResources().getColor(R.color.gray));
        //spinnerParvandehOthersNumber.setEnabled(false);


        spinnerParvandehOthersPreviousRegime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    txtParvandehOtherNumber.setEnabled(false);
                    txtParvandehOtherNumber.setTextColor(getResources().getColor(R.color.gray));
                    spinnerParvandehOthersNumber.setEnabled(false);
                }

                if(i == 1)
                {
                    txtParvandehOtherNumber.setEnabled(true);
                    txtParvandehOtherNumber.setTextColor(getResources().getColor(R.color.black));
                    spinnerParvandehOthersNumber.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        for (int i = 1300; i <= MaxYear; i++)
            lstYear.add(String.valueOf(i));

        for (int i = 1; i <= 12; i++) {
            lstMonth.add(String.valueOf(i));
        }


        SetupSpinnersAdapter(spinnerYear, lstYear);
        SetupSpinnersAdapter(spinnerMonth, lstMonth);
        SetpDaysList(spinnerMonth);

        SetupSpinnersAdapter(spinnerMedicalInformation, lstMedicalInfo);
        spinnerMedicalInformation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        frameLayoutParvandehOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChandGozineheeController(true, false, false);
            }
        });

        frameLayoutParvandehTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChandGozineheeController(false, true, false);
            }
        });

        frameLayoutParvandehOThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChandGozineheeController(false, false, true);
            }
        });

        /*imgRegimAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowRegimDialog();
            }
        });

        imgRegimRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerParvandehRegim.removeViewAt(0);
            }
        });*/


        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();

                StructureCustomer myProfile = new StructureCustomer();
                Settings.getInstance().loadAll();
                myProfile = Settings.getInstance().getProfile();

                myProfile.activityInfo.setA(((TextView)(spinnerParvandehBasicActivity.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.activityInfo.setB(((TextView)(spinnerParvandehBasicIntrest.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.activityInfo.setC(((TextView)(spinnerParvandehBasicExcersisProblem.getSelectedView().findViewById(R.id.textView1))).getText().toString());

                myProfile.medicalInfo.setA(((TextView)(spinnerParvandehMedicalHistorySpecialSickness.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.medicalInfo.setB(((TextView)(spinnerParvandehMedicalHistoryMedicine.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.medicalInfo.setC(((TextView)(spinnerParvandehMedicalHistorySurgery.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.medicalInfo.setD(((TextView)(spinnerParvandehMedicalHistoryLaboratoryProblem.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.medicalInfo.setE(((TextView)(spinnerParvandehMedicalHistoryPhysiologic.getSelectedView().findViewById(R.id.textView1))).getText().toString());

                myProfile.foodHabitInfo.setB(((TextView)(spinnerParvandehFoodHabbitationBreakfast.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setC(((TextView)(spinnerParvandehFoodHabbitationLunch.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setD(((TextView)(spinnerParvandehFoodHabbitationDinner.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setE(((TextView)(spinnerParvandehFoodHabbitationAmount.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setF(((TextView)(spinnerParvandehFoodHabbitationHabit.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setG(edtFoodHabitationBeloved.getText().toString());
                myProfile.foodHabitInfo.setH(edtFoodHabitationSensitivity.getText().toString());
                myProfile.foodHabitInfo.setI(((TextView)(spinnerParvandehFoodHabbitationFruitInDay.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setJ(((TextView)(spinnerParvandehFoodHabbitationVegetables.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setK(((TextView)(spinnerParvandehFoodHabbitationRice.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setL(((TextView)(spinnerParvandehFoodHabbitationMilk.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setM(((TextView)(spinnerParvandehFoodHabbitationMeat.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.foodHabitInfo.setN(((TextView)(spinnerParvandehFoodHabbitationFastFood.getSelectedView().findViewById(R.id.textView1))).getText().toString());

                myProfile.otherInfo.setA(((TextView)(spinnerParvandehOthersNighttimeSleep.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                String str = spinnerParvandehOthersPreviousRegime.getSelectedItem().toString();
                myProfile.otherInfo.setB(((TextView)(spinnerParvandehOthersPreviousRegime.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.otherInfo.setC(((TextView)(spinnerParvandehOthersNumber.getSelectedView().findViewById(R.id.textView1))).getText().toString());
                myProfile.otherInfo.setD(edtParvandehOthersEdit.getText().toString());

                myProfile.profile = Settings.getInstance().getProfile().profile;
                myProfile.basicInfo = Settings.getInstance().getProfile().basicInfo;

                myProfile.basicInfo.setBirthDate(
                    ((TextView)(spinnerYear.getSelectedView().findViewById(R.id.textView1))).getText().toString() + "/" +
                    ((TextView)(spinnerMonth.getSelectedView().findViewById(R.id.textView1))).getText().toString() + "/" +
                    ((TextView)(spinnerDay.getSelectedView().findViewById(R.id.textView1))).getText().toString());

                final StructureCustomer newProfile = myProfile;

                facadeService.UpdateProfile(new DataCallbbackOutput() {
                    @Override
                    public void onSuccess(String result) {
                        String token = Settings.getInstance().getProfile().getAuthorizationToken();
                        Settings.getInstance().setProfile(newProfile);
                        Settings.getInstance().getProfile().setAuthorizationToken(token);
                        Settings.getInstance().saveAll();
                        Snackbar.make(getActivity().findViewById(android.R.id.content), Html.fromHtml("<font color=\"#00FF00\">ذخیره اطلاعات موفقیت آمیز بود</font>"), Snackbar.LENGTH_LONG)
                                .addCallback(new Snackbar.Callback(){
                                    @Override
                                    public void onDismissed(Snackbar snackbar, int event) {
                                        ActivityMain.fragmentManager.beginTransaction().replace(
                                                ActivityMain.globalFrame.getId(), FragmentProfileMoshaver.getInstance(), "PROFILE_MOSHAVER_TAG").addToBackStack("PROFILE_MOSHAVER_TAG").commit();
                                    }

                                    @Override
                                    public void onShown(Snackbar snackbar) {

                                    }
                                })
                                .show();

                        cdd.dismiss();
                    }
                }, getActivity(), myProfile, parentView, false);

            }
        });



//        @Override
//        protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
//            super.onFocusChanged(focused, direction, previouslyFocusedRect);
//            if(focused)
//            {
//                this.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.shape_edittext_focused));
//            }
//            else
//            {
//                this.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.shape_edittext));
//            }
//        }

        edtFoodHabitationBeloved.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        edtFoodHabitationSensitivity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        edtParvandehOthersEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    view.setBackgroundResource(R.drawable.shape_big_edittext_focused);
                }
                else
                {
                    view.setBackgroundResource(R.drawable.shape_big_edittext);
                }
            }
        });

        edtParvandehYourName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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


        Settings.getInstance().setbOneTimeFillSpinnerParvandeh(false);
        Settings.getInstance().saveAll();


        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("AAAAAAAAAAAAAAA", spinnerDay.getItemAtPosition(i).toString() + "   " +
                        spinnerDay.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerParvandehOthersNighttimeSleep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("BBBBBBBBBBBBBBBBB", spinnerParvandehOthersNighttimeSleep.getItemAtPosition(i).toString() + "   " +
                        spinnerParvandehOthersNighttimeSleep.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        return parentView;
    }

    void ConfigParvandeh()
    {
        try {
            final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.show();
            if (Settings.getInstance().getProfile() != null) {
                spinnerYear.setSelection(getSpinnerIndex(spinnerYear, Settings.getInstance().getProfile().basicInfo.getBirthDate().split("/")[0]));
                spinnerMonth.setSelection(getSpinnerIndex(spinnerMonth, Settings.getInstance().getProfile().basicInfo.getBirthDate().split("/")[1]));
                spinnerDay.setSelection(getSpinnerIndex(spinnerDay, Settings.getInstance().getProfile().basicInfo.getBirthDate().split("/")[2]));

                spinnerParvandehMedicalHistorySpecialSickness.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistorySpecialSickness, Settings.getInstance().getProfile().medicalInfo.getA()));
                spinnerParvandehMedicalHistoryMedicine.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistoryMedicine, Settings.getInstance().getProfile().medicalInfo.getB()));
                spinnerParvandehMedicalHistorySurgery.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistorySurgery, Settings.getInstance().getProfile().medicalInfo.getC()));
                spinnerParvandehMedicalHistoryLaboratoryProblem.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistoryLaboratoryProblem, Settings.getInstance().getProfile().medicalInfo.getD()));
                spinnerParvandehMedicalHistoryPhysiologic.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistoryPhysiologic, Settings.getInstance().getProfile().medicalInfo.getE()));

                spinnerParvandehBasicActivity.setSelection(getSpinnerIndex(spinnerParvandehBasicActivity, Settings.getInstance().getProfile().activityInfo.getA()));
                spinnerParvandehBasicIntrest.setSelection(getSpinnerIndex(spinnerParvandehBasicIntrest, Settings.getInstance().getProfile().activityInfo.getB()));
                spinnerParvandehBasicExcersisProblem.setSelection(getSpinnerIndex(spinnerParvandehBasicExcersisProblem, Settings.getInstance().getProfile().activityInfo.getC()));

                spinnerParvandehFoodHabbitationBreakfast.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationBreakfast, Settings.getInstance().getProfile().foodHabitInfo.getB()));
                spinnerParvandehFoodHabbitationLunch.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationLunch, Settings.getInstance().getProfile().foodHabitInfo.getC()));
                spinnerParvandehFoodHabbitationDinner.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationDinner, Settings.getInstance().getProfile().foodHabitInfo.getD()));
                spinnerParvandehFoodHabbitationAmount.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationAmount, Settings.getInstance().getProfile().foodHabitInfo.getE()));
                spinnerParvandehFoodHabbitationHabit.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationHabit, Settings.getInstance().getProfile().foodHabitInfo.getF()));
                edtFoodHabitationBeloved.setText(Settings.getInstance().getProfile().foodHabitInfo.getG());
                edtFoodHabitationSensitivity.setText(Settings.getInstance().getProfile().foodHabitInfo.getH());
                spinnerParvandehFoodHabbitationFruitInDay.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationFruitInDay, Settings.getInstance().getProfile().foodHabitInfo.getI()));
                spinnerParvandehFoodHabbitationVegetables.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationVegetables, Settings.getInstance().getProfile().foodHabitInfo.getJ()));
                spinnerParvandehFoodHabbitationRice.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationRice, Settings.getInstance().getProfile().foodHabitInfo.getK()));
                spinnerParvandehFoodHabbitationMilk.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationMilk, Settings.getInstance().getProfile().foodHabitInfo.getL()));
                spinnerParvandehFoodHabbitationMeat.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationMeat, Settings.getInstance().getProfile().foodHabitInfo.getM()));
                spinnerParvandehFoodHabbitationFastFood.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationFastFood, Settings.getInstance().getProfile().foodHabitInfo.getN()));

                spinnerParvandehOthersNighttimeSleep.setSelection(getSpinnerIndex(spinnerParvandehOthersNighttimeSleep, Settings.getInstance().getProfile().otherInfo.getA()));
                spinnerParvandehOthersPreviousRegime.setSelection(getSpinnerIndex(spinnerParvandehOthersPreviousRegime, Settings.getInstance().getProfile().otherInfo.getB()));
                spinnerParvandehOthersNumber.setSelection(getSpinnerIndex(spinnerParvandehOthersNumber, Settings.getInstance().getProfile().otherInfo.getC()));
                edtParvandehOthersEdit.setText(Settings.getInstance().getProfile().otherInfo.getD());


                txtGoalWeight.setText(String.valueOf((int) Float.parseFloat(Settings.getInstance().getDashbord().getIdealWeight())));
                txtHeight.setText(String.valueOf(Settings.getInstance().getProfile().basicInfo.getHeight()));
                txtWeight.setText(String.valueOf(Settings.getInstance().getProfile().basicInfo.getWeight()));
                if(Settings.getInstance().getProfile().basicInfo.getGender() == 0)
                    txtSex.setText("خانم");
                else
                if(Settings.getInstance().getProfile().basicInfo.getGender() == 1)
                    txtSex.setText("آقا");
                else
                    txtSex.setText("نامشخص");

                txtAge.setText(String.valueOf(Settings.getInstance().getProfile().basicInfo.getAge()));
                txtDoreKamar.setText(String.valueOf(Settings.getInstance().getProfile().basicInfo.getWaist()));
            } else {


                facadeService.GetProfile(new DataCallbbackOutput() {
                    @Override
                    public void onSuccess(String result) {
                        StructureCustomer parvandehPezeshki = new Gson().fromJson(result, StructureCustomer.class);
                        if (parvandehPezeshki != null) {
                            spinnerParvandehMedicalHistoryPhysiologic.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistoryPhysiologic, parvandehPezeshki.medicalInfo.getE()));
                            spinnerParvandehMedicalHistoryLaboratoryProblem.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistoryLaboratoryProblem, parvandehPezeshki.medicalInfo.getD()));
                            spinnerParvandehMedicalHistorySurgery.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistorySurgery, parvandehPezeshki.medicalInfo.getC()));
                            spinnerParvandehMedicalHistoryMedicine.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistoryMedicine, parvandehPezeshki.medicalInfo.getB()));
                            spinnerParvandehMedicalHistorySpecialSickness.setSelection(getSpinnerIndex(spinnerParvandehMedicalHistorySpecialSickness, parvandehPezeshki.medicalInfo.getA()));

                            spinnerParvandehBasicActivity.setSelection(getSpinnerIndex(spinnerParvandehBasicActivity, parvandehPezeshki.activityInfo.getA()));
                            spinnerParvandehBasicIntrest.setSelection(getSpinnerIndex(spinnerParvandehBasicIntrest, parvandehPezeshki.activityInfo.getB()));
                            spinnerParvandehBasicExcersisProblem.setSelection(getSpinnerIndex(spinnerParvandehBasicExcersisProblem, parvandehPezeshki.activityInfo.getC()));

                            spinnerParvandehFoodHabbitationBreakfast.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationBreakfast, parvandehPezeshki.foodHabitInfo.getB()));
                            spinnerParvandehFoodHabbitationLunch.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationLunch, parvandehPezeshki.foodHabitInfo.getC()));
                            spinnerParvandehFoodHabbitationDinner.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationDinner, parvandehPezeshki.foodHabitInfo.getD()));
                            spinnerParvandehFoodHabbitationAmount.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationAmount, parvandehPezeshki.foodHabitInfo.getE()));
                            spinnerParvandehFoodHabbitationHabit.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationHabit, parvandehPezeshki.foodHabitInfo.getF()));
                            edtFoodHabitationBeloved.setText(parvandehPezeshki.foodHabitInfo.getG());
                            edtFoodHabitationSensitivity.setText(parvandehPezeshki.foodHabitInfo.getH());
                            spinnerParvandehFoodHabbitationFruitInDay.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationFruitInDay, parvandehPezeshki.foodHabitInfo.getI()));
                            spinnerParvandehFoodHabbitationVegetables.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationVegetables, parvandehPezeshki.foodHabitInfo.getJ()));
                            spinnerParvandehFoodHabbitationRice.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationRice, parvandehPezeshki.foodHabitInfo.getK()));
                            spinnerParvandehFoodHabbitationMilk.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationMilk, parvandehPezeshki.foodHabitInfo.getL()));
                            spinnerParvandehFoodHabbitationMeat.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationMeat, parvandehPezeshki.foodHabitInfo.getM()));
                            spinnerParvandehFoodHabbitationFastFood.setSelection(getSpinnerIndex(spinnerParvandehFoodHabbitationFastFood, parvandehPezeshki.foodHabitInfo.getN()));

                            spinnerParvandehOthersNighttimeSleep.setSelection(getSpinnerIndex(spinnerParvandehOthersNighttimeSleep, parvandehPezeshki.otherInfo.getA()));
                            spinnerParvandehOthersPreviousRegime.setSelection(getSpinnerIndex(spinnerParvandehOthersPreviousRegime, parvandehPezeshki.otherInfo.getB()));
                            spinnerParvandehOthersNumber.setSelection(getSpinnerIndex(spinnerParvandehOthersNumber, parvandehPezeshki.otherInfo.getC()));


                            if (parvandehPezeshki.basicInfo.getBirthDate() != null) {
                                String[] separated = parvandehPezeshki.basicInfo.getBirthDate().split("/");

                                spinnerYear.setSelection(getSpinnerIndex(spinnerDay, separated[0]));
                                spinnerMonth.setSelection(getSpinnerIndex(spinnerDay, separated[1]));
                                spinnerDay.setSelection(getSpinnerIndex(spinnerDay, separated[2]));
                            }

                            parvandehPezeshki.setAuthorizationToken(Settings.getInstance().getProfile().getAuthorizationToken());
                            parvandehPezeshki.profile = Settings.getInstance().getProfile().profile;
                            parvandehPezeshki.basicInfo = Settings.getInstance().getProfile().basicInfo;

                            Settings.getInstance().setProfile(parvandehPezeshki);
                            Settings.getInstance().saveAll();
                        }


                    }
                }, getActivity());
            }

            cdd.dismiss();
        }catch(Exception e)
        {

        }

//        if(strType.equals("local")) {
//            spinnerYear.setSelection(Settings.getInstance().getProfile().getBirthday_Day());
//            spinnerMonth.setSelection(Settings.getInstance().getProfile().getBirthday_Month());
//            spinnerDay.setSelection(Settings.getInstance().getProfile().getBirthday_Year());
//
//            spinnerParvandehMedicalHistoryPhysiologic.setSelection(Settings.getInstance().getProfile().getMedicalHistorySharayetePhysiology());
//            spinnerParvandehMedicalHistoryLaboratoryProblem.setSelection(Settings.getInstance().getProfile().getMedicalHistoryMoshkeleAzmayeshgahi());
//            spinnerParvandehMedicalHistorySurgery.setSelection(Settings.getInstance().getProfile().getMedicalHistorySaabegheAmaleJarrahi());
//            spinnerParvandehMedicalHistoryMedicine.setSelection(Settings.getInstance().getProfile().getMedicalHistoryDarooKhas());
//            spinnerParvandehMedicalHistorySpecialSickness.setSelection(Settings.getInstance().getProfile().getMedicalHistoryBimariKhas());
//
//            spinnerParvandehBasicActivity.setSelection(Settings.getInstance().getProfile().getBasicFaaliatBadani());
//            spinnerParvandehBasicIntrest.setSelection(Settings.getInstance().getProfile().getBasicAlagheBeVarzesh());
//            spinnerParvandehBasicExcersisProblem.setSelection(Settings.getInstance().getProfile().getBasicMoshkeleVarzesh());
//
//            spinnerParvandehFoodHabbitationBreakfast.setSelection(Settings.getInstance().getProfile().getFoodHabbitationBreakfastAmount());
//            spinnerParvandehFoodHabbitationLunch.setSelection(Settings.getInstance().getProfile().getFoodHabbitationLunchAmount());
//            spinnerParvandehFoodHabbitationDinner.setSelection(Settings.getInstance().getProfile().getFoodHabbitationDinnerAmount());
//            spinnerParvandehFoodHabbitationAmount.setSelection(Settings.getInstance().getProfile().getFoodHabbitationMianVadeh());
//            spinnerParvandehFoodHabbitationHabit.setSelection(Settings.getInstance().getProfile().getFoodHabbitationRizeKhaari());
//            edtFoodHabitationBeloved.setText(Settings.getInstance().getProfile().getFoodHabbitationGhazayeMoredeAlaghe());
//            edtFoodHabitationSensitivity.setText(Settings.getInstance().getProfile().getFoodHabbitationGhazaiiKeDoostNadaridYaHassasid());
//            spinnerParvandehFoodHabbitationFruitInDay.setSelection(Settings.getInstance().getProfile().getFoodHabbitationMivehAmount());
//            spinnerParvandehFoodHabbitationVegetables.setSelection(Settings.getInstance().getProfile().getFoodHabbitationSabzijatAmount());
//            spinnerParvandehFoodHabbitationRice.setSelection(Settings.getInstance().getProfile().getFoodHabbitationBerenjAmount());
//            spinnerParvandehFoodHabbitationMilk.setSelection(Settings.getInstance().getProfile().getFoodHabbitationMilkAmount());
//            spinnerParvandehFoodHabbitationMeat.setSelection(Settings.getInstance().getProfile().getFoodHabbitationMeatAmount());
//            spinnerParvandehFoodHabbitationFastFood.setSelection(Settings.getInstance().getProfile().getFoodHabbitationFastFoodAmount());
//
//            spinnerParvandehOthersNighttimeSleep.setSelection(Settings.getInstance().getProfile().getOthersSleepAmount());
//            spinnerParvandehOthersPreviousRegime.setSelection(Settings.getInstance().getProfile().getOthersOldRegimOrNot());
//            spinnerParvandehOthersNumber.setSelection(Settings.getInstance().getProfile().getOthersNumberToRegim());
//
//            txtGoalWeight.setText(Settings.getInstance().getProfile().getGoalWeight());
//            txtHeight.setText(Settings.getInstance().getProfile().getHeight());
//            txtWeight.setText(Settings.getInstance().getProfile().getWeight());
//            txtSex.setText(Settings.getInstance().getProfile().getSex());
//            txtAge.setText(Settings.getInstance().getProfile().getAge());
//            txtDoreKamar.setText(Settings.getInstance().getProfile().getDoreKamar());
//            txtDoreMoch.setText(Settings.getInstance().getProfile().getDoreMoch());
//
//        }
//
//        if(strType.equals("network"))
//        {
//            spinnerYear.setSelection(parvandehPezeshki.getBirthday_Day());
//            spinnerMonth.setSelection(parvandehPezeshki.getBirthday_Month());
//            spinnerDay.setSelection(parvandehPezeshki.getBirthday_Year());
//
//            spinnerParvandehMedicalHistoryPhysiologic.setSelection(parvandehPezeshki.getMedicalHistorySharayetePhysiology());
//            spinnerParvandehMedicalHistoryLaboratoryProblem.setSelection(parvandehPezeshki.getMedicalHistoryMoshkeleAzmayeshgahi());
//            spinnerParvandehMedicalHistorySurgery.setSelection(parvandehPezeshki.getMedicalHistorySaabegheAmaleJarrahi());
//            spinnerParvandehMedicalHistoryMedicine.setSelection(parvandehPezeshki.getMedicalHistoryDarooKhas());
//            spinnerParvandehMedicalHistorySpecialSickness.setSelection(parvandehPezeshki.getMedicalHistoryBimariKhas());
//
//            spinnerParvandehBasicActivity.setSelection(parvandehPezeshki.getBasicFaaliatBadani());
//            spinnerParvandehBasicIntrest.setSelection(parvandehPezeshki.getBasicAlagheBeVarzesh());
//            spinnerParvandehBasicExcersisProblem.setSelection(parvandehPezeshki.getBasicMoshkeleVarzesh());
//
//            spinnerParvandehFoodHabbitationBreakfast.setSelection(parvandehPezeshki.getFoodHabbitationBreakfastAmount());
//            spinnerParvandehFoodHabbitationLunch.setSelection(parvandehPezeshki.getFoodHabbitationLunchAmount());
//            spinnerParvandehFoodHabbitationDinner.setSelection(parvandehPezeshki.getFoodHabbitationDinnerAmount());
//            spinnerParvandehFoodHabbitationAmount.setSelection(parvandehPezeshki.getFoodHabbitationMianVadeh());
//            spinnerParvandehFoodHabbitationHabit.setSelection(parvandehPezeshki.getFoodHabbitationRizeKhaari());
//            edtFoodHabitationBeloved.setText(parvandehPezeshki.getFoodHabbitationGhazayeMoredeAlaghe());
//            edtFoodHabitationSensitivity.setText(parvandehPezeshki.getFoodHabbitationGhazaiiKeDoostNadaridYaHassasid());
//            spinnerParvandehFoodHabbitationFruitInDay.setSelection(parvandehPezeshki.getFoodHabbitationMivehAmount());
//            spinnerParvandehFoodHabbitationVegetables.setSelection(parvandehPezeshki.getFoodHabbitationSabzijatAmount());
//            spinnerParvandehFoodHabbitationRice.setSelection(parvandehPezeshki.getFoodHabbitationBerenjAmount());
//            spinnerParvandehFoodHabbitationMilk.setSelection(parvandehPezeshki.getFoodHabbitationMilkAmount());
//            spinnerParvandehFoodHabbitationMeat.setSelection(parvandehPezeshki.getFoodHabbitationMeatAmount());
//            spinnerParvandehFoodHabbitationFastFood.setSelection(parvandehPezeshki.getFoodHabbitationFastFoodAmount());
//
//            spinnerParvandehOthersNighttimeSleep.setSelection(parvandehPezeshki.getOthersSleepAmount());
//            spinnerParvandehOthersPreviousRegime.setSelection(parvandehPezeshki.getOthersOldRegimOrNot());
//            spinnerParvandehOthersNumber.setSelection(parvandehPezeshki.getOthersNumberToRegim());
//
//            txtGoalWeight.setText(parvandehPezeshki.getGoalWeight());
//            txtHeight.setText(parvandehPezeshki.getHeight());
//            txtWeight.setText(parvandehPezeshki.getWeight());
//            txtSex.setText(parvandehPezeshki.getSex());
//            txtAge.setText(parvandehPezeshki.getAge());
//            txtDoreKamar.setText(parvandehPezeshki.getDoreKamar());
//            txtDoreMoch.setText(parvandehPezeshki.getDoreMoch());
//        }
    }

    private int getSpinnerIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    //Others
    void SetupSpinnersAdapter(AppCompatSpinner spinner, List<String> lst)
    {
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.layout_spinner, lst);
//        dataAdapter.setDropDownViewResource(R.layout.layout_spinner);
//        spinner.setAdapter(dataAdapter);
        spinner.setAdapter(null);
        MyArrayAdapter ma = new MyArrayAdapter(getContext(), lst);
        spinner.setAdapter(ma);
    }

    void SetpDaysList(final AppCompatSpinner spinner)
    {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lstDay.clear();
                if (i <= 5) {
                    for (int j = 1; j <= 31; j++)
                        lstDay.add(String.valueOf(j));
                }

                if (i > 5 && i <= 10) {
                    for (int j = 1; j <= 30; j++)
                        lstDay.add(String.valueOf(j));
                }

                if (i > 10) {
                    for (int j = 1; j <= 29; j++)
                        lstDay.add(String.valueOf(j));
                }

                SetupSpinnersAdapter(spinnerDay, lstDay);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void ChandGozineheeController(boolean bOne, boolean bTwo, boolean bThree)
    {
        int tickColorOne = -1, tickColorTwo = -1, tickColorThree = -1;
        int fontColorOne = -1, fontColorTwo = -1, fontColorThree = -1;
        int bkColorOne = -1, bkColorTwo = -1, bkColorThree = -1;

        if(bOne)
        {
            tickColorOne = R.drawable.ic_parvandeh_tick_black;
            tickColorThree = tickColorTwo = R.drawable.ic_parvandeh_tick_gray;

            fontColorOne = R.color.white;
            fontColorThree = fontColorTwo = R.color.black;

            bkColorOne = R.drawable.shape_button_2;
            bkColorThree = bkColorTwo = R.drawable.shape_button_3;
        }

        if(bTwo)
        {
            tickColorTwo = R.drawable.ic_parvandeh_tick_black;
            tickColorThree = tickColorOne = R.drawable.ic_parvandeh_tick_gray;

            fontColorTwo = R.color.white;
            fontColorThree = fontColorOne = R.color.black;

            bkColorTwo = R.drawable.shape_button_2;
            bkColorThree = bkColorOne = R.drawable.shape_button_3;
        }

        if(bThree)
        {
            tickColorThree = R.drawable.ic_parvandeh_tick_black;
            tickColorOne = tickColorTwo = R.drawable.ic_parvandeh_tick_gray;

            fontColorThree = R.color.white;
            fontColorOne = fontColorTwo = R.color.black;

            bkColorThree = R.drawable.shape_button_2;
            bkColorOne = bkColorTwo = R.drawable.shape_button_3;
        }

        imgTicks[0].setImageResource(tickColorOne);
        imgTicks[1].setImageResource(tickColorTwo);
        imgTicks[2].setImageResource(tickColorThree);
//        ((ImageView)view.findViewById(R.id.btn_parvandeh_tick_one))
//                .setBackgroundResource(tickColorOne);
//        ((ImageView)view.findViewById(R.id.btn_parvandeh_tick_two))
//                .setBackgroundResource(tickColorTwo);
//        ((ImageView)view.findViewById(R.id.btn_parvandeh_tick_three))
//                .setBackgroundResource(tickColorThree);


        ((TextViewFont)parentView.findViewById(R.id.txt_parvandeh_one)).setTextColor(getResources().getColor(fontColorOne));
        ((TextViewFont)parentView.findViewById(R.id.txt_parvandeh_two)).setTextColor(getResources().getColor(fontColorTwo));
        ((TextViewFont)parentView.findViewById(R.id.txt_parvandeh_three)).setTextColor(getResources().getColor(fontColorThree));

        ((ImageView)parentView.findViewById(R.id.btn_parvandeh_one))
                .setBackground(getResources().getDrawable(bkColorOne));
        ((ImageView)parentView.findViewById(R.id.btn_parvandeh_two))
                .setBackground(getResources().getDrawable(bkColorTwo));
        ((ImageView)parentView.findViewById(R.id.btn_parvandeh_three))
                .setBackground(getResources().getDrawable(bkColorThree));
    }

    /*void ShowRegimDialog()
    {
        Button btnOk;
        Button btnCancel;
        final EditText editTextDialog;
        AlertDialog.Builder alert;
        View alertLayout;
        final AlertDialog dialog;
        LayoutInflater inflater;

        alert = new AlertDialog.Builder(context);
        inflater = getLayoutInflater();
        alertLayout = inflater.inflate(R.layout.layout_parvandeh_regim_dialog, null);
        alert.setView(alertLayout);

        btnOk = (Button)alertLayout.findViewById(R.id.btn_parvandeh_regim_dialog_ok);
        btnCancel = (Button)alertLayout.findViewById(R.id.btn_parvandeh_regim_dialog_cancel);
        editTextDialog = (EditText)alertLayout.findViewById(R.id.edt_parvandeh_regim_dialog);
        dialog = alert.create();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextDialog.getText().toString().equals("") || editTextDialog.getText().toString().equals(null))
                {
                    //Do Nothing
                }
                else
                {
                    lstRegimAdd.add(editTextDialog.getText().toString());
                    SetupSpinnersAdapter(spinnerParvandehRegim, lstRegimAdd);
                    dialog.dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }*/


    void ConfigAllLists()
    {
        lstRegimAdd.clear();
        lstParvandehBasicActivity.clear();
        lstParvandehBasicIntrest.clear();
        lstParvandehBasicExcersisProblem.clear();
        lstParvandehMedicalHistorySpecialSickness.clear();
        lstParvandehMedicalHistoryMedicine.clear();
        lstParvandehMedicalHistorySurgery.clear();
        lstParvandehMedicalHistoryLaboratoryProblem.clear();
        lstParvandehMedicalHistoryPhysiologic.clear();
        lstParvandehFoodHabbitationBreakfast.clear();
        lstParvandehFoodHabbitationLunch.clear();
        lstParvandehFoodHabbitationDinner.clear();
        lstParvandehFoodHabbitationAmount.clear();
        lstParvandehFoodHabbitationHabit.clear();
        lstParvandehFoodHabbitationFruitInDay.clear();
        lstParvandehFoodHabbitationVegetables.clear();
        lstParvandehFoodHabbitationRice.clear();
        lstParvandehFoodHabbitationMilk.clear();
        lstParvandehFoodHabbitationMeat.clear();
        lstParvandehFoodHabbitationFastFood.clear();
        lstParvandehOthersNumber.clear();
        lstMedicalInfo.clear();
        strParvandehOthersNighttimeSleep.clear();
        strParvandehOthersPreviousRegime.clear();

        lstParvandehBasicActivity.add(" ");
        lstParvandehBasicActivity.add("بدون ورزش و فعاليت");
        lstParvandehBasicActivity.add("فعاليت کم (کمتر از 2 ساعت ورزش یا فعالیت بدنی در هفته)");
        lstParvandehBasicActivity.add("فعاليت متوسط (2 تا 4 ساعت ورزش یا فعالیت بدنی در هفته)");
        lstParvandehBasicActivity.add("خيلي فعال (ورزش یا فعالیت روزانه)");
        lstParvandehBasicActivity.add("ورزشکار حرفه ای");

        lstParvandehBasicIntrest.add(" ");
        lstParvandehBasicIntrest.add("پیاده روی");
        lstParvandehBasicIntrest.add("دویدن");
        lstParvandehBasicIntrest.add("شنا");
        lstParvandehBasicIntrest.add("ورزش های سالنی");
        lstParvandehBasicIntrest.add("بدنسازی");
        lstParvandehBasicIntrest.add("سایر (توضیح دهید)");

        lstParvandehBasicExcersisProblem.add(" ");
        lstParvandehBasicExcersisProblem.add("خیر");
        lstParvandehBasicExcersisProblem.add("بله (توضیح دهید)");



        lstParvandehMedicalHistorySpecialSickness.add(" ");
        lstParvandehMedicalHistorySpecialSickness.add("خیر");
        lstParvandehMedicalHistorySpecialSickness.add("بیماری قلبی عروقی");
        lstParvandehMedicalHistorySpecialSickness.add("فشار خون بالا");
        lstParvandehMedicalHistorySpecialSickness.add("دیابت (نوع 1، نوع 2 و ...)");
        lstParvandehMedicalHistorySpecialSickness.add("مشکلات کلیوی (سنگ کلیه، دیالیز و ...)");
        lstParvandehMedicalHistorySpecialSickness.add("مشکلات تیروئیدی (کم کاری، پرکاری، عمل جراحی و ...)");
        lstParvandehMedicalHistorySpecialSickness.add("مشکلات گوارشی (نفخ، یبوست، ترش کردن معده و ...)");
        lstParvandehMedicalHistorySpecialSickness.add("مشکلات عصبی: (افسردگی، استرس، پرخوری عصبی و ...)");
        lstParvandehMedicalHistorySpecialSickness.add("مشکلات حرکتی (دیسک کمر، پوکی استخوان، آرتروز، روماتیسم مفصلی و ...)");
        lstParvandehMedicalHistorySpecialSickness.add("مشکلات جنسی (کاهش ميل جنسي، توقف يا دوره هاي قاعدگي نامنظم و ...)");
        lstParvandehMedicalHistorySpecialSickness.add("مشکلات پوستی (ریزش مو، آکنه، پوست خشک و خارش دار و ...)");
        lstParvandehMedicalHistorySpecialSickness.add("سایر مشکلات پزشکی و سلامتی (توضیح دهید)");


        lstParvandehMedicalHistoryMedicine.add(" ");
        lstParvandehMedicalHistoryMedicine.add("خیر");
        lstParvandehMedicalHistoryMedicine.add("بله (توضیح دهید)");

        lstParvandehMedicalHistorySurgery.add(" ");
        lstParvandehMedicalHistorySurgery.add("خیر");
        lstParvandehMedicalHistorySurgery.add("بله (توضیح دهید)");

        lstParvandehMedicalHistoryLaboratoryProblem.add(" ");
        lstParvandehMedicalHistoryLaboratoryProblem.add("قند خون بالا");
        lstParvandehMedicalHistoryLaboratoryProblem.add("چربی خون بالا");
        lstParvandehMedicalHistoryLaboratoryProblem.add("فقر آهن");
        lstParvandehMedicalHistoryLaboratoryProblem.add("کمبود ویتامین");
        lstParvandehMedicalHistoryLaboratoryProblem.add("اسید اوریک بالا");
        lstParvandehMedicalHistoryLaboratoryProblem.add("سایر (توضیح دهید)");

        lstParvandehMedicalHistoryPhysiologic.add(" ");
        lstParvandehMedicalHistoryPhysiologic.add("خیر");
        lstParvandehMedicalHistoryPhysiologic.add("بله (توضیح دهید)");




        lstParvandehFoodHabbitationBreakfast.add(" ");
        lstParvandehFoodHabbitationBreakfast.add("هر روز");
        lstParvandehFoodHabbitationBreakfast.add("فقط چند روز در هفته");
        lstParvandehFoodHabbitationBreakfast.add("صبحانه نمی خورم");

        lstParvandehFoodHabbitationLunch.add(" ");
        lstParvandehFoodHabbitationLunch.add("هر روز");
        lstParvandehFoodHabbitationLunch.add("فقط چند روز در هفته");
        lstParvandehFoodHabbitationLunch.add("ناهار نمی خورم");

        lstParvandehFoodHabbitationDinner.add(" ");
        lstParvandehFoodHabbitationDinner.add("هر روز");
        lstParvandehFoodHabbitationDinner.add("فقط چند روز در هفته");
        lstParvandehFoodHabbitationDinner.add("شام نمی خورم");

        lstParvandehFoodHabbitationAmount.add(" ");
        lstParvandehFoodHabbitationAmount.add("روزانه 1 نوبت");
        lstParvandehFoodHabbitationAmount.add("روزانه 2 نوبت");
        lstParvandehFoodHabbitationAmount.add("روزانه 3 نوبت یا بیشتر");
        lstParvandehFoodHabbitationAmount.add("میان وعده نمی خورم");

        lstParvandehFoodHabbitationHabit.add(" ");
        lstParvandehFoodHabbitationHabit.add("خیر");
        lstParvandehFoodHabbitationHabit.add("بله (توضیح دهید)");

        //Not use
        /*lstParvandehFoodHabbitationBeloved.add("");
        lstParvandehFoodHabbitationSensitivity.add("");*/
        lstParvandehFoodHabbitationFruitInDay.add(" ");
        lstParvandehFoodHabbitationFruitInDay.add("1 یا 2 عدد");
        lstParvandehFoodHabbitationFruitInDay.add("3 یا 4 عدد");
        lstParvandehFoodHabbitationFruitInDay.add("5 عدد یا بیشتر");

        lstParvandehFoodHabbitationVegetables.add(" ");
        lstParvandehFoodHabbitationVegetables.add("مصرف نمی کنم");
        lstParvandehFoodHabbitationVegetables.add("به ندرت مصرف می کنم");
        lstParvandehFoodHabbitationVegetables.add("به طور معمول مصرف می کنم");
        lstParvandehFoodHabbitationVegetables.add("زیاد مصرف میکنم");

        lstParvandehFoodHabbitationRice.add(" ");
        lstParvandehFoodHabbitationRice.add("1 وعده غذایی");
        lstParvandehFoodHabbitationRice.add("2 وعده غذایی");
        lstParvandehFoodHabbitationRice.add("مصرف نمی کنم");

        lstParvandehFoodHabbitationMilk.add(" ");
        lstParvandehFoodHabbitationMilk.add("مصرف نمی کنم");
        lstParvandehFoodHabbitationMilk.add("به ندرت مصرف می کنم");
        lstParvandehFoodHabbitationMilk.add("به طور معمول مصرف می کنم");
        lstParvandehFoodHabbitationMilk.add("زیاد مصرف می کنم");

        lstParvandehFoodHabbitationMeat.add(" ");
        lstParvandehFoodHabbitationMeat.add("مصرف نمی کنم");
        lstParvandehFoodHabbitationMeat.add("به ندرت مصرف می کنم");
        lstParvandehFoodHabbitationMeat.add("به طور معمول مصرف می کنم");
        lstParvandehFoodHabbitationMeat.add("زیاد مصرف می کنم");

        lstParvandehFoodHabbitationFastFood.add(" ");
        lstParvandehFoodHabbitationFastFood.add("1 وعده در هفته");
        lstParvandehFoodHabbitationFastFood.add("2 وعده در هفته");
        lstParvandehFoodHabbitationFastFood.add("3 وعده در هفته یا بیشتر");
        lstParvandehFoodHabbitationFastFood.add("مصرف نمی کنم");


        strParvandehOthersNighttimeSleep.add(" ");
        strParvandehOthersNighttimeSleep.add("4 تا 6 ساعت");
        strParvandehOthersNighttimeSleep.add("6 تا 8 ساعت");
        strParvandehOthersNighttimeSleep.add("بیشتر از 8 ساعت");

        strParvandehOthersPreviousRegime.add(" ");
        strParvandehOthersPreviousRegime.add("خیر");
        strParvandehOthersPreviousRegime.add("بله");

        lstParvandehOthersNumber.add(" ");
        lstParvandehOthersNumber.add("1");
        lstParvandehOthersNumber.add("2");
        lstParvandehOthersNumber.add("3");
        lstParvandehOthersNumber.add("4");
        lstParvandehOthersNumber.add("5");






















        lstRegimAdd.add("رژیم کاهش وزن");
        lstRegimAdd.add("رژیم افزایش وزن");
        lstRegimAdd.add("رژیم برای خانمهای باردار یا شیرده");
        lstRegimAdd.add("رژیم برای بیماریهای خاص (دیابت، فشار خون و ...)");
        lstRegimAdd.add("رژیم برای دوران رشد کودکان");
        lstRegimAdd.add("رژیم برای دوران بلوغ نوجوانان");

        lstMedicalInfo.add("مورد اول");
        lstMedicalInfo.add("مورد دوم");
        lstMedicalInfo.add("مورد سوم");
        lstMedicalInfo.add("مورد چهارم");
        lstMedicalInfo.add("مورد پنجم");

        txtGoalWeight.setText("" + (int)Float.parseFloat(Settings.getInstance().getDashbord().getIdealWeight()));
        txtHeight.setText("" + Settings.getInstance().getProfile().basicInfo.getHeight());
        txtWeight.setText("" + Settings.getInstance().getProfile().basicInfo.getWeight());

        if(Settings.getInstance().getProfile().basicInfo.getGender() == 0)
            txtSex.setText("آقا");
        else
            txtSex.setText("خانم");

        txtAge.setText("" + Settings.getInstance().getProfile().basicInfo.getAge());
        txtDoreKamar.setText("" + Settings.getInstance().getProfile().basicInfo.getWaist());
        txtDoreMoch.setText("" + Settings.getInstance().getProfile().basicInfo.getWrist());

    }

    private class MyArrayAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        List<String> list;

        public MyArrayAdapter(Context con, List<String> lst) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(con);
            list = lst;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ListContent holder;
            View v = convertView;
            if (v == null) {
                v = mInflater.inflate(R.layout.my_spinner_style, null);
                holder = new ListContent();

                holder.name = (TextView) v.findViewById(R.id.textView1);

                v.setTag(holder);
            } else {

                holder = (ListContent) v.getTag();
            }

            holder.name.setTypeface(myFont);
            holder.name.setText(list.get(position));

            return v;
        }

    }

    static class ListContent {

        TextView name;

    }
}
