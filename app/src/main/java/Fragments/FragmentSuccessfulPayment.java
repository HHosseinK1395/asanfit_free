package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import FontComponents.MyButton;
import FontComponents.TextViewFont;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentSuccessfulPayment extends  Fragment{

    MyButton btnCompletePezeshki;
    TextViewFont txtWhichMonth;
    ImageView imgWhichMonth;

    private static FragmentSuccessfulPayment instance;
    public FragmentSuccessfulPayment() {
        // Required empty public constructor
    }

    public static FragmentSuccessfulPayment getInstance()
    {
        if (instance == null)
            instance = new FragmentSuccessfulPayment();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_successful_payment, container, false);

        btnCompletePezeshki = view.findViewById(R.id.btn_complete_pezeshki);

        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
        ActivityMain.lnrBotomMenu.setVisibility(View.INVISIBLE);
        txtWhichMonth = view.findViewById(R.id.txt_which_month);
        imgWhichMonth = view.findViewById(R.id.img_which_month);

        btnCompletePezeshki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityMain.fragmentManager.beginTransaction().replace(
                ActivityMain.globalFrame.getId(), FragmentParvandehPezeshki.getInstance(), "PARVANDEH_TAG").addToBackStack("PARVANDEH_TAG").commit();
            }
        });


        ConfigPanel();



        return view;
    }

    private void ConfigPanel()
    {
        switch(FragmentExtendedSubscription.getInstance().whichSelected)
        {
            case 1:
            {
                txtWhichMonth.setText("1");
                imgWhichMonth.setImageResource(R.drawable.plan_orange);
            }
            break;

            case 2:
            {
                txtWhichMonth.setText("3");
                imgWhichMonth.setImageResource(R.drawable.plan_purple);
            }
            break;

            case 3:
            {
                txtWhichMonth.setText("6");
                imgWhichMonth.setImageResource(R.drawable.plan_blue);
            }
            break;
        }
    }
}

