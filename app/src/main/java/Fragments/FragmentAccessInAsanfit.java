package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import FontComponents.MyButton;
import Others.Globals;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentAccessInAsanfit extends Fragment {

    MyButton btnPlans;

    private static FragmentAccessInAsanfit instance;
    public FragmentAccessInAsanfit() {
        // Required empty public constructor
    }

    public static FragmentAccessInAsanfit getInstance()
    {
        if (instance == null)
            instance = new FragmentAccessInAsanfit();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_access_in_asanfit, container, false);

        btnPlans = view.findViewById(R.id.btn_access_in_asanfit_plans);

        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
        ActivityMain.lnrBotomMenu.setVisibility(View.INVISIBLE);

        btnPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityMain.fragmentManager.beginTransaction().replace(
                ActivityMain.globalFrame.getId(), FragmentExtendedSubscription.getInstance(), "EXTENDED_SUBSCRIPTION__TAG").addToBackStack("EXTENDED_SUBSCRIPTION__TAG").commit();
            }
        });

        return view;
    }

    private void XmlToJson() {

    }

}

