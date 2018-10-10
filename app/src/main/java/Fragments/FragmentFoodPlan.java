package Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Adapters.AdapterDiet;
import FontComponents.MyButton;
import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.DateConvertor;
import Others.Globals;
import Others.MyProgressDialog;
import Others.Settings;
import Structures.FoodPlan;
import Structures.StructureFoodPlan;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 1/4/2018.
 */

public class FragmentFoodPlan extends Fragment {

    LinearLayout
            lnrDietNegativeBreakfast,
            lnrDietNegativeMorningSnack,
            lnrDietNegativeDinner,
            lnrDietNegativeEveningSnack,
            lnrDietNegativeLunch,
            lntDietNegativeBeforeSleep;

    GraphView graphView;

    int globalPlanCounter;

    //Section 1
    private RecyclerView recyclerViewDietBreakfast;
    private ArrayList<String> dietList = new ArrayList<String>();
    private AdapterDiet adapter;
    private ImageView imgDietBreakFastPositive, imgDietBreakFastMinus;
    private LinearLayout lnrDietBreakfastExpandable, lnrDietPositiveParent;
    private ExpandableLayout expandableLayout;

    //Section 2
    private RecyclerView recyclerViewDietMorningSnack;
    private ArrayList<String> dietMorningSnackList = new ArrayList<String>();
    private ExpandableLayout expandableMorningSnackLayout;
    private ImageView imgDietMorningSnackPositive, imgDietMorningSnackMinus;
    private LinearLayout lnrDietMorningSnackExpandable, lnrDietMOrningSnackPositiveParent;

    //Section 3
    private RecyclerView recyclerViewDietDinner;
    private ArrayList<String> dietDinnerList = new ArrayList<String>();
    private ExpandableLayout expandableDinnerLayout;
    private ImageView imgDietDinnerPositive, imgDietDinnerMinus;
    private LinearLayout lnrDietDinnerExpandable, lnrDietDinnerPositiveParent;

    //Section 4
    private RecyclerView recyclerViewDietEveningSnack;
    private ArrayList<String> dietEveningSnackList = new ArrayList<String>();
    private ExpandableLayout expandableEveningSnackLayout;
    private ImageView imgDietEveningSnackPositive, imgDietEveningSnackMinus;
    private LinearLayout lnrDietEveningSnackExpandable, lnrDietEveningSnackPositiveParent;

    //Section 5
    private RecyclerView recyclerViewDietLunch;
    private ArrayList<String> dietLunchList = new ArrayList<String>();
    private ExpandableLayout expandableLunchLayout;
    private ImageView imgDietLunchPositive, imgDietLunchMinus;
    private LinearLayout lnrDietLunchExpandable, lnrDietLunchPositiveParent;

    //Section 6
    private RecyclerView recyclerViewDietBeforeSleep;
    private ArrayList<String> dietBeforeSleepList = new ArrayList<String>();
    private ExpandableLayout expandableBeforeSleepLayout;
    private ImageView imgDietBeforeSleepPositive, imgDietBeforeSleepMinus;
    private LinearLayout lnrDietBeforeSleepExpandable, lnrDietBeforeSleepPositiveParent;

    private ImageView imgLeftArrowCalnedar, imgRightArrowCalendar;
    private TextViewFont txtWeekText;

    private MyButton btnHowToCook, btnAdvisorRecommend;
    private Calendar cal;

    //private ImageView imgDotSaturday, imgDotSunday, imgDotMonday, imgDotTuesday, imgDotWednesday, imgDotThursday, imgDotFriday;
    //private TextViewFont txtSaturday, txtSunday, txtMonday, txtTuesday, txtWednesday, txtThursday, txtFriday;

    private ImageView imgDaysDot[] = new ImageView[7];
    private TextView txtDaysText[] = new TextView[7];
    private TextView txtDaysText2[] = new TextView[7];
    private TextView txtDaysNameText[] = new TextView[7];
    private View parentView;
    StructureFoodPlan foodPlan = new StructureFoodPlan();
    public String globalStartDate, globalEndDate;


    final List<String> lstStartDates = new ArrayList<>();
    final List<String> lstEndDates = new ArrayList<>();
    final List<String> globalList = new ArrayList<>();

    FacadeService facadeService = new FacadeService();

    private static FragmentFoodPlan instance;
    public FragmentFoodPlan() {
        // Required empty public constructor
    }

    public static FragmentFoodPlan getInstance()
    {
        if (instance == null)
            instance = new FragmentFoodPlan();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_food_plan, container, false);

