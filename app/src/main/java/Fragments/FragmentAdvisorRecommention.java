package Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.Globals;
import Others.MyProgressDialog;
import Structures.StructureMoshaverRecommended;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentAdvisorRecommention extends Fragment {

    TextViewFont txtBreakfast, txtLunch, txtDinner;
    FacadeService facadeService = new FacadeService();
    StructureMoshaverRecommended moshaverRecommended = new StructureMoshaverRecommended();
    View parentView;
    private static FragmentAdvisorRecommention instance;
    TextViewFont txtBreakfastTitle, txtLunchTitle, txtDinnerTitle;
    JSONArray jsArray;
    ArrayList<String> adviceStringArray = new ArrayList<String>();


    public FragmentAdvisorRecommention() {
        // Required empty public constructor
    }

    public static FragmentAdvisorRecommention getInstance()
    {
        if (instance == null)
            instance = new FragmentAdvisorRecommention();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_advisor_recommention, container, false);

        txtBreakfast = parentView.findViewById(R.id.txt_advisor_recommention_breakfast);
        txtLunch = parentView.findViewById(R.id.txt_advisor_recommention_lunch);
        txtDinner = parentView.findViewById(R.id.txt_advisor_recommention_dinner);

        txtBreakfastTitle = parentView.findViewById(R.id.txt_breakfast_title);
        txtLunchTitle = parentView.findViewById(R.id.txt_lunch_title);
        txtDinnerTitle = parentView.findViewById(R.id.txt_dinner_title);

        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);

        //TODO : Fill txt by server text
        //Valley
        GetRecommend();


        return parentView;
    }

    void GetRecommend()
    {
        final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();


        facadeService.GetMoshaverRecommended(new DataCallbbackOutput() {
            @Override
            public void onSuccess(String result) {
                moshaverRecommended = new Gson().fromJson(result, StructureMoshaverRecommended.class);
                if (moshaverRecommended != null) {
                    ParseRecommendedResultRecipe(moshaverRecommended, "adviceData");
                    txtBreakfast.setText(adviceStringArray.get(0));
                    txtLunch.setText(adviceStringArray.get(1));
                    txtDinner.setText(adviceStringArray.get(2));

                    cdd.dismiss();
                }
            }
        }, getContext(), parentView);
    }

    public void ParseRecommendedResultRecipe(StructureMoshaverRecommended mainResult, String parseString )
    {
        String recipeDataString;
        try {
            jsArray = new JSONArray(new Gson().toJson(mainResult.getItems()));
            for (int i = 0; i < jsArray.length(); i++)
            {

                JSONObject jsonObj = jsArray.getJSONObject(i);
                recipeDataString = jsonObj.getString(parseString);
                if (parseString.equals("adviceData"))
                {
                    JSONArray jsonArray = new JSONArray(recipeDataString);
                    for (int k = 0; k < jsonArray.length(); k++) {
                        adviceStringArray.add(jsonArray.getString(k));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//{"totalCount":1,"items":[{"isActive":true,"consultantId":4,"purchaseId":"24428deb-703b-42ce-8ce3-08d587dd79c8",
// "recipeData":
//      "[{
//          \"title\":\"عنوان غذا 1\",
//          \"description\":\"شرح 1\",
    //      \"recipe\":\"دستور پخت 1\"
//      },
//      {
//          \"title\":\"عنوان غذا 2\",
//          \"description\":\"شرح 2\",
//          \"recipe\":\"دستور پخت 2\"
//      }]",
// "adviceData":"[\"توصیه\",\"توصیه 2\",\"توصیه 3\"]","id":"8c1d0d73-8396-4d65-cf85-08d587dd79cf"}]}


}

