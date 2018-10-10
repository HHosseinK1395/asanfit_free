package Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import FontComponents.MyButton;
import FontComponents.MyEditText;
import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.Globals;
import Others.MyProgressDialog;
import Others.Settings;
import Structures.Purchase;
import Structures.StructurePlan;
import Structures.StructurePurchase;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentExtendedSubscription extends Fragment {


    private ImageView imgExtendedSubscription[] = new ImageView[3];
    private ImageView imgSendCode;
    private TextViewFont
                txtDiscountAmount,
                txtAmount,
                txtEndDayAmount,
                txt1Month,
                txt3Month,
                txt6Month,
                txt1MonthRagham,
                txt3MonthRagham,
                txt6MonthRagham,
                txt3MonthDiscount,
                txt6MonthDiscount;
    private MyButton btnBuy;
    private LinearLayout linearLayout1Month, linearLayout3Month, linearLayout6Month;
    private MyEditText edtCode;
    private View parentView;
    private StructurePlan plan = new StructurePlan();
    private StructurePurchase purchase = new StructurePurchase();
    public static int whichSelected;


    private FacadeService facadeService = new FacadeService();

    private static FragmentExtendedSubscription instance;
    public FragmentExtendedSubscription() {
        // Required empty public constructor
    }

    public static FragmentExtendedSubscription getInstance()
    {
        if (instance == null)
            instance = new FragmentExtendedSubscription();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_extended_subscription, container, false);

        imgExtendedSubscription[0] = parentView.findViewById(R.id.img_extended_subscription_1_month);
        imgExtendedSubscription[1] = parentView.findViewById(R.id.img_extended_subscription_3_month);
        imgExtendedSubscription[2] = parentView.findViewById(R.id.img_extended_subscription_6_month);

        imgSendCode = parentView.findViewById(R.id.img_send_code);
        txtDiscountAmount = parentView.findViewById(R.id.txt_discount_amount);
        txtAmount = parentView.findViewById(R.id.txt_discount);
        txtEndDayAmount = parentView.findViewById(R.id.txt_end_days_amount);
        btnBuy = parentView.findViewById(R.id.btn_buy);

        linearLayout3Month = parentView.findViewById(R.id.lnr_pishnehadi_3_month_parent);
        linearLayout6Month = parentView.findViewById(R.id.lnr_pishnehadi_6_month_parent);

        txt3Month = parentView.findViewById(R.id.txt_pishnehadi_3_month_toman);
        txt6Month = parentView.findViewById(R.id.txt_pishnehadi_6_month_toman);
        txt1Month = parentView.findViewById(R.id.txt_pishnehadi_1_month_toman);
        txt1MonthRagham = parentView.findViewById(R.id.txt_pishnehadi_1_month_ragham);
        txt3MonthRagham = parentView.findViewById(R.id.txt_pishnehadi_3_month_ragham);
        txt6MonthRagham = parentView.findViewById(R.id.txt_pishnehadi_6_month_ragham);
        txt3MonthDiscount = parentView.findViewById(R.id.txt_pishnehadi_3_month_discount);
        txt6MonthDiscount = parentView.findViewById(R.id.txt_pishnehadi_6_month_discount);

        edtCode = parentView.findViewById(R.id.edt_register_code);

        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
        ActivityMain.lnrBotomMenu.setVisibility(View.INVISIBLE);
        txt1Month.setTextColor(getResources().getColor(R.color.gray));
        whichSelected = 1;

        ConfigPlans();

        imgExtendedSubscription[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                txt1Month.setTextColor(getResources().getColor(R.color.gray));
                txt3Month.setTextColor(getResources().getColor(R.color.black));
                txt6Month.setTextColor(getResources().getColor(R.color.black));

                imgExtendedSubscription[0].setImageResource(R.drawable.plan_orange_without_shadow);
                imgExtendedSubscription[1].setImageResource(R.drawable.plan_purple);
                imgExtendedSubscription[2].setImageResource(R.drawable.plan_blue);


                whichSelected = 1;
            }
        });

        imgExtendedSubscription[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                txt1Month.setTextColor(getResources().getColor(R.color.black));
                txt3Month.setTextColor(getResources().getColor(R.color.gray));
                txt6Month.setTextColor(getResources().getColor(R.color.black));

                imgExtendedSubscription[0].setImageResource(R.drawable.plan_orange);
                imgExtendedSubscription[1].setImageResource(R.drawable.plan_purple_without_shadow);
                imgExtendedSubscription[2].setImageResource(R.drawable.plan_blue);

                whichSelected = 2;
            }
        });

        imgExtendedSubscription[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                txt1Month.setTextColor(getResources().getColor(R.color.black));
                txt3Month.setTextColor(getResources().getColor(R.color.black));
                txt6Month.setTextColor(getResources().getColor(R.color.gray));

                imgExtendedSubscription[0].setImageResource(R.drawable.plan_orange);
                imgExtendedSubscription[1].setImageResource(R.drawable.plan_purple);
                imgExtendedSubscription[2].setImageResource(R.drawable.plan_blue_without_shadow);

                whichSelected = 3;
            }
        });

        imgSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : send discount code and get response from server for setup discount code
                //TODO : get discount from server and set txtDiscountAmount and txtAmount in bottom of panel.
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), FragmentSuccessfulPayment.class);
//                startActivity(intent);
                //ActivityMain.fragmentManager.beginTransaction().replace(
                        //ActivityMain.globalFrame.getId(), FragmentSuccessfulPayment.getInstance(), "PROFILE_MOSHAVER_TAG").addToBackStack("PROFILE_MOSHAVER_TAG").commit();
                final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال انجام خرید...");
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();

                switch(whichSelected)
                {
                    case 1:
                    {
                        Purchase myPlans = new Purchase();
                        myPlans.setPlanId(Settings.getInstance().getPlan().getItems().get(0).getId());
                        myPlans.setCouponId(Settings.getInstance().getPlan().getItems().get(0).getId());
                        myPlans.setCustomerId(0);
                        myPlans.setEndDate("0");
                        myPlans.setRemainingDays(2);
                        myPlans.setActive(false);
                        myPlans.setPayedPrice(1000);
                        myPlans.setCreationTime("0");
                        myPlans.setId("0");

                        purchase.getItems().add(myPlans);
                    }
                    break;
                    case 2:
                    {
                        Purchase myPlans = new Purchase();
                        myPlans.setPlanId(Settings.getInstance().getPlan().getItems().get(1).getId());
                        myPlans.setCouponId(Settings.getInstance().getPlan().getItems().get(1).getId());
                        myPlans.setCustomerId(0);
                        myPlans.setEndDate("0");
                        myPlans.setRemainingDays(2);
                        myPlans.setActive(false);
                        myPlans.setPayedPrice(1000);
                        myPlans.setCreationTime("0");
                        myPlans.setId("0");

                        purchase.getItems().add(myPlans);
                    }
                    break;
                    case 3:
                    {
                        Purchase myPlans = new Purchase();
                        myPlans.setPlanId(Settings.getInstance().getPlan().getItems().get(2).getId());
                        myPlans.setCouponId(Settings.getInstance().getPlan().getItems().get(2).getId());
                        myPlans.setCustomerId(0);
                        myPlans.setEndDate("0");
                        myPlans.setRemainingDays(2);
                        myPlans.setActive(false);
                        myPlans.setPayedPrice(1000);
                        myPlans.setCreationTime("0");
                        myPlans.setId("0");

                        purchase.getItems().add(myPlans);
                    }
                    break;
                }
                facadeService.Purchase(new DataCallbbackOutput() {
                    @Override
                    public void onSuccess(String result) {
                        purchase = new Gson().fromJson(result, StructurePurchase.class);
                        if(purchase != null)
                        {
                            Settings.getInstance().setPurchase(purchase);
                            switch(whichSelected)
                            {
                                case 1:
                                {
                                    Settings.getInstance().getStructurePlanBuyOrNot().setB1MonthBuy(false);
                                    Settings.getInstance().getStructurePlanBuyOrNot().setB3MonthBuy(true);
                                    Settings.getInstance().getStructurePlanBuyOrNot().setB6MonthBuy(true);
                                }
                                break;
                                case 2:
                                {
                                    Settings.getInstance().getStructurePlanBuyOrNot().setB1MonthBuy(true);
                                    Settings.getInstance().getStructurePlanBuyOrNot().setB3MonthBuy(false);
                                    Settings.getInstance().getStructurePlanBuyOrNot().setB6MonthBuy(true);
                                }
                                break;
                                case 3:
                                {
                                    Settings.getInstance().getStructurePlanBuyOrNot().setB1MonthBuy(true);
                                    Settings.getInstance().getStructurePlanBuyOrNot().setB3MonthBuy(true);
                                    Settings.getInstance().getStructurePlanBuyOrNot().setB6MonthBuy(false);
                                }
                                break;
                                default:
                                {

                                }
                                break;
                            }
                            Settings.getInstance().saveAll();
                        }
                        cdd.dismiss();
                        ActivityMain.fragmentManager.beginTransaction().replace(
                                ActivityMain.globalFrame.getId(),
                                FragmentSuccessfulPayment.getInstance(),
                                "PROFILE_MOSHAVER_TAG").addToBackStack("PROFILE_MOSHAVER_TAG").commit();
                    }
                }, getActivity(), parentView, purchase);

            }
        });

        edtCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    view.setBackgroundResource(R.drawable.shape_edittext_focused);
                }
                else
                {
                    view.setBackgroundResource(R.drawable.shape_edittext);
                }
            }
        });

        return parentView;
    }

    void ConfigPlans()
    {
//        if(Settings.getInstance().getPlan() != null)
//        {
//            txt1MonthRagham.setText(Settings.getInstance().getPlan().getItems().get(0).getPayedPrice());
//            txt3MonthRagham.setText(Settings.getInstance().getPlan().getItems().get(1).getPayedPrice());
//            txt6MonthRagham.setText(Settings.getInstance().getPlan().getItems().get(2).getPayedPrice());
//            txt3MonthDiscount.setText(Settings.getInstance().getPlan().getItems().get(4).getPayedPrice());
//            txt6MonthDiscount.setText(Settings.getInstance().getPlan().getItems().get(5).getPayedPrice());
//        }
//        else
//        {
        final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();


        try
        {
            if(!Settings.getInstance().getStructurePlanBuyOrNot().isB1MonthBuy())
            {
                imgExtendedSubscription[0].setEnabled(false);
            }

            if(!Settings.getInstance().getStructurePlanBuyOrNot().isB3MonthBuy())
            {
                imgExtendedSubscription[1].setEnabled(false);
            }

            if(!Settings.getInstance().getStructurePlanBuyOrNot().isB6MonthBuy())
            {
                imgExtendedSubscription[2].setEnabled(false);
            }


        }catch(Exception e)
        {

        }

        facadeService.GetPlan(new DataCallbbackOutput() {
                @Override
                public void onSuccess(String result) {
                    plan = new Gson().fromJson(result, StructurePlan.class);
                    if(plan != null && plan.getTotalCount() != 0)
                    {
                        txt6MonthRagham.setText("" + plan.getItems().get(0).getPrice());
                        txt3MonthRagham.setText("" +  + plan.getItems().get(1).getPrice());
                        txt1MonthRagham.setText("" + plan.getItems().get(2).getPrice());
                        //txt3MonthDiscount.setText(plan.getItems().get(4).getPrice());
                        //txt6MonthDiscount.setText(plan.getItems().get(5).getPrice());

                        Settings.getInstance().setPlan(plan);
                        Settings.getInstance().saveAll();
                    }
                    else
                    {
                        Snackbar.make(getActivity().findViewById(android.R.id.content), Html.fromHtml("<font color=\"#FF0000\">لطفا اینترنت خود را چک کنید</font>"), Snackbar.LENGTH_LONG)
                                .addCallback(new Snackbar.Callback(){
                                    @Override
                                    public void onDismissed(Snackbar snackbar, int event) {
                                        //ActivityMain.fragmentManager.beginTransaction().replace(
                                                //ActivityMain.globalFrame.getId(), FragmentFirstPage.getInstance()).addToBackStack("FRAGMENT_FIRST_PAGE_TAG").commit();
                                    }

                                    @Override
                                    public void onShown(Snackbar snackbar) {

                                    }
                                })
                                .show();
                    }

                    cdd.dismiss();
                }
            }, getActivity(), parentView);
        //}

    }
}