        //Calendar Header
        imgLeftArrowCalnedar = parentView.findViewById(R.id.img_food_plan_arrow_left_calendar);
        imgRightArrowCalendar = parentView.findViewById(R.id.img_food_plan_arrow_right_calendar);
        txtWeekText = parentView.findViewById(R.id.txt_food_plan_week_counter);
        btnHowToCook = parentView.findViewById(R.id.btn_how_to_cook);
        btnAdvisorRecommend = parentView.findViewById(R.id.btn_advisor_recommended);

        //Calendar Body
        imgDaysDot[0] = parentView.findViewById(R.id.img_food_plan_saturday_dot);
        imgDaysDot[1] = parentView.findViewById(R.id.img_food_plan_sunday_dot);
        imgDaysDot[2] = parentView.findViewById(R.id.img_food_plan_monday_dot);
        imgDaysDot[3] = parentView.findViewById(R.id.img_food_plan_tuesday_dot);
        imgDaysDot[4] = parentView.findViewById(R.id.img_food_plan_wednesday_dot);
        imgDaysDot[5] = parentView.findViewById(R.id.img_food_plan_thursday_dot);
        imgDaysDot[6] = parentView.findViewById(R.id.img_food_plan_friday_dot);

        txtDaysText[0] = parentView.findViewById(R.id.txt_food_plan_saturday);
        txtDaysText[1] = parentView.findViewById(R.id.txt_food_plan_sunday);
        txtDaysText[2] = parentView.findViewById(R.id.txt_food_plan_monday);
        txtDaysText[3] = parentView.findViewById(R.id.txt_food_plan_tuesday);
        txtDaysText[4] = parentView.findViewById(R.id.txt_food_plan_wednesday);
        txtDaysText[5] = parentView.findViewById(R.id.txt_food_plan_thursday);
        txtDaysText[6] = parentView.findViewById(R.id.txt_food_plan_friday);

        txtDaysText2[0] = parentView.findViewById(R.id.txt_food_plan_saturday1);
        txtDaysText2[1] = parentView.findViewById(R.id.txt_food_plan_sunday1);
        txtDaysText2[2] = parentView.findViewById(R.id.txt_food_plan_monday1);
        txtDaysText2[3] = parentView.findViewById(R.id.txt_food_plan_tuesday1);
        txtDaysText2[4] = parentView.findViewById(R.id.txt_food_plan_wednesday1);
        txtDaysText2[5] = parentView.findViewById(R.id.txt_food_plan_thursday1);
        txtDaysText2[6] = parentView.findViewById(R.id.txt_food_plan_friday1);


        txtDaysNameText[0] = parentView.findViewById(R.id.txt_food_plan_day_name_saturday);
        txtDaysNameText[1] = parentView.findViewById(R.id.txt_food_plan_day_name_sunday);
        txtDaysNameText[2] = parentView.findViewById(R.id.txt_food_plan_day_name_monday);
        txtDaysNameText[3] = parentView.findViewById(R.id.txt_food_plan_day_name_tuesday);
        txtDaysNameText[4] = parentView.findViewById(R.id.txt_food_plan_day_name_wednesday);
        txtDaysNameText[5] = parentView.findViewById(R.id.txt_food_plan_day_name_thursday);
        txtDaysNameText[6] = parentView.findViewById(R.id.txt_food_plan_day_name_friday);

