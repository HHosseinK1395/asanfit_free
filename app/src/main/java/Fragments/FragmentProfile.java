package Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontComponents.MyButton;
import FontComponents.MyEditText;
import FontComponents.TextViewFont;
import Network.DataCallbbackOutput;
import Network.FacadeService;
import Others.Globals;
import Others.MyProgressDialog;
import Others.Settings;
import Structures.StructureCustomer;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentProfile extends Fragment {


    private AppCompatSpinner spinner;
    private SwitchCompat switchNotification, switchMotivational;
    private TextViewFont btnExit;
    private Typeface myFont;
    private List<String> categories;
    private MyEditText edtUserInfoName, edtUserInfoMobile, edtUserInfoEmail;
    private FacadeService facadeService = new FacadeService();
    private MyButton btnEdit;
    StructureCustomer structureCustomer = new StructureCustomer();
    StructureCustomer innerStructureCustomer = new StructureCustomer();
    View parentView;
    private static FragmentProfile instance;
    private FragmentProfile() {
        // Required empty public constructor
    }

    public static FragmentProfile getInstance()
    {
        if (instance == null)
            instance = new FragmentProfile();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_profile, container, false);

        spinner = parentView.findViewById(R.id.spinner);
        switchNotification =  parentView.findViewById(R.id.switch_resolve_notification_message);
        switchMotivational = parentView.findViewById(R.id.switch_resolve_motivational_message);
        btnExit = parentView.findViewById(R.id.btn_exit);
        edtUserInfoName = parentView.findViewById(R.id.edt_user_info_name);
        edtUserInfoMobile = parentView.findViewById(R.id.edt_user_info_mobile);
        edtUserInfoEmail = parentView.findViewById(R.id.edt_user_info_email);
        btnEdit = parentView.findViewById(R.id.btn_user_info_calculate);

        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);
        myFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/IranSansFaNum.ttf");
        edtUserInfoName.setEnabled(false);
        edtUserInfoMobile.setEnabled(false);
        edtUserInfoEmail.setEnabled(false);

        ConfigProfile();

        //Init
        btnExit.setPaintFlags(btnExit.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//set underline for textview
        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
        categories = new ArrayList<String>();
        categories.add("مازندران");
        categories.add("تهران");
        categories.add("شیراز");
        categories.add("اصفهان");
        categories.add("مشهد");
        categories.add("کرمانشاه");


//        CSVReader reader = null;
//        try {
//            reader = new CSVReader(new FileReader("city.csv"));
//
//            String [] nextLine;
//            try {
//                while ((nextLine = reader.readNext()) != null) {
//                    // nextLine[] is an array of values from the line
//                    Log.e("EEEEEEEEEEEEEEEEEEEEEE", nextLine[0] + nextLine[1] + "etc...");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        SetupSpinnersAdapter(spinner, categories);

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b)
            {

            }
            }
        });

        switchMotivational.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b)
            {

            }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        edtUserInfoName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        edtUserInfoMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        edtUserInfoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();

                innerStructureCustomer = new StructureCustomer();
                innerStructureCustomer.profile.setName(edtUserInfoName.getText().toString());
                innerStructureCustomer.profile.setPhoneNumber(edtUserInfoMobile.getText().toString());
                innerStructureCustomer.profile.setEmail(edtUserInfoEmail.getText().toString());
                innerStructureCustomer.profile.setCity(spinner.getSelectedItemPosition());

                facadeService.UpdateProfile(new DataCallbbackOutput() {
                    @Override
                    public void onSuccess(String result) {
                        if(!result.equals("")) {
                            String token = Settings.getInstance().getProfile().getAuthorizationToken();
                            innerStructureCustomer.setTenantId(1);
                            ActivityMain.txtNavUserName.setText(innerStructureCustomer.profile.getName());
                            Settings.getInstance().setProfile(innerStructureCustomer);
                            Settings.getInstance().getProfile().setAuthorizationToken(token);
                            Settings.getInstance().saveAll();

                            //Toast.makeText(getContext(), "ذخیره پروفایل - موفق", Toast.LENGTH_LONG).show();

                            Snackbar.make(getActivity().findViewById(android.R.id.content), Html.fromHtml("<font color=\"#00FF00\">ذخیره اطلاعات موفقیت آمیز بود</font>"), Snackbar.LENGTH_LONG)
                                    .addCallback(new Snackbar.Callback(){
                                        @Override
                                        public void onDismissed(Snackbar snackbar, int event) {
                                            ActivityMain.fragmentManager.beginTransaction().replace(
                                                    ActivityMain.globalFrame.getId(), FragmentFirstPage.getInstance()).addToBackStack("FRAGMENT_FIRST_PAGE_TAG").commit();
                                        }

                                        @Override
                                        public void onShown(Snackbar snackbar) {

                                        }
                                    })
                                    .show();

                        }

                        cdd.dismiss();
                    }

                }, getActivity(), innerStructureCustomer, view, true);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.getInstance().setbIsRegistered(false);
                ExitApp();
                ((ActivityMain)getActivity()).finish();
            }
        });

        return parentView;
    }

    void ExitApp()
    {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    void SetupSpinnersAdapter(AppCompatSpinner spinner, List<String> lst)
    {
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.layout_spinner, lst);
//        dataAdapter.setDropDownViewResource(R.layout.layout_spinner);
//        spinner.setAdapter(dataAdapter);

        MyArrayAdapter ma = new MyArrayAdapter(getContext(), lst);
        spinner.setAdapter(ma);
    }
    private void XmlToJson() {

    }

    private class MyArrayAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        List<String> list;

        public MyArrayAdapter(Context con, List<String> lst) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(con);
            list = lst;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final FragmentParvandehPezeshki.ListContent holder;
            View v = convertView;
            if (v == null) {
                v = mInflater.inflate(R.layout.my_spinner_style, null);
                holder = new FragmentParvandehPezeshki.ListContent();

                holder.name = (TextView) v.findViewById(R.id.textView1);

                v.setTag(holder);
            } else {

                holder = (FragmentParvandehPezeshki.ListContent) v.getTag();
            }

            holder.name.setTypeface(myFont);
            holder.name.setText(list.get(position));

            return v;
        }

    }

    static class ListContent {

        TextView name;

    }

    void ConfigProfile()
    {
        if(Settings.getInstance().getProfile() != null)
        {
            final MyProgressDialog cdd = new MyProgressDialog(getActivity(), "در حال بارگزاری . . .");
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.show();

            edtUserInfoName.setText(Settings.getInstance().getProfile().profile.getName());
            edtUserInfoMobile.setText(Settings.getInstance().getProfile().profile.getPhoneNumber());
            edtUserInfoEmail.setText(Settings.getInstance().getProfile().profile.getEmail());
            spinner.setSelection(Settings.getInstance().getProfile().profile.getCity());

            facadeService.GetProfile(new DataCallbbackOutput() {
                @Override
                public void onSuccess(String result) {

                    //String strResult = result;//.replace("\\", "");
                    structureCustomer = new Gson().fromJson(result, StructureCustomer.class);

                    if(structureCustomer != null)
                    {
                        List<String> strResult = new ArrayList<>();
                        try {
                            strResult.add(facadeService.searchnStringObject(result, "result", "name"));
                            strResult.add(facadeService.searchnStringObject(result, "result", "email"));
                            strResult.add(facadeService.searchnStringObject(result, "result", "mobile"));
                            strResult.add("" + facadeService.searchnIntObject(result, "gender", "city"));
                            if(edtUserInfoName.getText().toString().equals(""))
                                edtUserInfoName.setText(strResult.get(0));

                            if(edtUserInfoEmail.getText().toString().equals(""))
                                edtUserInfoEmail.setText(strResult.get(1));

                            if(edtUserInfoMobile.getText().toString().equals(""))
                                edtUserInfoMobile.setText(strResult.get(2));

                            spinner.setSelection(Integer.parseInt(strResult.get(3)));

                            Settings.getInstance().getProfile().profile.setName(strResult.get(0));
                            Settings.getInstance().getProfile().profile.setEmail(strResult.get(1));
                            Settings.getInstance().getProfile().profile.setPhoneNumber(strResult.get(2));
                            Settings.getInstance().getProfile().profile.setCity(Integer.parseInt(strResult.get(3)));
                            Settings.getInstance().getProfile().setAuthorizationToken(Settings.getInstance().getProfile().getAuthorizationToken());
                            Settings.getInstance().getProfile().setTenantId(1);
                            //Settings.getInstance().setProfile(structureCustomer);
                            Settings.getInstance().saveAll();


                            Snackbar.make(ActivityMain.activity, Html.fromHtml("<font color=\"#FF0000\">ذخیره پروفایل با موفقیت انجام شد</font>"), Snackbar.LENGTH_LONG);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    cdd.dismiss();
                }
            }, getActivity());
        }
    }
}

