package Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import FontComponents.MyButton;
import FontComponents.MyEditText;
import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.DateConvertor;
import Others.Globals;
import Others.MyProgressDialog;
import Others.Settings;
import Structures.StructureAllWeghtDailyRecord;
import Structures.StructureWeightBasicInfo;
import Structures.StructureWeightForUpdate;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentInputWeightInfoAndDiagram extends Fragment {


    MyEditText editWeight, editHeight;
    TextViewFont txtWeightRight, txtWeightLeft, txtHeightRight, txtHeightLeft;
    Globals globals;
    GraphView graph;
    MyButton btnRegisterWeightInfo;
    FacadeService facadeService = new FacadeService();
    View parentView;
    TextViewFont[] txtMonth = new TextViewFont[7];
    TextViewFont[] txtDay = new TextViewFont[7];
    String[] strDateMonth = new String[7];
    String[] strDateDay = new String[7];
    StructureAllWeghtDailyRecord structureAllWeghtDailyRecord = new StructureAllWeghtDailyRecord();

    private static FragmentInputWeightInfoAndDiagram instance;
    public FragmentInputWeightInfoAndDiagram() {
        // Required empty public constructor
    }

    public static FragmentInputWeightInfoAndDiagram getInstance()
    {
        if (instance == null)
            instance = new FragmentInputWeightInfoAndDiagram();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_input_weight_info_and_diagram, container, false);

        editWeight = parentView.findViewById(R.id.edt_user_info_weight);
        editHeight = parentView.findViewById(R.id.edt_user_info_height);
        txtWeightRight = parentView.findViewById(R.id.txt_weight_right);
        txtWeightLeft = parentView.findViewById(R.id.txt_weight_left);
        txtHeightRight = parentView.findViewById(R.id.txt_height_right);
        txtHeightLeft = parentView.findViewById(R.id.txt_height_left);
        graph = parentView.findViewById(R.id.graph);
        btnRegisterWeightInfo = parentView.findViewById(R.id.btn_weight_info);

        txtMonth[0] = parentView.findViewById(R.id.txt_weight_info_saturday);
        txtMonth[1] = parentView.findViewById(R.id.txt_weight_info_sunday);
        txtMonth[2] = parentView.findViewById(R.id.txt_weight_info_monday);
        txtMonth[3] = parentView.findViewById(R.id.txt_weight_info_tuesday);
        txtMonth[4] = parentView.findViewById(R.id.txt_weight_info_wednesday);
        txtMonth[5] = parentView.findViewById(R.id.txt_weight_info_thursday);
        txtMonth[6] = parentView.findViewById(R.id.txt_weight_info_friday);

        txtDay[0] = parentView.findViewById(R.id.txt_weight_info_saturday1);
        txtDay[1] = parentView.findViewById(R.id.txt_weight_info_sunday1);
        txtDay[2] = parentView.findViewById(R.id.txt_weight_info_monday1);
        txtDay[3] = parentView.findViewById(R.id.txt_weight_info_tuesday1);
        txtDay[4] = parentView.findViewById(R.id.txt_weight_info_wednesday1);
        txtDay[5] = parentView.findViewById(R.id.txt_weight_info_thursday1);
        txtDay[6] = parentView.findViewById(R.id.txt_weight_info_friday1);



        //Init
        globals = new Globals(getActivity());
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        graph.setBackgroundColor(Color.argb(50, 50, 0, 200));
        editWeight.setText(String.valueOf(Settings.getInstance().getWeightForUpdate().getBasicInfo().getWeight()));
        editHeight.setText(String.valueOf(Settings.getInstance().getWeightForUpdate().getBasicInfo().getWaist()));

        ConfigDate();
        GetDailyRecord();


        editWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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


        editHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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


        btnRegisterWeightInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editHeight.getText().toString().equals("") || editHeight.getText().toString().equals(""))
                {
                    Snackbar.make(parentView, Html.fromHtml("<font color=\"#FF0000\">اطلاعات وزن و دور کمر را به درستی وارد کنید</font>"), Snackbar.LENGTH_LONG)
                            .addCallback(new Snackbar.Callback(){
                                @Override
                                public void onDismissed(Snackbar transientBottomBar, int event) {
                                }

                                @Override
                                public void onShown(Snackbar sb) {
                                    super.onShown(sb);

                                }
                            })
                            .show();

                    return;
                }

                final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();



                Settings.getInstance().loadAll();
                StructureWeightForUpdate basiInfo = new StructureWeightForUpdate();
                basiInfo.getBasicInfo().setWeight(Float.parseFloat(editWeight.getText().toString()));
                basiInfo.getBasicInfo().setWaist(Float.parseFloat(editHeight.getText().toString()));
                Settings.getInstance().setWeightForUpdate(basiInfo);
                Settings.getInstance().saveAll();

                facadeService.UpdateWeightInfo(new DataCallbbackOutput() {
                    @Override
                    public void onSuccess(String result) {
                        //Toast.makeText(getContext(), "ذخیره اطلاعات وزن - موفق", Toast.LENGTH_LONG).show();
                        Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">ثبت اطلاعات با موفقیت انجام شد</font>"), Snackbar.LENGTH_LONG);
                        cdd.dismiss();
                    }
                }, getActivity(), view, basiInfo);

            }
        });

        //setGraph();

        return parentView;
    }

    void ConfigDate()
    {
        DateConvertor dc = new DateConvertor();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < 7; i++) {
            Log.i("dateTag:", dc.miladiToShamsi(dc.getDateFromTimeStamp(cal.getTimeInMillis())));
            String[] str = dc.miladiToShamsi(dc.getDateFromTimeStamp(cal.getTimeInMillis())).split("/");

            txtMonth[i].setText(str[1]);
            if(str[1].equals("12") && Integer.parseInt(str[2]) <= 29)
                txtDay[i].setText("/" + String.valueOf(Integer.parseInt(str[2]) - 1));
            else
                txtDay[i].setText("/" + str[2]);
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }


        //Viewport viewport = graph.getViewport();
        //viewport.setMinX(0);
        //viewport.setMinY(0);

    }
    final List<String> lstServerWeights = new ArrayList<>();
    void GetDailyRecord()
    {
        final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

//        for(int i = 0; i < 7; i++)
//        {
//            lstServerWeights.add(0f);
//        }

        facadeService.GetDailyRecord(new DataCallbbackOutput() {
            @Override
            public void onSuccess(String result) {
                structureAllWeghtDailyRecord = new Gson().fromJson(result, StructureAllWeghtDailyRecord.class);
                if(structureAllWeghtDailyRecord != null) {
                    try {
                        JSONArray objArray = new JSONArray(new Gson().toJson(structureAllWeghtDailyRecord.getItems()));
                        for (int i = 0; i < objArray.length(); i++) {
                            JSONObject jsonObj = objArray.getJSONObject(i);
                            String dailyRecordString = jsonObj.get("dailyRecordData").toString();
                            JSONObject objInner = new JSONObject(dailyRecordString);


                            String dailyRecordStrignInner = objInner.get("basicInfo").toString();
                            StructureWeightBasicInfo structureCustomer = new Gson().fromJson(dailyRecordStrignInner, StructureWeightBasicInfo.class);
                            if (structureCustomer != null) {
                                lstServerWeights.add(String.valueOf(structureCustomer.getWeight()));
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Collections.reverse(lstServerWeights);
                    SetupGraph();
                }


                cdd.dismiss();



            }
        }, getActivity(), null);
    }

    void SetupGraph()
    {
        try {
            DataPoint[] dataPoints = new DataPoint[txtDay.length];
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
            Random r = new Random();
            for (int i = 0; i < txtDay.length; i++) {

                int i1 = r.nextInt(80 - 65) + 65;
                series.appendData(
                        new DataPoint(Integer.parseInt(txtDay[i].getText().toString().split("/")[1]), Float.parseFloat(lstServerWeights.get(i))), true, 500
                );
            }

            graph.addSeries(series);
        }catch (Exception e)
        {

        }
    }
}
