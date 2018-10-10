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
import java.util.List;

import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.Globals;
import Others.MyProgressDialog;
import Structures.MoshaverRecipeData;
import Structures.StructureMoshaverRecommended;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentHowToCook extends Fragment {

    TextViewFont txtBreakfast, txtLunch, txtDinner;
    private static FragmentHowToCook instance;
    FacadeService facadeService = new FacadeService();
    View parentView;
    List<MoshaverRecipeData> recipeDataStructure = new ArrayList<MoshaverRecipeData>();
    JSONArray jsArray;
    StructureMoshaverRecommended moshaverRecommended = new StructureMoshaverRecommended();
    TextViewFont txtBreakfastTitle, txtLunchTitle, txtDinnerTitle;

    public FragmentHowToCook() {
        // Required empty public constructor
    }

    public static FragmentHowToCook getInstance()
    {
        if (instance == null)
            instance = new FragmentHowToCook();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_how_to_cook, container, false);

        txtBreakfast = parentView.findViewById(R.id.txt_how_to_cook_breakfast);
        txtLunch = parentView.findViewById(R.id.txt_how_to_cook_lunch);
        txtDinner = parentView.findViewById(R.id.txt_how_to_cook_dinner);

        txtBreakfastTitle = parentView.findViewById(R.id.txt_how_to_cook_breakfast_title);
        txtLunchTitle = parentView.findViewById(R.id.txt_how_to_cook_lunch_title);
        txtDinnerTitle = parentView.findViewById(R.id.txt_how_to_cook_dinner_title);

        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);

        //TODO : Fill txt by server text
        //Valley
        GetHowToCook();


        return parentView;
    }

    void GetHowToCook()
    {
        final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

        facadeService.GetMoshaverRecommended(new DataCallbbackOutput() {
            @Override
            public void onSuccess(String result) {
                moshaverRecommended = new Gson().fromJson(result, StructureMoshaverRecommended.class);
                if(moshaverRecommended != null) {
                    ParseRecommendedResultRecipe(moshaverRecommended, "recipeData");

                    txtBreakfastTitle.setText(recipeDataStructure.get(0).getTitle());
                    txtLunchTitle.setText(recipeDataStructure.get(1).getTitle());
                    txtDinnerTitle.setText(recipeDataStructure.get(2).getTitle());

                    txtBreakfast.setText(recipeDataStructure.get(0).getDescription());
                    txtLunch.setText(recipeDataStructure.get(1).getDescription());
                    txtDinner.setText(recipeDataStructure.get(2).getDescription());

                }

                cdd.dismiss();

            }
        }, getContext(), parentView);
    }

    public void ParseRecommendedResultRecipe(StructureMoshaverRecommended mainResult, String parseString )
    {
        final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

        String recipeDataString;
        try {
            jsArray = new JSONArray(new Gson().toJson(mainResult.getItems()));
            for (int i = 0; i < jsArray.length(); i++)
            {

                JSONObject jsonObj = jsArray.getJSONObject(i);
                recipeDataString = jsonObj.getString(parseString);
                if(parseString.equals("recipeData"))
                {
                    JSONArray jsonMainArr = new JSONArray(recipeDataString);
                    for (int j = 0; j < jsonMainArr.length(); ++j) {
                        JSONObject rec = jsonMainArr.getJSONObject(j);
                        recipeDataStructure.add(new Gson().fromJson(rec.toString(), MoshaverRecipeData.class));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        cdd.dismiss();
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

