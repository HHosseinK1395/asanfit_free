package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import FontComponents.MyButton;
import FontComponents.TextViewFont;
import Others.Globals;
import Others.Settings;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentDiscountCode extends Fragment {

    StringBuffer buffer;
    TextViewFont txtDiscountCod;
    MyButton btnShare;

    private static FragmentDiscountCode instance;
    public FragmentDiscountCode() {
        // Required empty public constructor
    }

    public static FragmentDiscountCode getInstance()
    {
        if (instance == null)
            instance = new FragmentDiscountCode();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discount_code, container, false);

        txtDiscountCod = view.findViewById(R.id.txt_discount_code);
        btnShare = view.findViewById(R.id.btn_user_info_complete_start);
        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);
        buffer = new StringBuffer(Settings.getInstance().getProfile().profile.getName());
        txtDiscountCod.setText(buffer.reverse().toString());

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody = txtDiscountCod.getText().toString();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "آسان فیت");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, txtDiscountCod.getText().toString()));
            }
        });


        return view;
    }
}

