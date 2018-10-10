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

import FontComponents.MyButton;
import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.MyProgressDialog;
import Structures.MoshaverBasicInfo;
import Structures.StructureMoshaverProfile;
import de.hdodenhof.circleimageview.CircleImageView;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 1/4/2018.
 */

public class FragmentProfileMoshaver extends Fragment {


    private CircleImageView moshaverProfileImage;
    private MyButton btnSelectAdvisor;
    private TextViewFont txtName, txtFunction, txtPersonalInfomation, txtResume;
    private FacadeService facadeService = new FacadeService();
    StructureMoshaverProfile moshaverProfile = new StructureMoshaverProfile();
    View parentView;
    JSONArray jsArray;
    MoshaverBasicInfo moshaverBasicInfo = new MoshaverBasicInfo();

    private static FragmentProfileMoshaver instance;
    public FragmentProfileMoshaver() {
        // Required empty public constructor
    }

    public static FragmentProfileMoshaver getInstance()
    {
        if (instance == null)
            instance = new FragmentProfileMoshaver();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_profile_moshaver, container, false);

        moshaverProfileImage = parentView.findViewById(R.id.moshaver_profile_image);
        btnSelectAdvisor = parentView.findViewById(R.id.btn_select_advisor_continue);
        txtName = parentView.findViewById(R.id.txt_profile_moshaver_name);
        txtFunction = parentView.findViewById(R.id.txt_profile_moshaver_function);
        txtPersonalInfomation = parentView.findViewById(R.id.txt_moshaver_personal_information);
        txtResume = parentView.findViewById(R.id.txt_moshaver_resume);

        //init
        ConfigMoshaverProfile();
        btnSelectAdvisor.setVisibility(View.GONE);

        btnSelectAdvisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityMain.fragmentManager.beginTransaction().replace(
                ActivityMain.globalFrame.getId(), FragmentSelectAdvisor.getInstance(), "SELECT_ADVISOR_TAG").addToBackStack("SELECT_ADVISOR_TAG").commit();
            }
        });

        return parentView;
    }

    void ConfigMoshaverProfile()
    {
//        if(Settings.getInstance().getMoshaverProfile() != null)
//        {
//            txtName.setText(Settings.getInstance().getMoshaverProfile().getName());
//            txtFunction.setText(Settings.getInstance().getMoshaverProfile().getFunction());
//            txtPersonalInfomation.setText(Settings.getInstance().getMoshaverProfile().getPersonalInformation());
//            txtResume.setText(Settings.getInstance().getMoshaverProfile().getResume());
//        }
//        else
//        {
            final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.show();



            facadeService.GetMoshaverProfile(new DataCallbbackOutput() {
                @Override
                public void onSuccess(String result) {
                    moshaverProfile = new Gson().fromJson(result, StructureMoshaverProfile.class);
                    if(moshaverProfile != null) {
                        txtName.setText(moshaverProfile.getItems().get(0).getName());
                        txtFunction.setText(moshaverProfile.getItems().get(0).getSurname());
                        ParseMoshaverProfile(moshaverProfile, "profile");
                        if(moshaverBasicInfo != null) {
                            txtPersonalInfomation.setText(moshaverBasicInfo.getBasicInfo().getTitle() + "\n" +
                                    "سن مشاره:" +
                                    moshaverBasicInfo.getBasicInfo().getAge() + "\n" +
                                    "سال تولد مشاور:" +
                                    moshaverBasicInfo.getBasicInfo().getBirthDate()
                            );

                            txtResume.setText(
                                    "بیوگرافی مشاور:" + moshaverBasicInfo.getBiography() + "\n" +
                                    "رزومه:" + moshaverBasicInfo.getCurriculumVitae() + "\n" +
                                    "اطلاعات دیگر مشاور شما:" + moshaverBasicInfo.getInfo() + "\n");

                            if(moshaverProfile.getItems().get(0).getGender().equals("0")) //Femal
                            {
                                moshaverProfileImage.setImageResource(R.drawable.person_empty);
                            }
                            else
                            {
                                if(moshaverProfile.getItems().get(0).getGender().equals("1")) //Male
                                {
                                    moshaverProfileImage.setImageResource(R.drawable.person_empty);
                                }
                            }

                        }
                    }
                    else
                    {
                        txtName.setText("مشاوری به شما اختصاص نیافته است");
                        txtFunction.setText("");
                        txtPersonalInfomation.setText("");
                        txtResume.setText("");
                        moshaverProfileImage.setImageResource(R.drawable.person_empty);
                    }
                    cdd.dismiss();
                }
            }, getActivity(), parentView);
        //}
    }

    public void ParseMoshaverProfile(StructureMoshaverProfile mainResult, String parseString )
    {
        String recipeDataString;
        try
        {
            jsArray = new JSONArray(new Gson().toJson(mainResult.getItems()));
            for (int i = 0; i < jsArray.length(); i++)
            {
                JSONObject jsonObj = jsArray.getJSONObject(i);
                recipeDataString = jsonObj.getString(parseString);
                moshaverBasicInfo = new Gson().fromJson(recipeDataString, MoshaverBasicInfo.class);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
/*
{
    "totalCount":1,
    "items":
    [{
        "name":"Moshaver 1",
        "surname":"Moshaver 1",
        "isActive":true,
        "fullName":"Moshaver 1 Moshaver 1",
        "profile":
        "{
            \"basicInfo\":
            {
                \"title\":\"abc\",
                \"age\":48,
                \"birthDate\":\"1970\/1\/1\"
            },
            \"biography\":\"abc\",
            \"curriculumVitae\":\"abc\",
            \"Info\":\"abc\",
            \"rating\":{},
            \"additionalInfo\":{}
        }",
        "gender":1,
        "id":4
    }]
}
*/