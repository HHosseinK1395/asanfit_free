package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import Adapters.AdapterDiet;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 1/4/2018.
 */

public class FragmentDiet extends Fragment {

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


    private static FragmentDiet instance;
    public FragmentDiet() {
        // Required empty public constructor
    }

    public static FragmentDiet getInstance()
    {
        if (instance == null)
            instance = new FragmentDiet();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        //Section 4
        expandableLayout = view.findViewById(R.id.expandable_layout);
        recyclerViewDietBreakfast = view.findViewById(R.id.recycle_view_diet_breakfast);
        imgDietBreakFastPositive = view.findViewById(R.id.img_diet_breakfast_positive);
        imgDietBreakFastMinus = view.findViewById(R.id.img_diet_breakfast_minus);
        lnrDietBreakfastExpandable = view.findViewById(R.id.lnr_diet_breakfast_expandable);
        lnrDietPositiveParent = view.findViewById(R.id.lnr_diet_positive_parent);

        //Section 2
        recyclerViewDietMorningSnack = view.findViewById(R.id.recycle_view_diet_morning_snack);
        expandableMorningSnackLayout = view.findViewById(R.id.expandable_morning_snack_layout);
        imgDietMorningSnackPositive = view.findViewById(R.id.img_diet_morning_snack_positive);
        imgDietMorningSnackMinus = view.findViewById(R.id.img_diet_morning_snack_minus);
        lnrDietMorningSnackExpandable = view.findViewById(R.id.lnr_diet_morning_snack_expandable);
        lnrDietMOrningSnackPositiveParent = view.findViewById(R.id.lnr_diet_morning_snack_positive_parent);

        //Section 3
        recyclerViewDietDinner = view.findViewById(R.id.recycle_view_diet_dinner);
        expandableDinnerLayout = view.findViewById(R.id.expandable_dinner_layout);
        imgDietDinnerPositive = view.findViewById(R.id.img_diet_dinner_positive);
        imgDietDinnerMinus = view.findViewById(R.id.img_diet_dinner_minus);
        lnrDietDinnerExpandable = view.findViewById(R.id.lnr_diet_dinner_expandable);
        lnrDietDinnerPositiveParent = view.findViewById(R.id.lnr_diet_dinner_positive_parent);

        //Section 4
        recyclerViewDietEveningSnack = view.findViewById(R.id.recycle_view_diet_evening_snack);
        expandableEveningSnackLayout = view.findViewById(R.id.expandable_evening_snack_layout);
        imgDietEveningSnackPositive = view.findViewById(R.id.img_diet_evening_snack_positive);
        imgDietEveningSnackMinus = view.findViewById(R.id.img_diet_evening_snack_minus);
        lnrDietEveningSnackExpandable = view.findViewById(R.id.lnr_diet_evening_snack_expandable);
        lnrDietEveningSnackPositiveParent = view.findViewById(R.id.lnr_diet_evening_snack_positive_parent);

        //Section 5
        recyclerViewDietLunch = view.findViewById(R.id.recycle_view_diet_lunch);
        expandableLunchLayout = view.findViewById(R.id.expandable_lunch_layout);
        imgDietLunchPositive = view.findViewById(R.id.img_diet_lunch_positive);
        imgDietLunchMinus = view.findViewById(R.id.img_diet_lunch_minus);
        lnrDietLunchExpandable = view.findViewById(R.id.lnr_diet_lunch_expandable);
        lnrDietLunchPositiveParent = view.findViewById(R.id.lnr_diet_lunch_positive_parent);

        //Section 6
        recyclerViewDietBeforeSleep = view.findViewById(R.id.recycle_view_diet_before_sleep);
        expandableBeforeSleepLayout = view.findViewById(R.id.expandable_before_sleep_layout);
        imgDietBeforeSleepPositive = view.findViewById(R.id.img_diet_before_sleep_positive);
        imgDietBeforeSleepMinus = view.findViewById(R.id.img_diet_before_sleep_minus);
        lnrDietBeforeSleepExpandable = view.findViewById(R.id.lnr_diet_before_sleep_expandable);
        lnrDietBeforeSleepPositiveParent = view.findViewById(R.id.lnr_diet_before_sleep_positive_parent);

        //Init
        InitDietAllList();

        //Section 6
        adapter = new AdapterDiet(dietBeforeSleepList, getActivity());
        recyclerViewDietBeforeSleep.setAdapter(adapter);
        recyclerViewDietBeforeSleep.setLayoutManager(new LinearLayoutManager(getActivity()));

        imgDietBeforeSleepPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableBeforeSleepLayout.expand();
                lnrDietBeforeSleepPositiveParent.setVisibility(View.GONE);
            }
        });

        imgDietBeforeSleepMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableBeforeSleepLayout.collapse();
                lnrDietBeforeSleepPositiveParent.setVisibility(View.VISIBLE);
            }
        });

        //Section 5
        adapter = new AdapterDiet(dietLunchList, getActivity());
        recyclerViewDietLunch.setAdapter(adapter);
        recyclerViewDietLunch.setLayoutManager(new LinearLayoutManager(getActivity()));

        imgDietLunchPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLunchLayout.expand();
                lnrDietLunchPositiveParent.setVisibility(View.GONE);
            }
        });

        imgDietLunchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLunchLayout.collapse();
                lnrDietLunchPositiveParent.setVisibility(View.VISIBLE);
            }
        });

        //Section 4
        adapter = new AdapterDiet(dietEveningSnackList, getActivity());
        recyclerViewDietEveningSnack.setAdapter(adapter);
        recyclerViewDietEveningSnack.setLayoutManager(new LinearLayoutManager(getActivity()));

        imgDietEveningSnackPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableEveningSnackLayout.expand();
                lnrDietEveningSnackPositiveParent.setVisibility(View.GONE);
            }
        });

        imgDietEveningSnackMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableEveningSnackLayout.collapse();
                lnrDietEveningSnackPositiveParent.setVisibility(View.VISIBLE);
            }
        });


        //Section 3
        adapter = new AdapterDiet(dietDinnerList, getActivity());
        recyclerViewDietDinner.setAdapter(adapter);
        recyclerViewDietDinner.setLayoutManager(new LinearLayoutManager(getActivity()));

        imgDietDinnerPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableDinnerLayout.expand();
                lnrDietDinnerPositiveParent.setVisibility(View.GONE);
            }
        });

        imgDietDinnerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableDinnerLayout.collapse();
                lnrDietDinnerPositiveParent.setVisibility(View.VISIBLE);
            }
        });

        //Section 2
        adapter = new AdapterDiet(dietMorningSnackList, getActivity());
        recyclerViewDietMorningSnack.setAdapter(adapter);
        recyclerViewDietMorningSnack.setLayoutManager(new LinearLayoutManager(getActivity()));

        imgDietMorningSnackPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableMorningSnackLayout.expand();
                lnrDietMOrningSnackPositiveParent.setVisibility(View.GONE);
            }
        });

        imgDietMorningSnackMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableMorningSnackLayout.collapse();
                lnrDietMOrningSnackPositiveParent.setVisibility(View.VISIBLE);
            }
        });


        //Section 1
        adapter = new AdapterDiet(dietList, getActivity());
        recyclerViewDietBreakfast.setAdapter(adapter);
        recyclerViewDietBreakfast.setLayoutManager(new LinearLayoutManager(getActivity()));


        imgDietBreakFastPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout.expand();
                lnrDietPositiveParent.setVisibility(View.GONE);
            }
        });

        imgDietBreakFastMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout.collapse();
                lnrDietPositiveParent.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    private void InitDietAllList() {
        //Section 1
        dietList.add(getResources().getString(R.string.fragment_diet_4));
        dietList.add(getResources().getString(R.string.fragment_diet_5));
        dietList.add(getResources().getString(R.string.fragment_diet_6));
        dietList.add(getResources().getString(R.string.fragment_diet_7));
        dietList.add(getResources().getString(R.string.fragment_diet_8));

        //Section 2
        dietMorningSnackList.add(getResources().getString(R.string.fragment_diet_4));
        dietMorningSnackList.add(getResources().getString(R.string.fragment_diet_5));
        dietMorningSnackList.add(getResources().getString(R.string.fragment_diet_6));
        dietMorningSnackList.add(getResources().getString(R.string.fragment_diet_7));
        dietMorningSnackList.add(getResources().getString(R.string.fragment_diet_8));

        //Section 3
        dietDinnerList.add(getResources().getString(R.string.fragment_diet_4));
        dietDinnerList.add(getResources().getString(R.string.fragment_diet_5));
        dietDinnerList.add(getResources().getString(R.string.fragment_diet_6));
        dietDinnerList.add(getResources().getString(R.string.fragment_diet_7));
        dietDinnerList.add(getResources().getString(R.string.fragment_diet_8));

        //Section 4
        dietEveningSnackList.add(getResources().getString(R.string.fragment_diet_4));
        dietEveningSnackList.add(getResources().getString(R.string.fragment_diet_5));
        dietEveningSnackList.add(getResources().getString(R.string.fragment_diet_6));
        dietEveningSnackList.add(getResources().getString(R.string.fragment_diet_7));
        dietEveningSnackList.add(getResources().getString(R.string.fragment_diet_8));

        //Section 5
        dietLunchList.add(getResources().getString(R.string.fragment_diet_4));
        dietLunchList.add(getResources().getString(R.string.fragment_diet_5));
        dietLunchList.add(getResources().getString(R.string.fragment_diet_6));
        dietLunchList.add(getResources().getString(R.string.fragment_diet_7));
        dietLunchList.add(getResources().getString(R.string.fragment_diet_8));

        //Section 6
        dietBeforeSleepList.add(getResources().getString(R.string.fragment_diet_4));
        dietBeforeSleepList.add(getResources().getString(R.string.fragment_diet_5));
        dietBeforeSleepList.add(getResources().getString(R.string.fragment_diet_6));
        dietBeforeSleepList.add(getResources().getString(R.string.fragment_diet_7));
        dietBeforeSleepList.add(getResources().getString(R.string.fragment_diet_8));


    }

    private void setLayoutSize(int size)
    {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lnrDietBreakfastExpandable.getLayoutParams();
        params.height = size;
        lnrDietBreakfastExpandable.setLayoutParams(params);
    }
}
