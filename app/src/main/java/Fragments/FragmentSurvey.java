package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import FontComponents.MyButton;
import Others.Globals;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentSurvey extends Fragment {

    private ImageView[] imgSurveyRate;
    private static FragmentSurvey instance;
    private MyButton btnThisAdvisor, btnNewAdvisor;
    private FragmentSurvey() {
        // Required empty public constructor
    }

    public static FragmentSurvey getInstance()
    {
        if (instance == null)
            instance = new FragmentSurvey();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_survey, container, false);

        //Init first
        imgSurveyRate = new ImageView[5];
        imgSurveyRate[0] = view.findViewById(R.id.img_survey_rate_1);
        imgSurveyRate[1] = view.findViewById(R.id.img_survey_rate_2);
        imgSurveyRate[2] = view.findViewById(R.id.img_survey_rate_3);
        imgSurveyRate[3] = view.findViewById(R.id.img_survey_rate_4);
        imgSurveyRate[4] = view.findViewById(R.id.img_survey_rate_5);
        btnThisAdvisor = view.findViewById(R.id.btn_survey_this_advisor);
        btnNewAdvisor = view.findViewById(R.id.btn_survey_new_advisor);

        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);

        setRate(3);

        btnThisAdvisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnNewAdvisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    void setRate(int rate)
    {
        if(rate > 5)
        {

        } else {
            for (int i = 0; i < imgSurveyRate.length; i++) {
                if (i < rate) {
                    imgSurveyRate[i].setImageResource(R.drawable.star_orange);
                } else {
                    imgSurveyRate[i].setImageResource(R.drawable.start_gray);
                }
            }
        }
    }
}