        imgLeftArrowCalnedar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globalPlanCounter > 0) {
                    globalPlanCounter--;
                    String[] strPlans;
                    CleanAllList();
                    strPlans = globalList.get(globalPlanCounter).split("-");
                    dietList.add(strPlans[0]);
                    dietMorningSnackList.add(strPlans[1]);
                    dietLunchList.add(strPlans[2]);
                    dietEveningSnackList.add(strPlans[3]);
                    dietDinnerList.add(strPlans[4]);
                    dietBeforeSleepList.add(strPlans[5]);

                    getCountOfDays(lstStartDates.get(globalPlanCounter), lstEndDates.get(globalPlanCounter));

                    InitCalendar(GetCurrentDayNumber() - 1);

                    //imgRightArrowCalendar.setImageResource(R.drawable.ic_arrow_forward_white);
                    //imgLeftArrowCalnedar.setImageResource(R.drawable.ic_arrow_back_white);
//                    Toast.makeText(
//                            getContext(),
//                            Persian_Number_To_String.GET_Number_To_PersianString("" + globalPlanCounter),
//                            Toast.LENGTH_LONG).show();

                }
            }
        });

        imgRightArrowCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(globalPlanCounter < foodPlan.getTotalCount() - 1) {
                    globalPlanCounter++;
                    String[] strPlans;
                    CleanAllList();
                    strPlans = globalList.get(globalPlanCounter).split("-");
                    dietList.add(strPlans[0]);
                    dietMorningSnackList.add(strPlans[1]);
                    dietLunchList.add(strPlans[2]);
                    dietEveningSnackList.add(strPlans[3]);
                    dietDinnerList.add(strPlans[4]);
                    dietBeforeSleepList.add(strPlans[5]);
                    getCountOfDays(lstStartDates.get(globalPlanCounter), lstEndDates.get(globalPlanCounter));

                    InitCalendar(GetCurrentDayNumber() - 1);
                    //imgRightArrowCalendar.setImageResource(R.drawable.ic_arrow_back_white);
                    //imgLeftArrowCalnedar.setImageResource(R.drawable.ic_arrow_forward_white);

//                    Toast.makeText(
//                            getContext(),
//                            Persian_Number_To_String.GET_Number_To_PersianString("" + globalPlanCounter),
//                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //Section 1
        expandableLayout = parentView.findViewById(R.id.expandable_layout);
        recyclerViewDietBreakfast = parentView.findViewById(R.id.recycle_view_diet_breakfast);
        imgDietBreakFastPositive = parentView.findViewById(R.id.img_diet_breakfast_positive);
        imgDietBreakFastMinus = parentView.findViewById(R.id.img_diet_breakfast_minus);
        lnrDietBreakfastExpandable = parentView.findViewById(R.id.lnr_diet_breakfast_expandable);
        lnrDietPositiveParent = parentView.findViewById(R.id.lnr_diet_positive_parent);
        lnrDietNegativeBreakfast = parentView.findViewById(R.id.lnr_expand_layout_breakfast);
        lnrDietNegativeBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietBreakFastMinus.callOnClick();
            }
        });

        lnrDietPositiveParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietBreakFastPositive.callOnClick();
            }
        });


        //Section 2
        recyclerViewDietMorningSnack = parentView.findViewById(R.id.recycle_view_diet_morning_snack);
        expandableMorningSnackLayout = parentView.findViewById(R.id.expandable_morning_snack_layout);
        imgDietMorningSnackPositive = parentView.findViewById(R.id.img_diet_morning_snack_positive);
        imgDietMorningSnackMinus = parentView.findViewById(R.id.img_diet_morning_snack_minus);
        lnrDietMorningSnackExpandable = parentView.findViewById(R.id.lnr_diet_morning_snack_expandable);
        lnrDietMOrningSnackPositiveParent = parentView.findViewById(R.id.lnr_diet_morning_snack_positive_parent);
        lnrDietNegativeMorningSnack = parentView.findViewById(R.id.lnr_expand_layout_morning_snack);
        lnrDietNegativeMorningSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietMorningSnackMinus.callOnClick();
            }
        });

        lnrDietMOrningSnackPositiveParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietMorningSnackPositive.callOnClick();
            }
        });

        //Section 3
        recyclerViewDietDinner = parentView.findViewById(R.id.recycle_view_diet_dinner);
        expandableDinnerLayout = parentView.findViewById(R.id.expandable_dinner_layout);
        imgDietDinnerPositive = parentView.findViewById(R.id.img_diet_dinner_positive);
        imgDietDinnerMinus = parentView.findViewById(R.id.img_diet_dinner_minus);
        lnrDietDinnerExpandable = parentView.findViewById(R.id.lnr_diet_dinner_expandable);
        lnrDietDinnerPositiveParent = parentView.findViewById(R.id.lnr_diet_dinner_positive_parent);
        lnrDietNegativeDinner = parentView.findViewById(R.id.lnr_expand_layout_dinner);
        lnrDietNegativeDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietDinnerMinus.callOnClick();
            }
        });

        lnrDietDinnerPositiveParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietDinnerPositive.callOnClick();
            }
        });

        //Section 4
        recyclerViewDietEveningSnack = parentView.findViewById(R.id.recycle_view_diet_evening_snack);
        expandableEveningSnackLayout = parentView.findViewById(R.id.expandable_evening_snack_layout);
        imgDietEveningSnackPositive = parentView.findViewById(R.id.img_diet_evening_snack_positive);
        imgDietEveningSnackMinus = parentView.findViewById(R.id.img_diet_evening_snack_minus);
        lnrDietEveningSnackExpandable = parentView.findViewById(R.id.lnr_diet_evening_snack_expandable);
        lnrDietEveningSnackPositiveParent = parentView.findViewById(R.id.lnr_diet_evening_snack_positive_parent);
        lnrDietNegativeEveningSnack = parentView.findViewById(R.id.lnr_expand_layout_evening_snack);
        lnrDietNegativeEveningSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietEveningSnackMinus.callOnClick();
            }
        });

        lnrDietEveningSnackPositiveParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietEveningSnackPositive.callOnClick();
            }
        });

        //Section 5
        recyclerViewDietLunch = parentView.findViewById(R.id.recycle_view_diet_lunch);
        expandableLunchLayout = parentView.findViewById(R.id.expandable_lunch_layout);
        imgDietLunchPositive = parentView.findViewById(R.id.img_diet_lunch_positive);
        imgDietLunchMinus = parentView.findViewById(R.id.img_diet_lunch_minus);
        lnrDietLunchExpandable = parentView.findViewById(R.id.lnr_diet_lunch_expandable);
        lnrDietLunchPositiveParent = parentView.findViewById(R.id.lnr_diet_lunch_positive_parent);
        lnrDietNegativeLunch = parentView.findViewById(R.id.lnr_expand_layout_lunch);
        lnrDietNegativeLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietLunchMinus.callOnClick();
            }
        });

        lnrDietLunchPositiveParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietLunchPositive.callOnClick();
            }
        });


        //Section 6
        recyclerViewDietBeforeSleep = parentView.findViewById(R.id.recycle_view_diet_before_sleep);
        expandableBeforeSleepLayout = parentView.findViewById(R.id.expandable_before_sleep_layout);
        imgDietBeforeSleepPositive = parentView.findViewById(R.id.img_diet_before_sleep_positive);
        imgDietBeforeSleepMinus = parentView.findViewById(R.id.img_diet_before_sleep_minus);
        lnrDietBeforeSleepExpandable = parentView.findViewById(R.id.lnr_diet_before_sleep_expandable);
        lnrDietBeforeSleepPositiveParent = parentView.findViewById(R.id.lnr_diet_before_sleep_positive_parent);
        lntDietNegativeBeforeSleep = parentView.findViewById(R.id.lnr_expand_layout_before_sleep);
        lntDietNegativeBeforeSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietBeforeSleepMinus.callOnClick();
            }
        });

        lnrDietBeforeSleepPositiveParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDietBeforeSleepPositive.callOnClick();
            }
        });

        //Init
        //InitDietAllList();
        InitCalendar(GetCurrentDayNumber() - 1);
        ActivityMain.lnrBotomMenu.setVisibility(View.VISIBLE);
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        globalStartDate = "";
        globalEndDate = "";
        globalPlanCounter = 0;

        expandableLayout.setVisibility(View.GONE);
        lnrDietPositiveParent.setVisibility(View.VISIBLE);

        expandableMorningSnackLayout.setVisibility(View.GONE);
        lnrDietMOrningSnackPositiveParent.setVisibility(View.VISIBLE);
        expandableDinnerLayout.setVisibility(View.GONE);
        lnrDietDinnerPositiveParent.setVisibility(View.VISIBLE);
        expandableEveningSnackLayout.setVisibility(View.GONE);
        lnrDietEveningSnackPositiveParent.setVisibility(View.VISIBLE);
        expandableLunchLayout.setVisibility(View.GONE);
        lnrDietLunchPositiveParent.setVisibility(View.VISIBLE);
        expandableBeforeSleepLayout.setVisibility(View.GONE);
        lnrDietBeforeSleepPositiveParent  .setVisibility(View.VISIBLE);




