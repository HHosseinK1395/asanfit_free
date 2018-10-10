package Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import FontComponents.MyButton;
import FontComponents.TextViewFont;
import Others.Globals;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentAboutUs extends Fragment {

    ImageView imgAparat, imgTelegram, imgInstagram;
    MyButton btnPhone, btnTelegram;
    private static FragmentAboutUs instance;
    TextViewFont txtRules;
    public FragmentAboutUs() {
        // Required empty public constructor
    }



    public static FragmentAboutUs getInstance()
    {
        if (instance == null)
            instance = new FragmentAboutUs();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        imgAparat = view.findViewById(R.id.btn_about_us_aparat);
        imgTelegram = view.findViewById(R.id.btn_about_us_telegram);
        imgInstagram = view.findViewById(R.id.btn_about_us_instagram);
        txtRules = view.findViewById(R.id.txt_rules_in_about_us);
        btnPhone = view.findViewById(R.id.btn_about_phone);
        btnTelegram = view.findViewById(R.id.btn_about_telegram);

        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);
        txtRules.setPaintFlags(txtRules.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        imgAparat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentMessageAparat("برنامه ما را ببینید");
            }
        });

        imgTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentMessageTelegram("برنامه ما را ببینید");
            }
        });

        imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentMessageInstagram("برنامه ما را ببینید");
            }
        });

        txtRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imgTick.setBackgroundResource(R.drawable.ic_register_rules_orange);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.register_rule_url)));
                startActivity(browserIntent);
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call("09111111111");
            }
        });

        btnTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentMessageTelegram("09111111111");
            }
        });

        return view;
    }

    void Call(String phone)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    private void XmlToJson() {

    }

    void intentMessageTelegram(String msg)
    {
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = isAppAvailable(getActivity().getApplicationContext(), appName);
        if (isAppInstalled)
        {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            myIntent.setPackage(appName);
            myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
            getActivity().startActivity(Intent.createChooser(myIntent, "Share with"));
        }
        else
        {
            Toast.makeText(getActivity(), "تلگرام نصب نشده است", Toast.LENGTH_SHORT).show();

        }
    }

    void intentMessageInstagram(String msg)
    {
        final String appName = "com.instagram.android";
        final boolean isAppInstalled = isAppAvailable(getActivity().getApplicationContext(), appName);
        if (isAppInstalled) {
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.setPackage(appName);
            shareIntent.putExtra(Intent.EXTRA_TITLE, msg);
            startActivity(shareIntent);
        }
        else
        {
            Toast.makeText(getActivity(), "اینستاگرام نصب نشده است", Toast.LENGTH_SHORT).show();
        }

    }

    void intentMessageAparat(String msg)
    {
        final String appName = "com.aparat";
        final boolean isAppInstalled = isAppAvailable(getActivity().getApplicationContext(), appName);
        if (isAppInstalled) {
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.setPackage(appName);
            shareIntent.putExtra(Intent.EXTRA_TITLE, msg);
            startActivity(shareIntent);
        }
        else
        {
            Toast.makeText(getActivity(), "آپارات نصب نشده است", Toast.LENGTH_SHORT).show();
        }

    }

    public static boolean isAppAvailable(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }

}

