package Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.hookedonplay.decoviewlib.DecoView;

import java.util.ArrayList;
import java.util.List;

import FontComponents.MyButton;
import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.Globals;
import Others.Settings;
import Structures.StructureNotification;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.NotificationService;
import ir.andishehlab.asanfit.R;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentFirstPage extends Fragment {

    private static FragmentFirstPage instance;
    private DecoView arcViewUp, arcViewDown1, arcViewDown2, arcViewDown3;
    private TextViewFont
            textViewWeight,
            txtOverWeight,
            txtWeightLoss,
            txtBodyMass,
            txtSliderText,
            txtBottomLeft,
            txtBottomRight,
            txtSourceWeight,
            txtDestweight;

    public static TextViewFont txtNotification;
    private MyButton btnBuildYours, btnTaghzieh, btnBottomRight, btnBottomLeft;
    private ImageView imgSliderLeft, imgSliderRight, imgBottomLeft, imgBottomRight, imgRotate;
    private BannerSlider bannerSlider;
    private LinearLayout lnrCloseMessage, lnrCloseParent;
    private FacadeService facadeService = new FacadeService();
    private View parentView;
    //publics
    public static String currentWeight = null;
    StructureNotification notification = new StructureNotification();

    public FragmentFirstPage() {
        // Required empty public constructor
    }

    public static FragmentFirstPage getInstance()
    {
        if (instance == null)
            instance = new FragmentFirstPage();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_first_page, container, false);


        Log.d("TokeTokenToken", Settings.getInstance().getProfile().getAuthorizationToken());
        arcViewUp = parentView.findViewById(R.id.circle_progress_weight);
        arcViewDown1 = parentView.findViewById(R.id.circle_progress_1);
        arcViewDown2 = parentView.findViewById(R.id.circle_progress_2);
        arcViewDown3 = parentView.findViewById(R.id.circle_progress_3);
        textViewWeight =  parentView.findViewById(R.id.txt_weight);
        txtOverWeight = parentView.findViewById(R.id.txt_overweight_measure);
        txtWeightLoss = parentView.findViewById(R.id.txt_weightloss_measure);
        txtBodyMass = parentView.findViewById(R.id.txt_bodymass_measure);
        btnBuildYours = parentView.findViewById(R.id.btn_user_info_complete_start);
        txtSliderText = parentView.findViewById(R.id.txt_dashboard_slider_text);
        imgSliderRight = parentView.findViewById(R.id.img_dashboard_slider_arrow_right);
        imgSliderLeft = parentView.findViewById(R.id.img_dashboard_slider_arrow_left);
        bannerSlider = parentView.findViewById(R.id.banner_slider1);
        btnTaghzieh = parentView.findViewById(R.id.btn_dashboard_slider_taghzieh);
        imgBottomRight = parentView.findViewById(R.id.img_dashboard_bottom_right);
        imgBottomLeft = parentView.findViewById(R.id.img_dashboard_bottom_left);
        btnBottomLeft = parentView.findViewById(R.id.btn_dashboard_bottom_left);
        btnBottomRight = parentView.findViewById(R.id.btn_dashboard_bottom_right);
        txtBottomLeft = parentView.findViewById(R.id.txt_dashboard_bottom_left);
        txtBottomRight = parentView.findViewById(R.id.txt_dashboard_bottom_right);
        imgRotate = parentView.findViewById(R.id.img_rotate);
        lnrCloseMessage = parentView.findViewById(R.id.img_message_close);
        lnrCloseParent = parentView.findViewById(R.id.lnr_close_message_parent);
        txtNotification = parentView.findViewById(R.id.txt_notification_text);
        txtSourceWeight = parentView.findViewById(R.id.txt_source_weight);
        txtDestweight = parentView.findViewById(R.id.txt_destination_weight);

        //Init
        //Globals.toolbarBack.setVisibility(View.INVISIBLE);
        try {
            ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.mycolor_yellow));
            ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.mycolor_yellow));
            ActivityMain.imgProgram.setColorFilter(getResources().getColor(R.color.black));
            ActivityMain.txtProgram.setTextColor(getResources().getColor(R.color.black));
            ActivityMain.imgChat.setColorFilter(getResources().getColor(R.color.black));
            ActivityMain.txtChat.setTextColor(getResources().getColor(R.color.black));
            if (Settings.getInstance().getStrNotificationMessage() != null)
                txtNotification.setText(Settings.getInstance().getStrNotificationMessage());
            ActivityMain.lnrBotomMenu.setVisibility(View.VISIBLE);
        }catch(Exception e)
        {

        }

        Globals globals = new Globals(getActivity());
        txtSourceWeight.setText("" + (int)Float.parseFloat(Settings.getInstance().getDashbord().getWeight()));
        txtDestweight.setText("" + (int)Float.parseFloat(Settings.getInstance().getDashbord().getIdealWeight()));

        int iCurrentWeight = 0;
        if(Integer.parseInt(Settings.getInstance().getDashbord().getCurrentWeight()) == 0)
            iCurrentWeight = Integer.parseInt(Settings.getInstance().getDashbord().getCurrentWeight());
        else
            iCurrentWeight = Integer.parseInt(Settings.getInstance().getDashbord().getCurrentWeight());

        globals.InitArcView(
                arcViewUp,
                textViewWeight,
                iCurrentWeight,
                10,
                100,
                Color.TRANSPARENT,
                false, 500,
                false);

        globals.InitArcView(
                arcViewDown1,
                txtOverWeight,
                (int)Float.parseFloat(Settings.getInstance().getDashbord().getShakhes()),
                7,
                100,
                getResources().getColor(R.color.first_page_circle_color_1),
                false,
                500,
                false);

        globals.InitArcView(
                arcViewDown2,
                txtWeightLoss,
                31,
                7,
                100,
                getResources().getColor(R.color.first_page_circle_color_2),
                false,
                500,
                true);

        globals.InitArcView(
                arcViewDown3,
                txtBodyMass,
                (int)Float.parseFloat(Settings.getInstance().getDashbord().getOverweight()),
                7,
                100,
                getResources().getColor(R.color.first_page_circle_color_3),
                false,
                500,
                false);


        btnBuildYours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : Test for Moshaver Profile
                ActivityMain.fragmentManager.beginTransaction().replace(
                ActivityMain.globalFrame.getId(), FragmentAccessInAsanfit.getInstance(), "ACCESS_IN_ASANFIT_TAG").addToBackStack("ACCESS_IN_ASANFIT_TAG").commit();
            }
        });

        InitBannerSlider(parentView);

        arcViewUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityMain.fragmentManager.beginTransaction().replace(
                ActivityMain.globalFrame.getId(), FragmentInputWeightInfoAndDiagram.getInstance()).addToBackStack("PROFILE_MOSHAVER_TAG").commit();
            }
        });

        imgSliderRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerSlider.setCurrentSlide(bannerSlider.getCurrentSlidePosition() + 1);
            }
        });

        imgSliderLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerSlider.setCurrentSlide(bannerSlider.getCurrentSlidePosition() - 1);
            }
        });

        btnTaghzieh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lnrCloseMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lnrCloseParent.animate()
                    .translationZ(view.getHeight())
                    .alpha(0.0f)
                    .setDuration(1000)
                    .setListener(new AnimatorListenerAdapter()
                    {
                        @Override
                        public void onAnimationEnd(Animator animation)
                        {
                            super.onAnimationEnd(animation);
                            lnrCloseParent.setVisibility(View.GONE);
                        }
                    });
            }
        });

        //RunNotificationThread();

        //Run Notification Service
        Intent intent = new Intent(getActivity(), NotificationService.class);
        getActivity().startService(intent);


        LocalBroadcastreciver();

        return parentView;
    }

    void LocalBroadcastreciver()
    {
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String notificationMessage = intent.getStringExtra(NotificationService.EXTRA_NOTIFICATION);

                        txtNotification.setText(notificationMessage);
                    }
                }, new IntentFilter(NotificationService.ACTION_LOCATION_BROADCAST)
        );
    }

    public void RunNotificationThread()
    {
        facadeService.GetNotification(new DataCallbbackOutput() {
            @Override
            public void onSuccess(String result) {
                notification = new Gson().fromJson(result, StructureNotification.class);
                if(notification != null) {
                    //Toast.makeText(getContext(), "اجرا شد", Toast.LENGTH_SHORT).show();
                    //txtNotification.setText("این متن به صورت تستی است .");
                }
            }
        }, getActivity(), parentView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rotate(74);
            }
        }, 2000);
    }

    private void rotate(float degree) {
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, (degree * 360) / 100,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(2000);
        rotateAnim.setFillAfter(true);
        imgRotate.startAnimation(rotateAnim);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void InitBannerSlider(View myView) {
        List<Banner> banners = new ArrayList<Banner>();
        banners.add(new DrawableBanner(R.drawable.slider_bk_4));
        banners.add(new DrawableBanner(R.drawable.slider_bk_5));
        banners.add(new DrawableBanner(R.drawable.slider_bk_6));

        bannerSlider.setBanners(banners);


    }

    @Override
    public void onDetach() {

        //ActivityMain.lnrBotomMenu.setVisibility(View.INVISIBLE);
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}