//        lnrDietBeforeSleepPositiveParent.setVisibility(View.GONE);
//        lnrDietLunchPositiveParent;
//        lnrDietEveningSnackPositiveParent;
//        lnrDietDinnerPositiveParent;
//        lnrDietMOrningSnackPositiveParent;
//        lnrDietPositiveParent.setVisibility(View.GONE);

        //collapseAll();
//        if(Settings.getInstance().getFoodPlan() != null)
//        {
//            InitDietAllList(Settings.getInstance().getFoodPlan(), "local");
//        }
//        else
//        {
//



        //Section 6
        adapter = new AdapterDiet(dietBeforeSleepList, getActivity());
        recyclerViewDietBeforeSleep.setAdapter(adapter);
        recyclerViewDietBeforeSleep.setLayoutManager(new LinearLayoutManager(getActivity()));
        BeforeSleep();


        //Section 5
        adapter = new AdapterDiet(dietLunchList, getActivity());
        recyclerViewDietLunch.setAdapter(adapter);
        recyclerViewDietLunch.setLayoutManager(new LinearLayoutManager(getActivity()));
        Lunch();


        //Section 4
        adapter = new AdapterDiet(dietEveningSnackList, getActivity());
        recyclerViewDietEveningSnack.setAdapter(adapter);
        recyclerViewDietEveningSnack.setLayoutManager(new LinearLayoutManager(getActivity()));
        EveningSnack();


        //Section 3
        adapter = new AdapterDiet(dietDinnerList, getActivity());
        recyclerViewDietDinner.setAdapter(adapter);
        recyclerViewDietDinner.setLayoutManager(new LinearLayoutManager(getActivity()));
        Dinner();


        //Section 2
        adapter = new AdapterDiet(dietMorningSnackList, getActivity());
        recyclerViewDietMorningSnack.setAdapter(adapter);
        recyclerViewDietMorningSnack.setLayoutManager(new LinearLayoutManager(getActivity()));
        MorningSnack();


        //Section 1
        adapter = new AdapterDiet(dietList, getActivity());
        recyclerViewDietBreakfast.setAdapter(adapter);
        recyclerViewDietBreakfast.setLayoutManager(new LinearLayoutManager(getActivity()));
        Breakfast();



        btnHowToCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityMain.fragmentManager.beginTransaction().replace(
                ActivityMain.globalFrame.getId(), FragmentHowToCook.getInstance()).addToBackStack("PROFILE_MOSHAVER_TAG").commit();
            }
        });

        btnAdvisorRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityMain.fragmentManager.beginTransaction().replace(
                ActivityMain.globalFrame.getId(), FragmentAdvisorRecommention.getInstance()).addToBackStack("ADVISOR_RECOMMEND_TAG").commit();
            }
        });


        TextCalendar();

        getFoodPlan(parentView);

        return parentView;
    }

    String getWeekName(int iWeekNumber)
    {
        String strWeekName = "";
        int num = globalList.size() - globalList.size();



        return strWeekName;
    }

    public String getCountOfDays(String createdDateString, String expireDateString) {
        List<Calendar> datelist = new ArrayList<Calendar>();


        DateConvertor dc = new DateConvertor();
        String strStartConverted = dc.ChangeFormatYYYT(createdDateString);
        String[] startDate = strStartConverted.split("-");
        Calendar cStart = Calendar.getInstance();
        cStart.set(Integer.parseInt(startDate[0]), Integer.parseInt(startDate[2]), Integer.parseInt(startDate[2]));


        String strEndConverted = dc.ChangeFormatYYYT(expireDateString);
        String[] endDate = strEndConverted.split("-");
        Calendar cEnd = Calendar.getInstance();
        cEnd.set(Integer.parseInt(endDate[0]), Integer.parseInt(endDate[2]), Integer.parseInt(endDate[2]));

        List<Long> betweenDate = new ArrayList<Long>();
        betweenDate = getDates(strStartConverted, strEndConverted);


        daysInThisWeek(0, 7, betweenDate);


        return "";
    }
    private static List<Long> getDates(String dateString1, String dateString2)
    {
        ArrayList<Long> dates = new ArrayList<Long>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1 .parse(dateString1);
            date2 = df1 .parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2))
        {
            dates.add(cal1.getTimeInMillis());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }
    void TextCalendar()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, 2014);
        calendar.set(2014, 1, 1);
        int ndays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
        for (int i = 1; i <= ndays; i++) {
            int week = calendar.get(Calendar.WEEK_OF_YEAR);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            List<Integer> dayList = map.get(week);
            if (dayList == null) {
                dayList = new ArrayList<Integer>();
                map.put(week, dayList);
            }
            dayList.add(day);
            calendar.add(Calendar.DATE, 1);
        }
        //Check the result:
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            System.out.println("Week: " + entry.getKey());
            for (int day : entry.getValue()) {
                System.out.println("Day: " + day);
            }
        }
    }
    void Breakfast()
    {
        expandableLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if(state == ExpandableLayout.State.COLLAPSED)
                {

                }

                if(state == ExpandableLayout.State.COLLAPSED)
                {
                    lnrDietPositiveParent.setVisibility(View.VISIBLE);
                }
            }
        });
        imgDietBreakFastPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout.expand();
                expandableLayout.setVisibility(View.VISIBLE);
                lnrDietPositiveParent.setVisibility(View.GONE);
            }
        });

        imgDietBreakFastMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout.collapse();

            }
        });
    }
    void MorningSnack()
    {
        expandableMorningSnackLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if(state == ExpandableLayout.State.COLLAPSED)
                {

                }

                if(state == ExpandableLayout.State.COLLAPSED)
                {
                    lnrDietMOrningSnackPositiveParent.setVisibility(View.VISIBLE);
                }
            }
        });

        imgDietMorningSnackPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableMorningSnackLayout.expand();
                lnrDietMOrningSnackPositiveParent.setVisibility(View.GONE);
                expandableMorningSnackLayout.setVisibility(View.VISIBLE);
            }
        });

        imgDietMorningSnackMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableMorningSnackLayout.collapse();
            }
        });
    }
    void Lunch()
    {
        expandableLunchLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if(state == ExpandableLayout.State.COLLAPSED)
                {

                }

                if(state == ExpandableLayout.State.COLLAPSED)
                {
                    lnrDietLunchPositiveParent.setVisibility(View.VISIBLE);
                }
            }
        });

        imgDietLunchPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLunchLayout.expand();
                lnrDietLunchPositiveParent.setVisibility(View.GONE);
                expandableLunchLayout.setVisibility(View.VISIBLE);
            }
        });

        imgDietLunchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLunchLayout.collapse();
            }
        });
    }
    void EveningSnack()
    {
        expandableEveningSnackLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if(state == ExpandableLayout.State.COLLAPSED)
                {

                }

                if(state == ExpandableLayout.State.COLLAPSED)
                {
                    lnrDietEveningSnackPositiveParent.setVisibility(View.VISIBLE);
                }
            }
        });

        imgDietEveningSnackPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableEveningSnackLayout.expand();
                lnrDietEveningSnackPositiveParent.setVisibility(View.GONE);
                expandableEveningSnackLayout.setVisibility(View.VISIBLE);
            }
        });

        imgDietEveningSnackMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableEveningSnackLayout.collapse();
            }
        });

    }
    void Dinner()
    {
        expandableDinnerLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if(state == ExpandableLayout.State.COLLAPSED)
                {

                }

                if(state == ExpandableLayout.State.COLLAPSED)
                {
                    lnrDietDinnerPositiveParent.setVisibility(View.VISIBLE);
                }
            }
        });
        imgDietDinnerPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableDinnerLayout.expand();
                lnrDietDinnerPositiveParent.setVisibility(View.GONE);
                expandableDinnerLayout.setVisibility(View.VISIBLE);
            }
        });

        imgDietDinnerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableDinnerLayout.collapse();

            }
        });
    }
    void BeforeSleep()
    {
        expandableBeforeSleepLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if(state == ExpandableLayout.State.COLLAPSED)
                {

                }

                if(state == ExpandableLayout.State.COLLAPSED)
                {
                    lnrDietBeforeSleepPositiveParent.setVisibility(View.VISIBLE);
                }
            }
        });
        imgDietBeforeSleepPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableBeforeSleepLayout.expand();
                lnrDietBeforeSleepPositiveParent.setVisibility(View.GONE);
                expandableBeforeSleepLayout.setVisibility(View.VISIBLE);
            }
        });

        imgDietBeforeSleepMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableBeforeSleepLayout.collapse();

            }
        });
    }
    void getFoodPlan(View view)
    {

        final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال دریافت اطلاعات برنامه غذایی . . .");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

        globalEndDate = "2018-03-17T00:00:00";


        facadeService.GetDiet(new DataCallbbackOutput() {
            @Override
            public void onSuccess(String result) {
                foodPlan = new Gson().fromJson(result, StructureFoodPlan.class);
                if(foodPlan != null)
                {
                    CleanAllList();
                    int rr = 0;
                    for(FoodPlan plan: foodPlan.getItems())
                    {
                        try {
                            dietList.add(new JSONObject(plan.getDietData()).get("breakfast").toString());
                            dietMorningSnackList.add(new JSONObject(plan.getDietData()).get("morningSnack").toString());
                            dietLunchList.add(new JSONObject(plan.getDietData()).get("lunch").toString());
                            dietEveningSnackList.add(new JSONObject(plan.getDietData()).get("eveningSnack").toString());
                            dietDinnerList.add(new JSONObject(plan.getDietData()).get("dinner").toString());
                            dietBeforeSleepList.add(new JSONObject(plan.getDietData()).get("beforSleep").toString());

                            String str =
                                    (dietList.get(rr) + "-" +
                                    dietMorningSnackList.get(rr) + "-" +
                                    dietLunchList.get(rr) + "-" +
                                    dietEveningSnackList.get(rr) + "-" +
                                    dietDinnerList.get(rr) + "-" +
                                    dietBeforeSleepList.get(rr));

                            globalList.add(str);

                            lstStartDates.add(plan.getValidFromTime());
                            lstEndDates.add(plan.getValidToTime());

                            rr++;
                            //lstStartDates =
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }



                    String[] strPlans;
                    CleanAllList();
                    strPlans = globalList.get(globalPlanCounter).split("-");
                    dietList.add(strPlans[0]);
                    dietMorningSnackList.add(strPlans[1]);
                    dietLunchList.add(strPlans[2]);
                    dietEveningSnackList.add(strPlans[3]);
                    dietDinnerList.add(strPlans[4]);
                    dietBeforeSleepList.add(strPlans[5]);

                    getCountOfDays(lstStartDates.get(globalPlanCounter), lstEndDates.get(globalPlanCounter));

                    //daysInThisWeek(0, 7);


















                        //InitDietAllList(plan, "online");

                    Settings.getInstance().setFoodPlan(foodPlan);
                    Settings.getInstance().saveAll();


                }
                else
                {
                    Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">ارتباط با سرور با مشکل مواجه شد لطفا دوباره تلاش کنید.</font>"), Snackbar.LENGTH_LONG);
                }

                cdd.dismiss();
            }
        }, getActivity(), parentView, globalStartDate, globalEndDate);
    }
    void CleanAllList()
    {
        dietList.clear();
        dietMorningSnackList.clear();
        dietLunchList.clear();
        dietEveningSnackList.clear();
        dietDinnerList.clear();
        dietBeforeSleepList.clear();
    }
    void daysInThisWeek(int start, int end, List<Long> milisecound)
    {
        DateConvertor dc = new DateConvertor();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

        for (int i = start; i < end; i++) {
            Log.i("dateTag:", dc.miladiToShamsi(dc.getDateFromTimeStamp(milisecound.get(i))));
            String[] str = dc.miladiToShamsi(dc.getDateFromTimeStamp(milisecound.get(i))).split("/");

            if(i == 0)
            {
                globalStartDate = dc.milisecoundToYYYMMMDDD(cal.getTimeInMillis());
            }

            txtDaysText[i].setText(str[1]);
            if(str[1].equals("12") && Integer.parseInt(str[2]) <= 29)
                txtDaysText2[i].setText("/" + String.valueOf(Integer.parseInt(str[2]) - 1));
            else
                txtDaysText2[i].setText("/" + str[2]);
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }
    }
    int GetCurrentDayNumber()
    {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int currentDayNumber = 0;

        switch (day) {
            case Calendar.SATURDAY:
                // Current day is Sunday
                currentDayNumber = 1;
                break;
            case Calendar.SUNDAY:
                // Current day is Monday
                currentDayNumber = 2;
                break;
            case Calendar.MONDAY:
                // etc.
                currentDayNumber = 3;
                break;

            case Calendar.TUESDAY:
                // etc.
                currentDayNumber = 4;
                break;

            case Calendar.WEDNESDAY:
                // etc.
                currentDayNumber = 5;
                break;

            case Calendar.THURSDAY:
                // etc.
                currentDayNumber =6;
                break;

            case Calendar.FRIDAY:
                // etc.
                currentDayNumber = 7;
                break;
        }

        return currentDayNumber;
    }
    private void collapseAll() {
        expandableBeforeSleepLayout.collapse();
        expandableLunchLayout.collapse();
        expandableEveningSnackLayout.collapse();
        expandableDinnerLayout.collapse();
        expandableMorningSnackLayout.collapse();
        expandableLayout.collapse();
    }
    void GetCurrentWeakDays()
    {
        cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd.MM.yyyy");

        for (int i = 0; i < 7; i++) {
            Log.i("dateTag", sdf.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }
    }
    private void InitCalendar(int iDayNumber) // Day number must be between 1 - 7
    {

        //TODO : Get date from server and set date text in calendar
        //

        for (int i = 0; i < imgDaysDot.length; i++) {

            if (globalPlanCounter != 0) {
                txtDaysNameText[i].setTextColor(getResources().getColor(R.color.gray_dark));
                txtDaysText[i].setTextColor(getResources().getColor(R.color.gray_dark));
                txtDaysText2[i].setTextColor(getResources().getColor(R.color.gray_dark));
                imgDaysDot[i].setImageResource(R.drawable.ic_food_plan_calendar_green_tick);
            }
            else {

                if (i < iDayNumber)
                    imgDaysDot[i].setImageResource(R.drawable.ic_food_plan_calendar_green_tick);

                if (i == iDayNumber) {
                    if (iDayNumber != 0) {
                        // i - 1 = green tick
                        imgDaysDot[i - 1].setImageResource(R.drawable.ic_food_plan_calendar_green_tick);
                        //txtDaysNameText[i-1].setTextColor(getResources().getColor(R.color.gray_dark));
                        //txtDaysText[i-1].setTextColor(getResources().getColor(R.color.gray_dark));
                        //txtDaysText2[i-1].setTextColor(getResources().getColor(R.color.gray_dark));

                        //i day name and day number and day dot = yellow
                        imgDaysDot[i].setImageResource(R.drawable.ic_food_plan_calendar_down_arrow);
                        txtDaysNameText[i].setTextColor(getResources().getColor(R.color.mycolor_yellow_text));
                        txtDaysText[i].setTextColor(getResources().getColor(R.color.mycolor_yellow_text));
                        txtDaysText2[i].setTextColor(getResources().getColor(R.color.mycolor_yellow_text));
                    } else {
                        imgDaysDot[i].setImageResource(R.drawable.ic_food_plan_calendar_down_arrow);
                        txtDaysNameText[i].setTextColor(getResources().getColor(R.color.mycolor_yellow_text));
                        txtDaysText[i].setTextColor(getResources().getColor(R.color.mycolor_yellow_text));
                        txtDaysText2[i].setTextColor(getResources().getColor(R.color.mycolor_yellow_text));
                    }
                } else if (i < iDayNumber) {
                    //imgDaysDot[i].setImageResource(R.drawable.ic_food_plan_calendar_gray_dot);
                    txtDaysNameText[i].setTextColor(getResources().getColor(R.color.gray_dark));
                    txtDaysText[i].setTextColor(getResources().getColor(R.color.gray_dark));
                }
            }
        }
    }

    private void InitDietAllList(FoodPlan myFoodPlan, String strType) {

//        List<FoodPlan> myFood = new ArrayList<>();
//        myFood = myFoodPlan;
//        if (myFoodPlan.get(0) != null)
//        {
//            //Section 1
//            dietList.add(myFoodPlan.get(0).getDietData());
//        }
//
//        if (myFoodPlan.get(1) != null)
//        {
//            //Section 2
//            dietList.add(myFoodPlan.get(1).getDietData());
//        }
//
//        if (myFoodPlan.get(2) != null)
//        {
//            //Section 3
//            dietList.add(myFoodPlan.get(2).getDietData());
//        }
//
//        if (myFoodPlan.get(3) != null)
//        {
//            //Section 4
//            dietList.add(myFoodPlan.get(3).getDietData());
//        }
//
//        if (myFoodPlan.get(4) != null)
//        {
//            //Section 5
//            dietList.add(myFoodPlan.get(4).getDietData());
//        }
//
//        if (myFoodPlan.get(5) != null)
//        {
//            //Section 6
//            dietList.add(myFoodPlan.get(5).getDietData());
//        }

//        for (StructureFoodPlan foodPlan : myFoodPlan)
//        {
//            if (foodPlan.getMeal().equals("صبحانه"))
//            {
//                //Section 1
//                dietList.addAll(foodPlan.getItems());
//            }
//
//            if (foodPlan.getMeal().equals("میان وعده صبح"))
//            {
//                //Section 2
//                dietMorningSnackList.addAll(foodPlan.getItems());
//            }
//
//            if (foodPlan.getMeal().equals("ناهار"))
//            {
//                //Section 3
//                dietDinnerList.addAll(foodPlan.getItems());
//            }
//
//            if (foodPlan.getMeal().equals("میان وعده عصر"))
//            {
//                //Section 4
//                dietEveningSnackList.addAll(foodPlan.getItems());
//
//            }
//
//            if (foodPlan.getMeal().equals("شام"))
//            {
//                //Section 5
//                dietLunchList.addAll(foodPlan.getItems());
//            }
//
//            if (foodPlan.getMeal().equals("قبل از خواب"))
//            {
//                //Section 6
//                dietBeforeSleepList.addAll(foodPlan.getItems());
//            }
//        }
    }
    private void setLayoutSize(int size)
    {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lnrDietBreakfastExpandable.getLayoutParams();
        params.height = size;
        lnrDietBreakfastExpandable.setLayoutParams(params);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        expandableLayout.setVisibility(View.GONE);
        lnrDietPositiveParent.setVisibility(View.VISIBLE);
        expandableBeforeSleepLayout.collapse();
        expandableDinnerLayout.collapse();
        expandableEveningSnackLayout.collapse();
        expandableLayout.collapse();
        expandableLunchLayout.collapse();
        expandableMorningSnackLayout.collapse();
    }
    @Override
    public void onDetach() {
        expandableLayout.setVisibility(View.GONE);
        lnrDietPositiveParent.setVisibility(View.VISIBLE);
        expandableBeforeSleepLayout.collapse();
        expandableDinnerLayout.collapse();
        expandableEveningSnackLayout.collapse();
        expandableLayout.collapse();
        expandableLunchLayout.collapse();
        expandableMorningSnackLayout.collapse();
        super.onDetach();
    }
}
